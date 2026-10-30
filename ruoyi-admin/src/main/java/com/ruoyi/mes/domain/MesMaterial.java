package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料主数据对象 mes_material
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物料ID */
    @Excel(name = "物料ID")
    private Long materialId;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 规格 */
    @Excel(name = "规格")
    private String spec;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 分类 */
    @Excel(name = "分类")
    private String category;

    /** 产品标识 */
    @Excel(name = "产品标识")
    private String productFlag;

    /** 安全库存 */
    @Excel(name = "安全库存")
    private BigDecimal safetyStock;

    /** 最高库存 */
    @Excel(name = "最高库存")
    private BigDecimal maxStock;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setMaterialCode(String materialCode)
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode()
    {
        return materialCode;
    }

    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public String getMaterialName()
    {
        return materialName;
    }

    public void setSpec(String spec)
    {
        this.spec = spec;
    }

    public String getSpec()
    {
        return spec;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public void setProductFlag(String productFlag)
    {
        this.productFlag = productFlag;
    }

    public String getProductFlag()
    {
        return productFlag;
    }

    public void setSafetyStock(BigDecimal safetyStock)
    {
        this.safetyStock = safetyStock;
    }

    public BigDecimal getSafetyStock()
    {
        return safetyStock;
    }

    public void setMaxStock(BigDecimal maxStock)
    {
        this.maxStock = maxStock;
    }

    public BigDecimal getMaxStock()
    {
        return maxStock;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
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
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("spec", getSpec())
            .append("unit", getUnit())
            .append("category", getCategory())
            .append("productFlag", getProductFlag())
            .append("safetyStock", getSafetyStock())
            .append("maxStock", getMaxStock())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
