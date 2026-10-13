package com.ruoyi.mes.wo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 工序任务对象 wo_operation_task
 *
 * @author ruoyi
 */
public class WoOperationTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 所属工单 */
    @Excel(name = "所属工单", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String operationName;

    /** 工序顺序号 */
    @Excel(name = "工序顺序号", cellType = ColumnType.NUMERIC)
    private Integer sequenceNo;

    /** 工作中心 */
    @Excel(name = "工作中心", cellType = ColumnType.NUMERIC)
    private Long workCenterId;

    /** 设备类型要求 */
    @Excel(name = "设备类型要求")
    private String requiredEquipmentType;

    /** 计划数量 */
    @Excel(name = "计划数量", cellType = ColumnType.NUMERIC)
    private BigDecimal plannedQty;

    /** 完成数量 */
    @Excel(name = "完成数量", cellType = ColumnType.NUMERIC)
    private BigDecimal completedQty;

    /** 合格数量 */
    @Excel(name = "合格数量", cellType = ColumnType.NUMERIC)
    private BigDecimal qualifiedQty;

    /** 不合格数量 */
    @Excel(name = "不合格数量", cellType = ColumnType.NUMERIC)
    private BigDecimal unqualifiedQty;

    /** 报废数量 */
    @Excel(name = "报废数量", cellType = ColumnType.NUMERIC)
    private BigDecimal scrapQty;

    /** 是否强制检验 */
    @Excel(name = "是否强制检验")
    private String forceInspect;

    /** 派工班组 */
    @Excel(name = "派工班组", cellType = ColumnType.NUMERIC)
    private Long dispatchTeamId;

    /** 执行人 */
    @Excel(name = "执行人")
    private String operatorNo;

    /** 工序状态 */
    @Excel(name = "工序状态")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setOperationName(String operationName)
    {
        this.operationName = operationName;
    }

    public String getOperationName()
    {
        return operationName;
    }

    public void setSequenceNo(Integer sequenceNo)
    {
        this.sequenceNo = sequenceNo;
    }

    public Integer getSequenceNo()
    {
        return sequenceNo;
    }

    public void setWorkCenterId(Long workCenterId)
    {
        this.workCenterId = workCenterId;
    }

    public Long getWorkCenterId()
    {
        return workCenterId;
    }

    public void setRequiredEquipmentType(String requiredEquipmentType)
    {
        this.requiredEquipmentType = requiredEquipmentType;
    }

    public String getRequiredEquipmentType()
    {
        return requiredEquipmentType;
    }

    public void setPlannedQty(BigDecimal plannedQty)
    {
        this.plannedQty = plannedQty;
    }

    public BigDecimal getPlannedQty()
    {
        return plannedQty;
    }

    public void setCompletedQty(BigDecimal completedQty)
    {
        this.completedQty = completedQty;
    }

    public BigDecimal getCompletedQty()
    {
        return completedQty;
    }

    public void setQualifiedQty(BigDecimal qualifiedQty)
    {
        this.qualifiedQty = qualifiedQty;
    }

    public BigDecimal getQualifiedQty()
    {
        return qualifiedQty;
    }

    public void setUnqualifiedQty(BigDecimal unqualifiedQty)
    {
        this.unqualifiedQty = unqualifiedQty;
    }

    public BigDecimal getUnqualifiedQty()
    {
        return unqualifiedQty;
    }

    public void setScrapQty(BigDecimal scrapQty)
    {
        this.scrapQty = scrapQty;
    }

    public BigDecimal getScrapQty()
    {
        return scrapQty;
    }

    public void setForceInspect(String forceInspect)
    {
        this.forceInspect = forceInspect;
    }

    public String getForceInspect()
    {
        return forceInspect;
    }

    public void setDispatchTeamId(Long dispatchTeamId)
    {
        this.dispatchTeamId = dispatchTeamId;
    }

    public Long getDispatchTeamId()
    {
        return dispatchTeamId;
    }

    public void setOperatorNo(String operatorNo)
    {
        this.operatorNo = operatorNo;
    }

    public String getOperatorNo()
    {
        return operatorNo;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("operationCode", getOperationCode())
            .append("operationName", getOperationName())
            .append("sequenceNo", getSequenceNo())
            .append("workCenterId", getWorkCenterId())
            .append("requiredEquipmentType", getRequiredEquipmentType())
            .append("plannedQty", getPlannedQty())
            .append("completedQty", getCompletedQty())
            .append("qualifiedQty", getQualifiedQty())
            .append("unqualifiedQty", getUnqualifiedQty())
            .append("scrapQty", getScrapQty())
            .append("forceInspect", getForceInspect())
            .append("dispatchTeamId", getDispatchTeamId())
            .append("operatorNo", getOperatorNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
