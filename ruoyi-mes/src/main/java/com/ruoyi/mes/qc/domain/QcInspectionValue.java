package com.ruoyi.mes.qc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 检验项目值对象 qc_inspection_value
 *
 * @author ruoyi
 */
public class QcInspectionValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 检验单ID */
    @Excel(name = "检验单ID", cellType = ColumnType.NUMERIC)
    private Long inspectionId;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String itemCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String itemName;

    /** 检验值 */
    @Excel(name = "检验值")
    private String itemValue;

    /** 下限 */
    @Excel(name = "下限", cellType = ColumnType.NUMERIC)
    private BigDecimal lowerLimit;

    /** 上限 */
    @Excel(name = "上限", cellType = ColumnType.NUMERIC)
    private BigDecimal upperLimit;

    /** 判定结果 */
    @Excel(name = "判定结果")
    private String result;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setInspectionId(Long inspectionId)
    {
        this.inspectionId = inspectionId;
    }

    public Long getInspectionId()
    {
        return inspectionId;
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

    public void setItemValue(String itemValue)
    {
        this.itemValue = itemValue;
    }

    public String getItemValue()
    {
        return itemValue;
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

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("inspectionId", getInspectionId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("itemValue", getItemValue())
            .append("lowerLimit", getLowerLimit())
            .append("upperLimit", getUpperLimit())
            .append("result", getResult())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
