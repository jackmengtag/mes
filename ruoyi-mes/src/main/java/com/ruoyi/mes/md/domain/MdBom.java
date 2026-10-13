package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * BOM主数据对象 md_bom
 *
 * @author ruoyi
 */
public class MdBom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** BOM编码 */
    @Excel(name = "BOM编码")
    private String bomCode;

    /** 产品ID */
    @Excel(name = "产品ID", cellType = ColumnType.NUMERIC)
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionNo;

    /** 版本状态 */
    @Excel(name = "版本状态")
    private String docStatus;

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

    public void setBomCode(String bomCode)
    {
        this.bomCode = bomCode;
    }

    public String getBomCode()
    {
        return bomCode;
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

    public void setVersionNo(String versionNo)
    {
        this.versionNo = versionNo;
    }

    public String getVersionNo()
    {
        return versionNo;
    }

    public void setDocStatus(String docStatus)
    {
        this.docStatus = docStatus;
    }

    public String getDocStatus()
    {
        return docStatus;
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
            .append("bomCode", getBomCode())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("versionNo", getVersionNo())
            .append("docStatus", getDocStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
