package com.ruoyi.mes.wo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.mes.common.enums.DocumentStatus;
import com.ruoyi.mes.common.enums.OperationStatus;
import com.ruoyi.mes.common.enums.SyncStatus;
import com.ruoyi.mes.common.enums.WorkOrderStatus;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.mapper.MesQueryMapper;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.integration.support.DomainEventService;
import com.ruoyi.mes.integration.support.MessageService;
import com.ruoyi.mes.md.domain.MdRoute;
import com.ruoyi.mes.md.domain.MdRouteOperation;
import com.ruoyi.mes.md.mapper.MdRouteMapper;
import com.ruoyi.mes.md.mapper.MdRouteOperationMapper;
import com.ruoyi.mes.mt.domain.MtMaterialConsumption;
import com.ruoyi.mes.mt.mapper.MtMaterialConsumptionMapper;
import com.ruoyi.mes.mt.service.MaterialService;
import com.ruoyi.mes.wo.domain.WoOperationTask;
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.mapper.WoOperationTaskMapper;
import com.ruoyi.mes.wo.mapper.WoProductionOrderMapper;

/**
 * 工单业务流：审核、下达（生成工序任务与物料需求）、暂停、恢复、完工、关闭、拆分
 * （FR-WO-001 ~ FR-WO-008 / 架构文档 5.4.1 工单状态机）
 *
 * @author ruoyi
 */
@Service
public class WorkOrderWorkflowService
{
    @Autowired
    private WoProductionOrderMapper orderMapper;

    @Autowired
    private WoOperationTaskMapper operationTaskMapper;

    @Autowired
    private MdRouteMapper routeMapper;

    @Autowired
    private MdRouteOperationMapper routeOperationMapper;

    @Autowired
    private MtMaterialConsumptionMapper consumptionMapper;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MesQueryMapper queryMapper;

    @Autowired
    private DomainEventService domainEventService;

    @Autowired
    private MessageService messageService;

    /**
     * 工单审核（FR-WO-002）
     */
    @Transactional
    public WoProductionOrder audit(Long orderId, String operator)
    {
        WoProductionOrder order = load(orderId);
        requireStatus(order, WorkOrderStatus.CREATED);
        order.setStatus(WorkOrderStatus.AUDITED.name());
        order.setUpdateBy(operator);
        orderMapper.updateWoProductionOrder(order);
        return order;
    }

    /**
     * 工单下达：校验工艺发布、生成工序任务、计算物料需求（FR-WO-003 / FR-EX-001）
     */
    @Transactional
    public WoProductionOrder release(Long orderId, String operator, String traceId)
    {
        WoProductionOrder order = load(orderId);
        if (!WorkOrderStatus.AUDITED.name().equals(order.getStatus())
                && !WorkOrderStatus.SCHEDULED.name().equals(order.getStatus()))
        {
            throw new ServiceException("仅已审核或已排产工单允许下达，当前状态：" + order.getStatus());
        }
        if (order.getRouteVersionId() == null)
        {
            throw new ServiceException("工单未固化工艺版本，不允许下达");
        }
        MdRoute route = routeMapper.selectMdRouteById(order.getRouteVersionId());
        if (route == null || !DocumentStatus.PUBLISHED.name().equals(route.getDocStatus()))
        {
            throw new ServiceException("工艺未发布，不允许下达");
        }
        // 生成工序任务
        MdRouteOperation opQuery = new MdRouteOperation();
        opQuery.setRouteId(route.getId());
        List<MdRouteOperation> operations = routeOperationMapper.selectMdRouteOperationList(opQuery);
        if (operations.isEmpty())
        {
            throw new ServiceException("工艺路线未配置工序，不允许下达");
        }
        // 避免重复生成
        WoOperationTask existsQuery = new WoOperationTask();
        existsQuery.setProductionOrderId(order.getId());
        boolean exists = !operationTaskMapper.selectWoOperationTaskList(existsQuery).isEmpty();
        if (!exists)
        {
            for (MdRouteOperation op : operations)
            {
                WoOperationTask task = new WoOperationTask();
                task.setProductionOrderId(order.getId());
                task.setOrderNo(order.getOrderNo());
                task.setOperationCode(op.getOperationCode());
                task.setOperationName(op.getOperationName());
                task.setSequenceNo(op.getSequenceNo());
                task.setWorkCenterId(op.getWorkCenterId());
                task.setRequiredEquipmentType(op.getEquipmentType());
                task.setPlannedQty(order.getPlannedQty());
                task.setCompletedQty(BigDecimal.ZERO);
                task.setQualifiedQty(BigDecimal.ZERO);
                task.setUnqualifiedQty(BigDecimal.ZERO);
                task.setScrapQty(BigDecimal.ZERO);
                task.setForceInspect(op.getForceInspect());
                task.setStatus(OperationStatus.NOT_STARTED.name());
                task.setCreateBy(operator);
                operationTaskMapper.insertWoOperationTask(task);
            }
        }
        // 物料需求计算
        materialService.calcRequirement(order);
        order.setStatus(WorkOrderStatus.RELEASED.name());
        order.setUpdateBy(operator);
        orderMapper.updateWoProductionOrder(order);
        domainEventService.publish("WorkOrderReleased", "WoProductionOrder", order.getId(),
                "{\"orderNo\":\"" + order.getOrderNo() + "\"}", traceId);
        messageService.recordOutbound("WorkOrderStatus", "ERP", order.getOrderNo() + "-RELEASED",
                "{\"mesWorkOrderNo\":\"" + order.getOrderNo() + "\",\"status\":\"RELEASED\"}");
        return order;
    }

    /**
     * 工单暂停（FR-WO-004）
     */
    @Transactional
    public WoProductionOrder pause(Long orderId, String reason, String operator)
    {
        WoProductionOrder order = load(orderId);
        if (WorkOrderStatus.CLOSED.name().equals(order.getStatus())
                || WorkOrderStatus.COMPLETED.name().equals(order.getStatus()))
        {
            throw new ServiceException("已完工或已关闭工单不允许暂停");
        }
        order.setStatus(WorkOrderStatus.PAUSED.name());
        order.setPauseReason(reason);
        order.setUpdateBy(operator);
        orderMapper.updateWoProductionOrder(order);
        return order;
    }

    /**
     * 工单恢复
     */
    @Transactional
    public WoProductionOrder resume(Long orderId, String operator)
    {
        WoProductionOrder order = load(orderId);
        requireStatus(order, WorkOrderStatus.PAUSED);
        order.setStatus(WorkOrderStatus.EXECUTING.name());
        order.setPauseReason(null);
        order.setUpdateBy(operator);
        orderMapper.updateWoProductionOrder(order);
        return order;
    }

    /**
     * 工单完工（FR-WO-006）：存在未关闭不合格品或未完成工序不允许完工
     */
    @Transactional
    public WoProductionOrder complete(Long orderId, String operator, String traceId)
    {
        WoProductionOrder order = load(orderId);
        if (WorkOrderStatus.COMPLETED.name().equals(order.getStatus())
                || WorkOrderStatus.CLOSED.name().equals(order.getStatus()))
        {
            throw new ServiceException("工单已完工或已关闭");
        }
        if (queryMapper.countOpenNonconformity(orderId) > 0)
        {
            throw new ServiceException("存在未关闭的不合格品，不允许完工");
        }
        if (queryMapper.countUnfinishedOperation(orderId) > 0)
        {
            throw new ServiceException("存在未完成工序，不允许完工");
        }
        order.setStatus(WorkOrderStatus.COMPLETED.name());
        order.setActualEndTime(new Date());
        order.setUpdateBy(operator);
        orderMapper.updateWoProductionOrder(order);
        domainEventService.publish("WorkOrderCompleted", "WoProductionOrder", order.getId(),
                "{\"orderNo\":\"" + order.getOrderNo() + "\"}", traceId);
        messageService.recordOutbound("ProductionCompletion", "ERP", order.getOrderNo() + "-COMPLETED",
                "{\"mesWorkOrderNo\":\"" + order.getOrderNo() + "\",\"completedQty\":" + order.getCompletedQty() + "}");
        return order;
    }

    /**
     * 工单关闭（FR-WO-006）：关键物料消耗未确认不允许关闭
     */
    @Transactional
    public WoProductionOrder close(Long orderId, String operator)
    {
        WoProductionOrder order = load(orderId);
        requireStatus(order, WorkOrderStatus.COMPLETED);
        MtMaterialConsumption query = new MtMaterialConsumption();
        query.setProductionOrderId(orderId);
        query.setSyncStatus(SyncStatus.PENDING.name());
        if (!consumptionMapper.selectMtMaterialConsumptionList(query).isEmpty())
        {
            throw new ServiceException("存在未确认的关键物料消耗，不允许关闭工单");
        }
        order.setStatus(WorkOrderStatus.CLOSED.name());
        order.setUpdateBy(operator);
        orderMapper.updateWoProductionOrder(order);
        return order;
    }

    /**
     * 工单拆分（FR-PS-004）：拆分数量之和必须等于原计划数量
     */
    @Transactional
    public List<WoProductionOrder> split(Long orderId, List<BigDecimal> quantities, String reason, String operator)
    {
        WoProductionOrder parent = load(orderId);
        if (!WorkOrderStatus.CREATED.name().equals(parent.getStatus())
                && !WorkOrderStatus.AUDITED.name().equals(parent.getStatus()))
        {
            throw new ServiceException("已开工工单不允许拆分");
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal q : quantities)
        {
            sum = sum.add(q == null ? BigDecimal.ZERO : q);
        }
        if (sum.compareTo(parent.getPlannedQty() == null ? BigDecimal.ZERO : parent.getPlannedQty()) != 0)
        {
            throw new ServiceException("拆分数量之和必须等于原计划数量");
        }
        java.util.List<WoProductionOrder> children = new java.util.ArrayList<>();
        for (BigDecimal q : quantities)
        {
            WoProductionOrder child = new WoProductionOrder();
            child.setOrderNo(MesSeqUtils.generate(MesConstants.PREFIX_WORK_ORDER));
            child.setSourceOrderNo(parent.getSourceOrderNo());
            child.setDemandId(parent.getDemandId());
            child.setParentOrderId(parent.getId());
            child.setSplitReason(reason);
            child.setProductId(parent.getProductId());
            child.setProductCode(parent.getProductCode());
            child.setProductVersionId(parent.getProductVersionId());
            child.setRouteVersionId(parent.getRouteVersionId());
            child.setBomVersionId(parent.getBomVersionId());
            child.setInspectionPlanVersionId(parent.getInspectionPlanVersionId());
            child.setLineId(parent.getLineId());
            child.setPriority(parent.getPriority());
            child.setPlannedQty(q);
            child.setCompletedQty(BigDecimal.ZERO);
            child.setQualifiedQty(BigDecimal.ZERO);
            child.setScrapQty(BigDecimal.ZERO);
            child.setKitStatus(parent.getKitStatus());
            child.setPlannedStartTime(parent.getPlannedStartTime());
            child.setPlannedEndTime(parent.getPlannedEndTime());
            child.setStatus(WorkOrderStatus.CREATED.name());
            child.setCreateBy(operator);
            orderMapper.insertWoProductionOrder(child);
            children.add(child);
        }
        return children;
    }

    /**
     * 查询工序任务
     */
    public List<WoOperationTask> listOperationTask(Long orderId)
    {
        WoOperationTask query = new WoOperationTask();
        query.setProductionOrderId(orderId);
        return operationTaskMapper.selectWoOperationTaskList(query);
    }

    /**
     * 查询工单
     */
    public WoProductionOrder getOrder(Long orderId)
    {
        return orderMapper.selectWoProductionOrderById(orderId);
    }

    private WoProductionOrder load(Long orderId)
    {
        WoProductionOrder order = orderMapper.selectWoProductionOrderById(orderId);
        if (order == null)
        {
            throw new ServiceException("工单不存在：" + orderId);
        }
        return order;
    }

    private void requireStatus(WoProductionOrder order, WorkOrderStatus expected)
    {
        if (!expected.name().equals(order.getStatus()))
        {
            throw new ServiceException("工单状态不允许该操作，期望：" + expected.getDesc() + "，当前：" + order.getStatus());
        }
    }
}
