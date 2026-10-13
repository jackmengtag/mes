package com.ruoyi.mes.ex.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.enums.InspectionResult;
import com.ruoyi.mes.common.enums.OperationStatus;
import com.ruoyi.mes.common.enums.SerialStatus;
import com.ruoyi.mes.common.enums.SyncStatus;
import com.ruoyi.mes.common.enums.WorkOrderStatus;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.ex.domain.ExProductSerial;
import com.ruoyi.mes.ex.domain.ExProductionReport;
import com.ruoyi.mes.ex.domain.ExWorkHour;
import com.ruoyi.mes.ex.mapper.ExProductSerialMapper;
import com.ruoyi.mes.ex.mapper.ExProductionReportMapper;
import com.ruoyi.mes.ex.mapper.ExWorkHourMapper;
import com.ruoyi.mes.integration.dto.MesDtos;
import com.ruoyi.mes.integration.support.DomainEventService;
import com.ruoyi.mes.integration.support.IdempotentService;
import com.ruoyi.mes.integration.support.MessageService;
import com.ruoyi.mes.md.domain.MdProduct;
import com.ruoyi.mes.md.mapper.MdProductMapper;
import com.ruoyi.mes.mt.service.MaterialService;
import com.ruoyi.mes.qc.domain.QcInspection;
import com.ruoyi.mes.qc.mapper.QcInspectionMapper;
import com.ruoyi.mes.tr.domain.TrTraceIndex;
import com.ruoyi.mes.tr.mapper.TrTraceIndexMapper;
import com.ruoyi.mes.wo.domain.WoOperationTask;
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.mapper.WoOperationTaskMapper;
import com.ruoyi.mes.wo.mapper.WoProductionOrderMapper;

/**
 * 生产执行业务服务：开工校验、报工（幂等）、序列号流转、工时与追溯履历（FR-EX / 架构 8.4 事务边界）
 *
 * <p>报工在同一本地事务内完成：校验状态 → 写报工 → 更新工序/工单进度 → 更新序列号 →
 * 写追溯履历 → 记录工时 → 写领域事件（待发送）。外部同步通过事件与消息表异步投递。</p>
 *
 * @author ruoyi
 */
@Service
public class ProductionExecService
{
    @Autowired
    private WoProductionOrderMapper orderMapper;

    @Autowired
    private WoOperationTaskMapper operationTaskMapper;

    @Autowired
    private ExProductSerialMapper serialMapper;

    @Autowired
    private ExProductionReportMapper reportMapper;

    @Autowired
    private ExWorkHourMapper workHourMapper;

    @Autowired
    private MdProductMapper productMapper;

    @Autowired
    private QcInspectionMapper inspectionMapper;

    @Autowired
    private TrTraceIndexMapper traceIndexMapper;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private IdempotentService idempotentService;

    @Autowired
    private DomainEventService domainEventService;

    @Autowired
    private MessageService messageService;

    /**
     * 工位开工校验（FR-EX-003）
     */
    @Transactional
    public WoOperationTask startOperation(Long taskId, String operatorNo, String traceId)
    {
        WoOperationTask task = operationTaskMapper.selectWoOperationTaskById(taskId);
        if (task == null)
        {
            throw new ServiceException("工序任务不存在：" + taskId);
        }
        if (!OperationStatus.NOT_STARTED.name().equals(task.getStatus())
                && !OperationStatus.DISPATCHED.name().equals(task.getStatus()))
        {
            throw new ServiceException("工序任务状态不允许开工：" + task.getStatus());
        }
        WoProductionOrder order = orderMapper.selectWoProductionOrderById(task.getProductionOrderId());
        if (order == null)
        {
            throw new ServiceException("工单不存在");
        }
        if (WorkOrderStatus.PAUSED.name().equals(order.getStatus())
                || WorkOrderStatus.COMPLETED.name().equals(order.getStatus())
                || WorkOrderStatus.CLOSED.name().equals(order.getStatus()))
        {
            throw new ServiceException("工单当前状态不允许开工：" + order.getStatus());
        }
        // 前置工序未完成不允许开工
        WoOperationTask opQuery = new WoOperationTask();
        opQuery.setProductionOrderId(order.getId());
        for (WoOperationTask other : operationTaskMapper.selectWoOperationTaskList(opQuery))
        {
            if (other.getSequenceNo() != null && task.getSequenceNo() != null
                    && other.getSequenceNo() < task.getSequenceNo()
                    && !OperationStatus.COMPLETED.name().equals(other.getStatus())
                    && !OperationStatus.CANCELLED.name().equals(other.getStatus()))
            {
                throw new ServiceException("前置工序未完成，不允许开工：" + other.getOperationName());
            }
        }
        // 关键物料齐套校验
        if (!materialService.checkKit(order.getId()))
        {
            throw new ServiceException("关键物料未齐套，不允许开工");
        }
        task.setStatus(OperationStatus.EXECUTING.name());
        task.setOperatorNo(operatorNo);
        task.setUpdateBy(operatorNo);
        operationTaskMapper.updateWoOperationTask(task);

        if (!WorkOrderStatus.EXECUTING.name().equals(order.getStatus()))
        {
            order.setStatus(WorkOrderStatus.EXECUTING.name());
        }
        if (order.getActualStartTime() == null)
        {
            order.setActualStartTime(new Date());
        }
        order.setUpdateBy(operatorNo);
        orderMapper.updateWoProductionOrder(order);
        domainEventService.publish("OperationStarted", "WoOperationTask", task.getId(),
                "{\"orderNo\":\"" + order.getOrderNo() + "\",\"operationCode\":\"" + task.getOperationCode() + "\"}", traceId);
        return task;
    }

    /**
     * 工序报工（FR-EX-004），幂等；报工成功即视为 MES 内部业务完成
     */
    @Transactional
    public ExProductionReport report(MesDtos.ProductionReportRequest req, String traceId)
    {
        String key = req.mesWorkOrderNo + "-" + req.operationCode + "-" + req.reportNo;
        IdempotentService.Result reg = idempotentService.tryRegister(key, "MES_REPORT");
        if (reg.isDuplicated())
        {
            Long bizId = reg.getRecord() == null ? null : reg.getRecord().getBizId();
            return bizId == null ? null : reportMapper.selectExProductionReportById(bizId);
        }
        WoProductionOrder order = findOrderByNo(req.mesWorkOrderNo);
        if (order == null)
        {
            throw new ServiceException("工单不存在：" + req.mesWorkOrderNo);
        }
        if (WorkOrderStatus.COMPLETED.name().equals(order.getStatus())
                || WorkOrderStatus.CLOSED.name().equals(order.getStatus()))
        {
            throw new ServiceException("已完工或已关闭工单不允许再次报工");
        }
        WoOperationTask task = findTask(order.getId(), req.operationCode);
        if (task == null)
        {
            throw new ServiceException("工序任务不存在：" + req.operationCode);
        }
        BigDecimal qualified = nz(req.qualifiedQuantity);
        BigDecimal unqualified = nz(req.unqualifiedQuantity);
        BigDecimal scrap = nz(req.scrapQuantity);
        BigDecimal reportQty = qualified.add(unqualified).add(scrap);
        if (reportQty.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ServiceException("报工数量必须大于 0");
        }
        BigDecimal reportable = nz(task.getPlannedQty()).subtract(nz(task.getCompletedQty())).subtract(nz(task.getScrapQty()));
        if (reportQty.compareTo(reportable) > 0)
        {
            throw new ServiceException("报工数量不得超过可报数量，可报：" + reportable);
        }
        // 写报工记录
        ExProductionReport report = new ExProductionReport();
        report.setReportNo(req.reportNo);
        report.setProductionOrderId(order.getId());
        report.setOrderNo(order.getOrderNo());
        report.setOperationTaskId(task.getId());
        report.setOperationCode(task.getOperationCode());
        report.setQualifiedQty(qualified);
        report.setUnqualifiedQty(unqualified);
        report.setScrapQty(scrap);
        report.setLaborMinutes(nz(req.laborMinutes));
        report.setMachineMinutes(nz(req.machineMinutes));
        report.setOperatorNo(StringUtils.isNotEmpty(req.operatorCode) ? req.operatorCode : "system");
        report.setShiftCode(req.shiftCode);
        report.setReportedAt(new Date());
        report.setIdempotencyKey(key);
        report.setSyncStatus(SyncStatus.PENDING.name());
        report.setCreateBy(report.getOperatorNo());
        reportMapper.insertExProductionReport(report);

        // 更新工序进度
        task.setCompletedQty(nz(task.getCompletedQty()).add(qualified).add(unqualified));
        task.setQualifiedQty(nz(task.getQualifiedQty()).add(qualified));
        task.setUnqualifiedQty(nz(task.getUnqualifiedQty()).add(unqualified));
        task.setScrapQty(nz(task.getScrapQty()).add(scrap));
        boolean forceInspect = MesConstants.YES.equals(task.getForceInspect());
        if (forceInspect && !hasPassedInspection(task.getId()))
        {
            task.setStatus(OperationStatus.WAIT_INSPECT.name());
        }
        else if (nz(task.getCompletedQty()).compareTo(nz(task.getPlannedQty())) >= 0)
        {
            task.setStatus(OperationStatus.COMPLETED.name());
        }
        else
        {
            task.setStatus(OperationStatus.EXECUTING.name());
        }
        task.setUpdateBy(report.getOperatorNo());
        operationTaskMapper.updateWoOperationTask(task);

        // 更新工单进度
        order.setCompletedQty(nz(order.getCompletedQty()).add(qualified).add(unqualified));
        order.setQualifiedQty(nz(order.getQualifiedQty()).add(qualified));
        order.setScrapQty(nz(order.getScrapQty()).add(scrap));
        if (!WorkOrderStatus.EXECUTING.name().equals(order.getStatus())
                && !WorkOrderStatus.PAUSED.name().equals(order.getStatus()))
        {
            order.setStatus(WorkOrderStatus.EXECUTING.name());
        }
        if (order.getActualStartTime() == null)
        {
            order.setActualStartTime(new Date());
        }
        order.setUpdateBy(report.getOperatorNo());
        orderMapper.updateWoProductionOrder(order);

        // 工时记录
        if (nz(req.laborMinutes).compareTo(BigDecimal.ZERO) > 0)
        {
            ExWorkHour wh = new ExWorkHour();
            wh.setProductionOrderId(order.getId());
            wh.setOperationTaskId(task.getId());
            wh.setWorkType("PROCESS");
            wh.setMinutes(nz(req.laborMinutes));
            wh.setOperatorNo(report.getOperatorNo());
            wh.setRecordTime(new Date());
            wh.setCreateBy(report.getOperatorNo());
            workHourMapper.insertExWorkHour(wh);
        }
        // 追溯履历
        TrTraceIndex idx = new TrTraceIndex();
        idx.setProductionOrderId(order.getId());
        idx.setOrderNo(order.getOrderNo());
        idx.setOperationCode(task.getOperationCode());
        idx.setOperatorNo(report.getOperatorNo());
        idx.setRefType("REPORT");
        idx.setRefId(report.getId());
        idx.setBizTime(new Date());
        idx.setCreateBy("system");
        traceIndexMapper.insertTrTraceIndex(idx);

        idempotentService.complete(reg.getRecord(), report.getId(), "OK", report.getReportNo());
        // 领域事件 + ERP 报工回传出站消息（失败不回滚现场报工）
        domainEventService.publish("ProductionReported", "WoProductionOrder", order.getId(),
                "{\"reportNo\":\"" + req.reportNo + "\",\"orderNo\":\"" + order.getOrderNo() + "\"}", traceId);
        messageService.recordOutbound("ProductionReport", "ERP", key,
                "{\"reportNo\":\"" + req.reportNo + "\",\"mesWorkOrderNo\":\"" + order.getOrderNo()
                        + "\",\"operationCode\":\"" + task.getOperationCode() + "\"}");
        return report;
    }

    /**
     * 绑定产品序列号到工单（FR-EX-005）
     */
    @Transactional
    public ExProductSerial bindSerial(String serialNo, Long orderId, String operator)
    {
        WoProductionOrder order = orderMapper.selectWoProductionOrderById(orderId);
        if (order == null)
        {
            throw new ServiceException("工单不存在：" + orderId);
        }
        ExProductSerial exist = findSerial(serialNo);
        if (exist != null)
        {
            if (exist.getProductionOrderId() != null && !exist.getProductionOrderId().equals(orderId))
            {
                throw new ServiceException("序列号已绑定其它工单：" + serialNo);
            }
            return exist;
        }
        ExProductSerial serial = new ExProductSerial();
        serial.setSerialNo(serialNo);
        serial.setProductId(order.getProductId());
        serial.setProductCode(order.getProductCode());
        serial.setProductionOrderId(orderId);
        serial.setCurrentStatus(SerialStatus.CREATED.name());
        serial.setQualityStatus(InspectionResult.PENDING.name());
        serial.setScrapFlag(MesConstants.NO);
        serial.setCreateBy(operator);
        serialMapper.insertExProductSerial(serial);
        return serial;
    }

    private boolean hasPassedInspection(Long operationTaskId)
    {
        QcInspection query = new QcInspection();
        query.setOperationTaskId(operationTaskId);
        query.setResult(InspectionResult.PASS.name());
        return !inspectionMapper.selectQcInspectionList(query).isEmpty();
    }

    private WoOperationTask findTask(Long orderId, String operationCode)
    {
        WoOperationTask query = new WoOperationTask();
        query.setProductionOrderId(orderId);
        query.setOperationCode(operationCode);
        for (WoOperationTask t : operationTaskMapper.selectWoOperationTaskList(query))
        {
            if (operationCode != null && operationCode.equals(t.getOperationCode()))
            {
                return t;
            }
        }
        return null;
    }

    private WoProductionOrder findOrderByNo(String orderNo)
    {
        WoProductionOrder query = new WoProductionOrder();
        query.setOrderNo(orderNo);
        for (WoProductionOrder o : orderMapper.selectWoProductionOrderList(query))
        {
            if (orderNo != null && orderNo.equals(o.getOrderNo()))
            {
                return o;
            }
        }
        return null;
    }

    private ExProductSerial findSerial(String serialNo)
    {
        if (serialNo == null)
        {
            return null;
        }
        ExProductSerial query = new ExProductSerial();
        query.setSerialNo(serialNo);
        List<ExProductSerial> list = serialMapper.selectExProductSerialList(query);
        for (ExProductSerial s : list)
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
