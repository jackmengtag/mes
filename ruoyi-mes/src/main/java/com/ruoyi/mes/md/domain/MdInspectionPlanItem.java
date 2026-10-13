package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 检验方案明细对象 md_inspection_plan_item
 *
 * @author ruoyi
 */
public class MdInspectionPlanItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 方案ID */
    @Excel(name = "方案ID", cellType = ColumnType.NUMERIC)
    private Long planId;

    /** 检验项目ID */
    @Excel(name = "检验项目ID", cellType = ColumnType.NUMERIC)
    private Long itemId;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String itemCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String itemName;

    /** 下限 */
    @Excel(name = "下限", cellType = ColumnType.NUMERIC)
    private BigDecimal lowerLimit;

    /** 上限 */
    @Excel(name = "上限", cellType = ColumnType.NUMERIC)
    private BigDecimal upperLimit;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 抽样规则 */
    @Excel(name = "抽样规则")
    private String samplingRule;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }

    public void setItemId(Long itemId)
    {
        this.itemId = itemId;
    }

    public Long getItemId()
    {
        return itemId;
    }

    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public String getItemCode()
    {
        return itemCode;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setLowerLimit(BigDecimal lowerLimit)
    {
        this.lowerLimit = lowerLimit;
    }

    public BigDecimal getLowerLimit()
    {
        return lowerLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit)
    {
        this.upperLimit = upperLimit;
    }

    public BigDecimal getUpperLimit()
    {
        return upperLimit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setSamplingRule(String samplingRule)
    {
        this.samplingRule = samplingRule;
    }

    public String getSamplingRule()
    {
        return samplingRule;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planId", getPlanId())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("lowerLimit", getLowerLimit())
            .append("upperLimit", getUpperLimit())
            .append("unit", getUnit())
            .append("samplingRule", getSamplingRule())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
