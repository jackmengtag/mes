package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产工单对象 mes_work_order
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesWorkOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long workOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderCode;

    /** 来源计划号 */
    @Excel(name = "来源计划号")
    private String planCode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private BigDecimal qty;

    /** 完成数量 */
    @Excel(name = "完成数量")
    private BigDecimal completedQty;

    /** 不良数量 */
    @Excel(name = "不良数量")
    private BigDecimal ngQty;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 产线 */
    @Excel(name = "产线")
    private String lineCode;

    /** 当前工位 */
    @Excel(name = "当前工位")
    private String station;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    /** 计划开始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStart;

    /** 计划结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEnd;

    /** 关联订单 */
    @Excel(name = "关联订单")
    private String sourceOrder;

    public void setWorkOrderId(Long workOrderId)
    {
        this.workOrderId = workOrderId;
    }

    public Long getWorkOrderId()
    {
        return workOrderId;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setPlanCode(String planCode)
    {
        this.planCode = planCode;
    }

    public String getPlanCode()
    {
        return planCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setCompletedQty(BigDecimal completedQty)
    {
        this.completedQty = completedQty;
    }

    public BigDecimal getCompletedQty()
    {
        return completedQty;
    }

    public void setNgQty(BigDecimal ngQty)
    {
        this.ngQty = ngQty;
    }

    public BigDecimal getNgQty()
    {
        return ngQty;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setLineCode(String lineCode)
    {
        this.lineCode = lineCode;
    }

    public String getLineCode()
    {
        return lineCode;
    }

    public void setStation(String station)
    {
        this.station = station;
    }

    public String getStation()
    {
        return station;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPlanStart(Date planStart)
    {
        this.planStart = planStart;
    }

    public Date getPlanStart()
    {
        return planStart;
    }

    public void setPlanEnd(Date planEnd)
    {
        this.planEnd = planEnd;
    }

    public Date getPlanEnd()
    {
        return planEnd;
    }

    public void setSourceOrder(String sourceOrder)
    {
        this.sourceOrder = sourceOrder;
    }

    public String getSourceOrder()
    {
        return sourceOrder;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("workOrderId", getWorkOrderId())
            .append("orderCode", getOrderCode())
            .append("planCode", getPlanCode())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("qty", getQty())
            .append("completedQty", getCompletedQty())
            .append("ngQty", getNgQty())
            .append("unit", getUnit())
            .append("lineCode", getLineCode())
            .append("station", getStation())
            .append("status", getStatus())
            .append("priority", getPriority())
            .append("planStart", getPlanStart())
            .append("planEnd", getPlanEnd())
            .append("sourceOrder", getSourceOrder())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
