package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应链协同对象 mes_collab
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesCollab extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 协同ID */
    @Excel(name = "协同ID")
    private Long collabId;

    /** 协同单号 */
    @Excel(name = "协同单号")
    private String collabCode;

    /** 协同类型 */
    @Excel(name = "协同类型")
    private String collabType;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal qty;

    /** 计划日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planDate;

    /** 实际日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 处理人 */
    @Excel(name = "处理人")
    private String handler;

    public void setCollabId(Long collabId)
    {
        this.collabId = collabId;
    }

    public Long getCollabId()
    {
        return collabId;
    }

    public void setCollabCode(String collabCode)
    {
        this.collabCode = collabCode;
    }

    public String getCollabCode()
    {
        return collabCode;
    }

    public void setCollabType(String collabType)
    {
        this.collabType = collabType;
    }

    public String getCollabType()
    {
        return collabType;
    }

    public void setSupplierCode(String supplierCode)
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode()
    {
        return supplierCode;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName()
    {
        return supplierName;
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

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setPlanDate(Date planDate)
    {
        this.planDate = planDate;
    }

    public Date getPlanDate()
    {
        return planDate;
    }

    public void setActualDate(Date actualDate)
    {
        this.actualDate = actualDate;
    }

    public Date getActualDate()
    {
        return actualDate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setHandler(String handler)
    {
        this.handler = handler;
    }

    public String getHandler()
    {
        return handler;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("collabId", getCollabId())
            .append("collabCode", getCollabCode())
            .append("collabType", getCollabType())
            .append("supplierCode", getSupplierCode())
            .append("supplierName", getSupplierName())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("qty", getQty())
            .append("planDate", getPlanDate())
            .append("actualDate", getActualDate())
            .append("status", getStatus())
            .append("content", getContent())
            .append("handler", getHandler())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
