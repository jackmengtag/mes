package com.ruoyi.mes.qc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.enums.DispositionType;
import com.ruoyi.mes.common.enums.InspectionResult;
import com.ruoyi.mes.common.enums.NonconformityStatus;
import com.ruoyi.mes.common.enums.SerialStatus;
import com.ruoyi.mes.common.enums.WorkOrderStatus;
import com.ruoyi.mes.common.mapper.MesQueryMapper;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.ex.domain.ExProductSerial;
import com.ruoyi.mes.ex.mapper.ExProductSerialMapper;
import com.ruoyi.mes.integration.support.DomainEventService;
import com.ruoyi.mes.integration.support.MessageService;
import com.ruoyi.mes.md.domain.MdInspectionPlanItem;
import com.ruoyi.mes.md.mapper.MdInspectionPlanItemMapper;
import com.ruoyi.mes.qc.domain.QcInspection;
import com.ruoyi.mes.qc.domain.QcInspectionValue;
import com.ruoyi.mes.qc.domain.QcNonconformity;
import com.ruoyi.mes.qc.domain.QcRelease;
import com.ruoyi.mes.qc.domain.QcRework;
import com.ruoyi.mes.qc.mapper.QcInspectionMapper;
import com.ruoyi.mes.qc.mapper.QcInspectionValueMapper;
import com.ruoyi.mes.qc.mapper.QcNonconformityMapper;
import com.ruoyi.mes.qc.mapper.QcReleaseMapper;
import com.ruoyi.mes.qc.mapper.QcReworkMapper;
import com.ruoyi.mes.tr.domain.TrTraceIndex;
import com.ruoyi.mes.tr.mapper.TrTraceIndexMapper;
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.domain.WoReworkOrder;
import com.ruoyi.mes.wo.mapper.WoProductionOrderMapper;
import com.ruoyi.mes.wo.mapper.WoReworkOrderMapper;

/**
 * 质量管理业务服务：检验、不合格品处理、返工返修、质量放行（FR-QC / 架构 5.4.2 不合格品状态机）
 *
 * @author ruoyi
 */
@Service
public class QualityService
{
    @Autowired
    private QcInspectionMapper inspectionMapper;

    @Autowired
    private QcInspectionValueMapper inspectionValueMapper;

    @Autowired
    private QcNonconformityMapper nonconformityMapper;

    @Autowired
    private QcReworkMapper reworkMapper;

    @Autowired
    private QcReleaseMapper releaseMapper;

    @Autowired
    private MdInspectionPlanItemMapper planItemMapper;

    @Autowired
    private ExProductSerialMapper serialMapper;

    @Autowired
    private WoProductionOrderMapper orderMapper;

    @Autowired
    private WoReworkOrderMapper reworkOrderMapper;

    @Autowired
    private TrTraceIndexMapper traceIndexMapper;

    @Autowired
    private MesQueryMapper queryMapper;

    @Autowired
    private DomainEventService domainEventService;

    @Autowired
    private MessageService messageService;

    /**
     * 创建检验单（FR-QC-001/002/003/004/005）
     */
    @Transactional
    public QcInspection createInspection(QcInspection inspection)
    {
        if (StringUtils.isEmpty(inspection.getInspectionNo()))
        {
            inspection.setInspectionNo(MesSeqUtils.generate(MesConstants.PREFIX_INSPECTION));
        }
        if (StringUtils.isEmpty(inspection.getResult()))
        {
            inspection.setResult(InspectionResult.PENDING.name());
        }
        inspection.setCreateBy(StringUtils.isNotEmpty(inspection.getInspectorNo()) ? inspection.getInspectorNo() : "system");
        inspectionMapper.insertQcInspection(inspection);
        // 按检验方案生成自检项
        if (inspection.getInspectionPlanId() != null)
        {
            MdInspectionPlanItem query = new MdInspectionPlanItem();
            query.setPlanId(inspection.getInspectionPlanId());
            for (MdInspectionPlanItem item : planItemMapper.selectMdInspectionPlanItemList(query))
            {
                QcInspectionValue v = new QcInspectionValue();
                v.setInspectionId(inspection.getId());
                v.setItemCode(item.getItemCode());
                v.setItemName(item.getItemName());
                v.setLowerLimit(item.getLowerLimit());
                v.setUpperLimit(item.getUpperLimit());
                v.setResult(InspectionResult.PENDING.name());
                v.setCreateBy("system");
                inspectionValueMapper.insertQcInspectionValue(v);
            }
        }
        return inspection;
    }

    /**
     * 提交检验结果并自动判定（FR-QC-005）；不合格自动触发隔离与不合格记录（FR-QC-006）
     */
    @Transactional
    public QcInspection submitResult(Long inspectionId, List<QcInspectionValue> values, String inspectorNo, String traceId)
    {
        QcInspection inspection = inspectionMapper.selectQcInspectionById(inspectionId);
        if (inspection == null)
        {
            throw new ServiceException("检验单不存在：" + inspectionId);
        }
        boolean overall = true;
        if (values != null)
        {
            for (QcInspectionValue v : values)
            {
                v.setInspectionId(inspectionId);
                v.setResult(judgeValue(v));
                if (v.getId() != null)
                {
                    v.setUpdateBy(inspectorNo);
                    inspectionValueMapper.updateQcInspectionValue(v);
                }
                else
                {
                    v.setCreateBy(inspectorNo);
                    inspectionValueMapper.insertQcInspectionValue(v);
                }
                if (!InspectionResult.PASS.name().equals(v.getResult()))
                {
                    overall = false;
                }
            }
        }
        // 汇总已有明细判定
        QcInspectionValue q = new QcInspectionValue();
        q.setInspectionId(inspectionId);
        for (QcInspectionValue v : inspectionValueMapper.selectQcInspectionValueList(q))
        {
            if (InspectionResult.FAIL.name().equals(v.getResult()))
            {
                overall = false;
            }
        }
        inspection.setResult(overall ? InspectionResult.PASS.name() : InspectionResult.FAIL.name());
        inspection.setInspectorNo(inspectorNo);
        inspection.setInspectedAt(new Date());
        inspection.setUpdateBy(inspectorNo);
        inspectionMapper.updateQcInspection(inspection);

        if (overall)
        {
            if (StringUtils.isNotEmpty(inspection.getSerialNo()))
            {
                ExProductSerial serial = findSerial(inspection.getSerialNo());
                if (serial != null)
                {
                    serial.setQualityStatus(InspectionResult.PASS.name());
                    serial.setUpdateBy(inspectorNo);
                    serialMapper.updateExProductSerial(serial);
                }
            }
        }
        else
        {
            // 创建不合格记录并隔离
            QcNonconformity nc = new QcNonconformity();
            nc.setNonconformityNo(MesSeqUtils.generate(MesConstants.PREFIX_NONCONFORMITY));
            nc.setProductionOrderId(inspection.getProductionOrderId());
            nc.setOrderNo(inspection.getOrderNo());
            nc.setOperationTaskId(inspection.getOperationTaskId());
            nc.setOperationCode(inspection.getOperationCode());
            nc.setSerialNo(inspection.getSerialNo());
            nc.setDefectDesc("检验不合格（" + inspection.getInspectionType() + "）");
            nc.setDiscoverySource(inspection.getInspectionType());
            nc.setDiscoveryNo(inspection.getInspectionNo());
            nc.setQty(BigDecimal.ONE);
            nc.setStatus(NonconformityStatus.ISOLATED.name());
            nc.setCreateBy(inspectorNo);
            nonconformityMapper.insertQcNonconformity(nc);
            if (StringUtils.isNotEmpty(inspection.getSerialNo()))
            {
                ExProductSerial serial = findSerial(inspection.getSerialNo());
                if (serial != null)
                {
                    serial.setQualityStatus(InspectionResult.FAIL.name());
                    serial.setCurrentStatus(SerialStatus.UNQUALIFIED.name());
                    serial.setWarehouseStatus("FROZEN");
                    serial.setUpdateBy(inspectorNo);
                    serialMapper.updateExProductSerial(serial);
                }
            }
            domainEventService.publish("NonconformityCreated", "QcNonconformity", nc.getId(),
                    "{\"nonconformityNo\":\"" + nc.getNonconformityNo() + "\"}", traceId);
        }
        writeTrace(inspection, inspectorNo);
        return inspection;
    }

    /**
     * 不合格品判定（FR-QC-007）：返工/返修/报废/让步接收/退供应商
     */
    @Transactional
    public QcNonconformity judge(Long nonconformityId, String disposalType, String reason,
            String dispositionBy, boolean authorized)
    {
        QcNonconformity nc = nonconformityMapper.selectQcNonconformityById(nonconformityId);
        if (nc == null)
        {
            throw new ServiceException("不合格记录不存在：" + nonconformityId);
        }
        if (DispositionType.SCRAP.name().equals(disposalType) || DispositionType.CONCESSION.name().equals(disposalType))
        {
            if (!authorized)
            {
                throw new ServiceException("报废与让步接收必须经过授权审批");
            }
        }
        nc.setDisposalType(disposalType);
        nc.setDispositionReason(reason);
        nc.setDispositionBy(dispositionBy);
        nc.setDispositionTime(new Date());
        if (authorized)
        {
            nc.setAuthorizedBy(dispositionBy);
        }
        if (DispositionType.REWORK.name().equals(disposalType))
        {
            nc.setStatus(NonconformityStatus.REWORKING.name());
            createRework(nc, dispositionBy);
        }
        else if (DispositionType.REPAIR.name().equals(disposalType))
        {
            nc.setStatus(NonconformityStatus.REPAIRING.name());
            createRework(nc, dispositionBy);
        }
        else if (DispositionType.SCRAP.name().equals(disposalType))
        {
            nc.setStatus(NonconformityStatus.SCRAPPED.name());
            if (StringUtils.isNotEmpty(nc.getSerialNo()))
            {
                ExProductSerial serial = findSerial(nc.getSerialNo());
                if (serial != null)
                {
                    serial.setScrapFlag(MesConstants.YES);
                    serial.setCurrentStatus(SerialStatus.SCRAPPED.name());
                    serial.setUpdateBy(dispositionBy);
                    serialMapper.updateExProductSerial(serial);
                }
            }
            // 报废扣减可用数量
            if (nc.getProductionOrderId() != null && nc.getQty() != null)
            {
                WoProductionOrder order = orderMapper.selectWoProductionOrderById(nc.getProductionOrderId());
                if (order != null)
                {
                    order.setScrapQty(nz(order.getScrapQty()).add(nc.getQty()));
                    order.setUpdateBy(dispositionBy);
                    orderMapper.updateWoProductionOrder(order);
                }
            }
        }
        else if (DispositionType.CONCESSION.name().equals(disposalType))
        {
            nc.setStatus(NonconformityStatus.CONCESSION.name());
        }
        else if (DispositionType.RETURN_SUPPLIER.name().equals(disposalType))
        {
            nc.setStatus(NonconformityStatus.CLOSED.name());
        }
        nc.setUpdateBy(dispositionBy);
        nonconformityMapper.updateQcNonconformity(nc);
        return nc;
    }

    /**
     * 返工复检（FR-QC-008）：复检不合格需再次判定
     */
    @Transactional
    public QcNonconformity reinspect(Long nonconformityId, String result, String operator)
    {
        QcNonconformity nc = nonconformityMapper.selectQcNonconformityById(nonconformityId);
        if (nc == null)
        {
            throw new ServiceException("不合格记录不存在：" + nonconformityId);
        }
        nc.setReinspectResult(result);
        nc.setStatus(InspectionResult.PASS.name().equals(result)
                ? NonconformityStatus.CLOSED.name() : NonconformityStatus.WAIT_DISPOSITION.name());
        nc.setUpdateBy(operator);
        nonconformityMapper.updateQcNonconformity(nc);
        if (InspectionResult.PASS.name().equals(result) && StringUtils.isNotEmpty(nc.getSerialNo()))
        {
            ExProductSerial serial = findSerial(nc.getSerialNo());
            if (serial != null && !MesConstants.YES.equals(serial.getScrapFlag()))
            {
                serial.setQualityStatus(InspectionResult.PASS.name());
                serial.setCurrentStatus(SerialStatus.QUALIFIED.name());
                serial.setWarehouseStatus("RELEASED");
                serial.setUpdateBy(operator);
                serialMapper.updateExProductSerial(serial);
            }
        }
        return nc;
    }

    /**
     * 质量放行（FR-QC-010 / 4.7.2）：前置条件不满足时列出阻塞项，不允许强制放行
     */
    @Transactional
    public QcRelease releaseOrder(Long orderId, String releaseBy, boolean special, String traceId)
    {
        WoProductionOrder order = orderMapper.selectWoProductionOrderById(orderId);
        if (order == null)
        {
            throw new ServiceException("工单不存在：" + orderId);
        }
        List<String> blockers = collectBlockers(orderId);
        if (!blockers.isEmpty() && !special)
        {
            throw new ServiceException("质量放行前置条件不满足：" + String.join("；", blockers));
        }
        QcRelease release = new QcRelease();
        release.setReleaseNo(MesSeqUtils.generate(MesConstants.PREFIX_RELEASE));
        release.setProductionOrderId(orderId);
        release.setOrderNo(order.getOrderNo());
        release.setReleaseResult(InspectionResult.PASS.name());
        release.setReleaseBy(releaseBy);
        release.setReleaseTime(new Date());
        release.setSpecialFlag(special ? MesConstants.YES : MesConstants.NO);
        release.setBlockReason(blockers.isEmpty() ? null : String.join("；", blockers));
        release.setCreateBy(releaseBy);
        releaseMapper.insertQcRelease(release);
        // 解除产品入库限制
        ExProductSerial q = new ExProductSerial();
        q.setProductionOrderId(orderId);
        for (ExProductSerial serial : serialMapper.selectExProductSerialList(q))
        {
            if (!MesConstants.YES.equals(serial.getScrapFlag()))
            {
                serial.setWarehouseStatus("RELEASED");
                serial.setUpdateBy(releaseBy);
                serialMapper.updateExProductSerial(serial);
            }
        }
        domainEventService.publish("QualityReleased", "WoProductionOrder", orderId,
                "{\"releaseNo\":\"" + release.getReleaseNo() + "\"}", traceId);
        messageService.recordOutbound("ProductInbound", "WMS", release.getReleaseNo(),
                "{\"workOrderNo\":\"" + order.getOrderNo() + "\",\"inboundNo\":\"" + release.getReleaseNo() + "\"}");
        return release;
    }

    /**
     * 收集质量放行阻塞项
     */
    public List<String> collectBlockers(Long orderId)
    {
        List<String> blockers = new ArrayList<>();
        if (queryMapper.countOpenNonconformity(orderId) > 0)
        {
            blockers.add("存在未关闭不合格品");
        }
        if (queryMapper.countPendingForceInspection(orderId) > 0)
        {
            blockers.add("存在未完成的强制检验工序");
        }
        if (queryMapper.countUnfinishedOperation(orderId) > 0)
        {
            blockers.add("存在未完成工序");
        }
        return blockers;
    }

    /**
     * 记录成品入库结果（IR-WMS-007）：WMS 返回库存凭证后更新成品状态
     */
    @Transactional
    public void confirmInbound(String workOrderNo, String receiptNo, String operator)
    {
        WoProductionOrder query = new WoProductionOrder();
        query.setOrderNo(workOrderNo);
        for (WoProductionOrder order : orderMapper.selectWoProductionOrderList(query))
        {
            if (workOrderNo.equals(order.getOrderNo()))
            {
                ExProductSerial sq = new ExProductSerial();
                sq.setProductionOrderId(order.getId());
                for (ExProductSerial serial : serialMapper.selectExProductSerialList(sq))
                {
                    serial.setWarehouseStatus(SerialStatus.IN_STOCK.name());
                    serial.setCurrentStatus(SerialStatus.IN_STOCK.name());
                    serial.setUpdateBy(operator);
                    serialMapper.updateExProductSerial(serial);
                }
                // 回传 ERP 完工（异步消息）
                messageService.recordOutbound("ProductionCompletion", "ERP", order.getOrderNo() + "-INBOUND",
                        "{\"mesWorkOrderNo\":\"" + order.getOrderNo() + "\",\"receiptNo\":\"" + receiptNo + "\"}");
            }
        }
    }

    private void createRework(QcNonconformity nc, String operator)
    {
        QcRework rework = new QcRework();
        rework.setReworkNo(MesSeqUtils.generate("RW"));
        rework.setNonconformityId(nc.getId());
        rework.setProductionOrderId(nc.getProductionOrderId());
        rework.setSerialNo(nc.getSerialNo());
        rework.setOperationCode(nc.getOperationCode());
        rework.setReworkQty(nc.getQty());
        rework.setExecutorNo(operator);
        rework.setStatus("REWORKING");
        rework.setCreateBy(operator);
        reworkMapper.insertQcRework(rework);

        WoReworkOrder ro = new WoReworkOrder();
        ro.setReworkNo(MesSeqUtils.generate(MesConstants.PREFIX_REWORK));
        ro.setOriginOrderId(nc.getProductionOrderId());
        ro.setOriginOrderNo(nc.getOrderNo());
        ro.setNonconformityId(nc.getId());
        ro.setSerialNo(nc.getSerialNo());
        ro.setOperationCode(nc.getOperationCode());
        ro.setReworkQty(nc.getQty());
        ro.setReason(nc.getDefectDesc());
        ro.setStatus("REWORKING");
        ro.setCreateBy(operator);
        reworkOrderMapper.insertWoReworkOrder(ro);
    }

    private void writeTrace(QcInspection inspection, String operator)
    {
        TrTraceIndex idx = new TrTraceIndex();
        idx.setSerialNo(inspection.getSerialNo());
        idx.setProductionOrderId(inspection.getProductionOrderId());
        idx.setOrderNo(inspection.getOrderNo());
        idx.setOperationCode(inspection.getOperationCode());
        idx.setOperatorNo(operator);
        idx.setRefType("INSPECTION");
        idx.setRefId(inspection.getId());
        idx.setBizTime(new Date());
        idx.setCreateBy("system");
        traceIndexMapper.insertTrTraceIndex(idx);
    }

    private static String judgeValue(QcInspectionValue v)
    {
        if (v.getItemValue() == null || v.getItemValue().isEmpty())
        {
            return InspectionResult.PENDING.name();
        }
        try
        {
            BigDecimal val = new BigDecimal(v.getItemValue());
            if (v.getLowerLimit() != null && val.compareTo(v.getLowerLimit()) < 0)
            {
                return InspectionResult.FAIL.name();
            }
            if (v.getUpperLimit() != null && val.compareTo(v.getUpperLimit()) > 0)
            {
                return InspectionResult.FAIL.name();
            }
            return InspectionResult.PASS.name();
        }
        catch (NumberFormatException e)
        {
            // 非数值型：由人工判定，默认合格
            return InspectionResult.PASS.name();
        }
    }

    private ExProductSerial findSerial(String serialNo)
    {
        ExProductSerial query = new ExProductSerial();
        query.setSerialNo(serialNo);
        for (ExProductSerial s : serialMapper.selectExProductSerialList(query))
        {
            if (serialNo.equals(s.getSerialNo()))
            {
                return s;
            }
        }
        return null;
    }

    private static BigDecimal nz(BigDecimal v)
    {
        return v == null ? BigDecimal.ZERO : v;
    }
}
