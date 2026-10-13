package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品主数据对象 md_product
 *
 * @author ruoyi
 */
public class MdProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String productType;

    /** 序列号管理标识 */
    @Excel(name = "序列号管理标识")
    private String serialFlag;

    /** 默认BOM版本 */
    @Excel(name = "默认BOM版本", cellType = ColumnType.NUMERIC)
    private Long defaultBomVersionId;

    /** 默认工艺版本 */
    @Excel(name = "默认工艺版本", cellType = ColumnType.NUMERIC)
    private Long defaultRouteVersionId;

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

    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setSerialFlag(String serialFlag)
    {
        this.serialFlag = serialFlag;
    }

    public String getSerialFlag()
    {
        return serialFlag;
    }

    public void setDefaultBomVersionId(Long defaultBomVersionId)
    {
        this.defaultBomVersionId = defaultBomVersionId;
    }

    public Long getDefaultBomVersionId()
    {
        return defaultBomVersionId;
    }

    public void setDefaultRouteVersionId(Long defaultRouteVersionId)
    {
        this.defaultRouteVersionId = defaultRouteVersionId;
    }

    public Long getDefaultRouteVersionId()
    {
        return defaultRouteVersionId;
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
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("productType", getProductType())
            .append("serialFlag", getSerialFlag())
            .append("defaultBomVersionId", getDefaultBomVersionId())
            .append("defaultRouteVersionId", getDefaultRouteVersionId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
