package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 产品版本对象 md_product_version
 *
 * @author ruoyi
 */
public class MdProductVersion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 产品ID */
    @Excel(name = "产品ID", cellType = ColumnType.NUMERIC)
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionNo;

    /** BOM版本 */
    @Excel(name = "BOM版本", cellType = ColumnType.NUMERIC)
    private Long bomVersionId;

    /** 工艺版本 */
    @Excel(name = "工艺版本", cellType = ColumnType.NUMERIC)
    private Long routeVersionId;

    /** 生效时间 */
    @Excel(name = "生效时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date effectiveTime;

    /** 失效时间 */
    @Excel(name = "失效时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

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

    public void setBomVersionId(Long bomVersionId)
    {
        this.bomVersionId = bomVersionId;
    }

    public Long getBomVersionId()
    {
        return bomVersionId;
    }

    public void setRouteVersionId(Long routeVersionId)
    {
        this.routeVersionId = routeVersionId;
    }

    public Long getRouteVersionId()
    {
        return routeVersionId;
    }

    public void setEffectiveTime(Date effectiveTime)
    {
        this.effectiveTime = effectiveTime;
    }

    public Date getEffectiveTime()
    {
        return effectiveTime;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    public Date getExpireTime()
    {
        return expireTime;
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
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("versionNo", getVersionNo())
            .append("bomVersionId", getBomVersionId())
            .append("routeVersionId", getRouteVersionId())
            .append("effectiveTime", getEffectiveTime())
            .append("expireTime", getExpireTime())
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
