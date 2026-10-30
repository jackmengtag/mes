package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产计划对象 mes_plan
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    @Excel(name = "计划ID")
    private Long planId;

    /** 计划单号 */
    @Excel(name = "计划单号")
    private String planCode;

    /** 计划类型 */
    @Excel(name = "计划类型")
    private String planType;

    /** 计划月份 */
    @Excel(name = "计划月份")
    private String planMonth;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 计划数量 */
    @Excel(name = "计划数量")
    private BigDecimal qty;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 产线 */
    @Excel(name = "产线")
    private String lineCode;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 来源单号 */
    @Excel(name = "来源单号")
    private String sourceCode;

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }

    public void setPlanCode(String planCode)
    {
        this.planCode = planCode;
    }

    public String getPlanCode()
    {
        return planCode;
    }

    public void setPlanType(String planType)
    {
        this.planType = planType;
    }

    public String getPlanType()
    {
        return planType;
    }

    public void setPlanMonth(String planMonth)
    {
        this.planMonth = planMonth;
    }

    public String getPlanMonth()
    {
        return planMonth;
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

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setSourceCode(String sourceCode)
    {
        this.sourceCode = sourceCode;
    }

    public String getSourceCode()
    {
        return sourceCode;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("planId", getPlanId())
            .append("planCode", getPlanCode())
            .append("planType", getPlanType())
            .append("planMonth", getPlanMonth())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("qty", getQty())
            .append("unit", getUnit())
            .append("lineCode", getLineCode())
            .append("status", getStatus())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("sourceCode", getSourceCode())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
