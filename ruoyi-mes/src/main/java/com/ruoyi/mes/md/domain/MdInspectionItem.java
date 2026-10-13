package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 检验项目对象 md_inspection_item
 *
 * @author ruoyi
 */
public class MdInspectionItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String itemCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String itemName;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 下限 */
    @Excel(name = "下限", cellType = ColumnType.NUMERIC)
    private BigDecimal lowerLimit;

    /** 上限 */
    @Excel(name = "上限", cellType = ColumnType.NUMERIC)
    private BigDecimal upperLimit;

    /** 标准值 */
    @Excel(name = "标准值", cellType = ColumnType.NUMERIC)
    private BigDecimal standardValue;

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

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
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

    public void setStandardValue(BigDecimal standardValue)
    {
        this.standardValue = standardValue;
    }

    public BigDecimal getStandardValue()
    {
        return standardValue;
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
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("unit", getUnit())
            .append("lowerLimit", getLowerLimit())
            .append("upperLimit", getUpperLimit())
            .append("standardValue", getStandardValue())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
