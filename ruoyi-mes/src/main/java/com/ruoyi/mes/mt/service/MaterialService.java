package com.ruoyi.mes.mt.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.enums.KitStatus;
import com.ruoyi.mes.common.enums.SyncStatus;
import com.ruoyi.mes.common.mapper.MesQueryMapper;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.integration.dto.MesDtos;
import com.ruoyi.mes.integration.support.DomainEventService;
import com.ruoyi.mes.integration.support.IdempotentService;
import com.ruoyi.mes.integration.support.MessageService;
import com.ruoyi.mes.md.domain.MdBomLine;
import com.ruoyi.mes.md.mapper.MdBomLineMapper;
import com.ruoyi.mes.mt.domain.MtMaterialConsumption;
import com.ruoyi.mes.mt.domain.MtMaterialRequirement;
import com.ruoyi.mes.mt.mapper.MtMaterialConsumptionMapper;
import com.ruoyi.mes.mt.mapper.MtMaterialRequirementMapper;
import com.ruoyi.mes.tr.domain.TrTraceIndex;
import com.ruoyi.mes.tr.mapper.TrTraceIndexMapper;
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.mapper.WoProductionOrderMapper;

/**
 * 物料管理业务服务：需求计算、齐套判断、批次消耗、退补料（FR-MT / IR-WMS）
 *
 * @author ruoyi
 */
@Service
public class MaterialService
{
    @Autowired
    private MdBomLineMapper bomLineMapper;

    @Autowired
    private MtMaterialRequirementMapper requirementMapper;

    @Autowired
    private MtMaterialConsumptionMapper consumptionMapper;

    @Autowired
    private WoProductionOrderMapper productionOrderMapper;

    @Autowired
    private TrTraceIndexMapper traceIndexMapper;

    @Autowired
    private MesQueryMapper queryMapper;

    @Autowired
    private IdempotentService idempotentService;

    @Autowired
    private DomainEventService domainEventService;

    @Autowired
    private MessageService messageService;

    /**
     * 按 BOM 计算工单物料需求（FR-MT-001）
     */
    @Transactional
    public List<MtMaterialRequirement> calcRequirement(WoProductionOrder order)
    {
        if (order == null || order.getBomVersionId() == null || order.getPlannedQty() == null)
        {
            return new ArrayList<>();
        }
        MdBomLine query = new MdBomLine();
        query.setBomId(order.getBomVersionId());
        List<MdBomLine> lines = bomLineMapper.selectMdBomLineList(query);
        List<MtMaterialRequirement> result = new ArrayList<>();
        for (MdBomLine line : lines)
        {
            BigDecimal base = order.getPlannedQty().multiply(line.getQuantity() == null ? BigDecimal.ZERO : line.getQuantity());
            BigDecimal loss = line.getLossRate() == null ? BigDecimal.ZERO : line.getLossRate();
            BigDecimal required = base.multiply(BigDecimal.ONE.add(loss)).setScale(4, RoundingMode.HALF_UP);
            MtMaterialRequirement req = new MtMaterialRequirement();
            req.setRequirementNo(MesSeqUtils.generate(MesConstants.PREFIX_REQUIREMENT));
            req.setProductionOrderId(order.getId());
            req.setOrderNo(order.getOrderNo());
            req.setOperationCode(line.getFeedingOperationCode());
            req.setMaterialId(line.getMaterialId());
            req.setMaterialCode(line.getMaterialCode());
            req.setMaterialName(line.getMaterialName());
            req.setRequiredQty(required);
            req.setAllocatedQty(BigDecimal.ZERO);
            req.setPickedQty(BigDecimal.ZERO);
            req.setConsumedQty(BigDecimal.ZERO);
            req.setLackQty(required);
            req.setKeyFlag(line.getKeyFlag());
            req.setKitStatus(KitStatus.NOT_CALCULATED.name());
            req.setStatus(MesConstants.STATUS_NORMAL);
            req.setCreateBy("system");
            requirementMapper.insertMtMaterialRequirement(req);
            result.add(req);
        }
        // 同步下发 WMS 备料请求并记录出站消息
        if (!result.isEmpty())
        {
            messageService.recordOutbound("MaterialRequirement", "WMS", order.getOrderNo() + "-REQ",
                    "{\"workOrderNo\":\"" + order.getOrderNo() + "\",\"items\":" + result.size() + "}");
        }
        return result;
    }

    /**
     * 应用 WMS 备料/拣料/配送结果，更新齐套状态（IR-WMS-002/003）
     */
    @Transactional
    public void applyPicking(MesDtos.PickingResultRequest req)
    {
        if (req == null || req.workOrderNo == null)
        {
            throw new ServiceException("备料结果缺少工单号");
        }
        WoProductionOrder order = findOrderByNo(req.workOrderNo);
        if (order == null)
        {
            throw new ServiceException("工单不存在：" + req.workOrderNo);
        }
        if (req.items != null)
        {
            for (java.util.Map<String, Object> item : req.items)
            {
                String materialCode = str(item.get("materialCode"));
                BigDecimal qty = decimal(item.get("quantity"));
                MtMaterialRequirement query = new MtMaterialRequirement();
                query.setProductionOrderId(order.getId());
                query.setMaterialCode(materialCode);
                List<MtMaterialRequirement> list = requirementMapper.selectMtMaterialRequirementList(query);
                for (MtMaterialRequirement r : list)
                {
                    if (!materialCode.equals(r.getMaterialCode()))
                    {
                        continue;
                    }
                    BigDecimal allocated = nz(r.getAllocatedQty()).add(qty);
                    r.setAllocatedQty(allocated);
                    r.setPickedQty(allocated);
                    BigDecimal lack = nz(r.getRequiredQty()).subtract(allocated);
                    r.setLackQty(lack.compareTo(BigDecimal.ZERO) > 0 ? lack : BigDecimal.ZERO);
                    r.setKitStatus(lack.compareTo(BigDecimal.ZERO) > 0 ? KitStatus.PARTIAL.name()
                            : ("Y".equals(req.deliveryFlag) ? KitStatus.DELIVERED.name() : KitStatus.KITTED.name()));
                    r.setUpdateBy("system");
                    requirementMapper.updateMtMaterialRequirement(r);
                }
            }
        }
        recomputeOrderKit(order.getId());
    }

    /**
     * 判断工单关键物料是否齐套（FR-MT-002）
     */
    public boolean checkKit(Long orderId)
    {
        return queryMapper.countKeyMaterialLack(orderId) == 0;
    }

    /**
     * 重算并回写工单齐套状态
     */
    @Transactional
    public void recomputeOrderKit(Long orderId)
    {
        WoProductionOrder order = productionOrderMapper.selectWoProductionOrderById(orderId);
        if (order == null)
        {
            return;
        }
        MtMaterialRequirement query = new MtMaterialRequirement();
        query.setProductionOrderId(orderId);
        List<MtMaterialRequirement> list = requirementMapper.selectMtMaterialRequirementList(query);
        String kitStatus;
        if (list.isEmpty())
        {
            kitStatus = KitStatus.NOT_CALCULATED.name();
        }
        else
        {
            boolean anyLack = false;
            boolean anyConsumed = false;
            boolean allKittedOrBeyond = true;
            boolean allConsumed = true;
            for (MtMaterialRequirement r : list)
            {
                String s = r.getKitStatus();
                boolean key = MesConstants.YES.equals(r.getKeyFlag());
                if (key && !(KitStatus.KITTED.name().equals(s) || KitStatus.DELIVERED.name().equals(s)
                        || KitStatus.PARTIAL_CONSUMED.name().equals(s) || KitStatus.CONSUMED.name().equals(s)))
                {
                    anyLack = true;
                    allKittedOrBeyond = false;
                }
                if (KitStatus.CONSUMED.name().equals(s))
                {
                    anyConsumed = true;
                }
                else
                {
                    allConsumed = false;
                }
            }
            if (anyLack)
            {
                kitStatus = KitStatus.LACK.name();
            }
            else if (allConsumed && !list.isEmpty())
            {
                kitStatus = KitStatus.CONSUMED.name();
            }
            else if (anyConsumed)
            {
                kitStatus = KitStatus.PARTIAL_CONSUMED.name();
            }
            else if (allKittedOrBeyond)
            {
                kitStatus = KitStatus.KITTED.name();
            }
            else
            {
                kitStatus = KitStatus.PARTIAL.name();
            }
        }
        order.setKitStatus(kitStatus);
        order.setUpdateBy("system");
        productionOrderMapper.updateWoProductionOrder(order);
    }

    /**
     * 物料消耗确认（FR-MT-004 / IR-WMS-004），幂等
     */
    @Transactional
    public MtMaterialConsumption confirmConsumption(MesDtos.MaterialConsumptionRequest req, String traceId)
    {
        String key = req.workOrderNo + "-" + req.productSerialNo + "-" + req.consumeNo;
        IdempotentService.Result reg = idempotentService.tryRegister(key, "WMS_CONSUMPTION");
        if (reg.isDuplicated())
        {
            Long bizId = reg.getRecord() == null ? null : reg.getRecord().getBizId();
            return bizId == null ? null : consumptionMapper.selectMtMaterialConsumptionById(bizId);
        }
        WoProductionOrder order = findOrderByNo(req.workOrderNo);
        if (order == null)
        {
            throw new ServiceException("工单不存在：" + req.workOrderNo);
        }
        MtMaterialConsumption last = null;
        if (req.items != null)
        {
            for (java.util.Map<String, Object> item : req.items)
            {
                String materialCode = str(item.get("materialCode"));
                BigDecimal qty = decimal(item.get("quantity"));
                MtMaterialConsumption c = new MtMaterialConsumption();
                c.setConsumeNo(req.consumeNo);
                c.setMaterialCode(materialCode);
                c.setMaterialBatchNo(str(item.get("batchNo")));
                c.setProductionOrderId(order.getId());
                c.setOrderNo(order.getOrderNo());
                c.setSerialNo(req.productSerialNo);
                c.setOperationCode(req.operationCode);
                c.setConsumedQty(qty);
                c.setOperatorNo(req.consumedBy);
                c.setSyncStatus(SyncStatus.PENDING.name());
                c.setConsumedAt(new Date());
                c.setIdempotencyKey(key + "-" + materialCode);
                c.setCreateBy(StringUtils.isNotEmpty(req.consumedBy) ? req.consumedBy : "system");
                consumptionMapper.insertMtMaterialConsumption(c);
                last = c;
                // 更新需求消耗量与齐套
                MtMaterialRequirement query = new MtMaterialRequirement();
                query.setProductionOrderId(order.getId());
                query.setMaterialCode(materialCode);
                for (MtMaterialRequirement r : requirementMapper.selectMtMaterialRequirementList(query))
                {
                    if (materialCode.equals(r.getMaterialCode()))
                    {
                        BigDecimal consumed = nz(r.getConsumedQty()).add(qty);
                        r.setConsumedQty(consumed);
                        boolean fully = consumed.compareTo(nz(r.getRequiredQty())) >= 0;
                        r.setKitStatus(fully ? KitStatus.CONSUMED.name() : KitStatus.PARTIAL_CONSUMED.name());
                        r.setUpdateBy("system");
                        requirementMapper.updateMtMaterialRequirement(r);
                    }
                }
                // 追溯索引
                writeTrace(order, req.productSerialNo, materialCode, c.getMaterialBatchNo(), req.operationCode, "CONSUMPTION", c.getId());
            }
        }
        if (last != null)
        {
            idempotentService.complete(reg.getRecord(), last.getId(), "OK", req.consumeNo);
        }
        recomputeOrderKit(order.getId());
        domainEventService.publish("MaterialConsumed", "WoProductionOrder", order.getId(),
                "{\"consumeNo\":\"" + req.consumeNo + "\"}", traceId);
        messageService.recordOutbound("MaterialConsumption", "WMS", key,
                "{\"consumeNo\":\"" + req.consumeNo + "\"}");
        return last;
    }

    /**
     * 查询工单物料需求
     */
    public List<MtMaterialRequirement> listRequirementByOrder(Long orderId)
    {
        MtMaterialRequirement query = new MtMaterialRequirement();
        query.setProductionOrderId(orderId);
        return requirementMapper.selectMtMaterialRequirementList(query);
    }

    private void writeTrace(WoProductionOrder order, String serialNo, String materialCode, String batchNo,
            String operationCode, String refType, Long refId)
    {
        TrTraceIndex idx = new TrTraceIndex();
        idx.setSerialNo(serialNo);
        idx.setProductionOrderId(order.getId());
        idx.setOrderNo(order.getOrderNo());
        idx.setMaterialBatchNo(batchNo);
        idx.setOperationCode(operationCode);
        idx.setRefType(refType);
        idx.setRefId(refId);
        idx.setBizTime(new Date());
        idx.setCreateBy("system");
        traceIndexMapper.insertTrTraceIndex(idx);
    }

    private WoProductionOrder findOrderByNo(String orderNo)
    {
        WoProductionOrder query = new WoProductionOrder();
        query.setOrderNo(orderNo);
        for (WoProductionOrder o : productionOrderMapper.selectWoProductionOrderList(query))
        {
            if (orderNo.equals(o.getOrderNo()))
            {
                return o;
            }
        }
        return null;
    }

    private static BigDecimal nz(BigDecimal v)
    {
        return v == null ? BigDecimal.ZERO : v;
    }

    private static String str(Object o)
    {
        return o == null ? null : String.valueOf(o);
    }

    private static BigDecimal decimal(Object o)
    {
        if (o == null)
        {
            return BigDecimal.ZERO;
        }
        if (o instanceof BigDecimal)
        {
            return (BigDecimal) o;
        }
        return new BigDecimal(String.valueOf(o));
    }
}
