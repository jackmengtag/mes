package com.ruoyi.mes.ps.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 生产计划对象 ps_plan
 *
 * @author ruoyi
 */
public class PsPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 计划单号 */
    @Excel(name = "计划单号")
    private String planNo;

    /** 需求ID */
    @Excel(name = "需求ID", cellType = ColumnType.NUMERIC)
    private Long demandId;

    /** 需求单号 */
    @Excel(name = "需求单号")
    private String demandNo;

    /** 产品ID */
    @Excel(name = "产品ID", cellType = ColumnType.NUMERIC)
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 计划数量 */
    @Excel(name = "计划数量", cellType = ColumnType.NUMERIC)
    private BigDecimal planQty;

    /** 计划开始 */
    @Excel(name = "计划开始", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date plannedStartTime;

    /** 计划结束 */
    @Excel(name = "计划结束", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date plannedEndTime;

    /** 产线 */
    @Excel(name = "产线", cellType = ColumnType.NUMERIC)
    private Long lineId;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setPlanNo(String planNo)
    {
        this.planNo = planNo;
    }

    public String getPlanNo()
    {
        return planNo;
    }

    public void setDemandId(Long demandId)
    {
        this.demandId = demandId;
    }

    public Long getDemandId()
    {
        return demandId;
    }

    public void setDemandNo(String demandNo)
    {
        this.demandNo = demandNo;
    }

    public String getDemandNo()
    {
        return demandNo;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setPlanQty(BigDecimal planQty)
    {
        this.planQty = planQty;
    }

    public BigDecimal getPlanQty()
    {
        return planQty;
    }

    public void setPlannedStartTime(Date plannedStartTime)
    {
        this.plannedStartTime = plannedStartTime;
    }

    public Date getPlannedStartTime()
    {
        return plannedStartTime;
    }

    public void setPlannedEndTime(Date plannedEndTime)
    {
        this.plannedEndTime = plannedEndTime;
    }

    public Date getPlannedEndTime()
    {
        return plannedEndTime;
    }

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getPriority()
    {
        return priority;
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
            .append("planNo", getPlanNo())
            .append("demandId", getDemandId())
            .append("demandNo", getDemandNo())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("planQty", getPlanQty())
            .append("plannedStartTime", getPlannedStartTime())
            .append("plannedEndTime", getPlannedEndTime())
            .append("lineId", getLineId())
            .append("priority", getPriority())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
