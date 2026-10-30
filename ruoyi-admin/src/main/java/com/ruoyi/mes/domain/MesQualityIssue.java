package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 质量问题对象 mes_quality_issue
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesQualityIssue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 问题ID */
    @Excel(name = "问题ID")
    private Long issueId;

    /** 问题单号 */
    @Excel(name = "问题单号")
    private String issueCode;

    /** 来源类型 */
    @Excel(name = "来源类型")
    private String sourceType;

    /** 来源单号 */
    @Excel(name = "来源单号")
    private String sourceCode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNo;

    /** 问题类型 */
    @Excel(name = "问题类型")
    private String issueType;

    /** 严重度 */
    @Excel(name = "严重度")
    private String severity;

    /** 不良数量 */
    @Excel(name = "不良数量")
    private BigDecimal qty;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String description;

    /** 处置方式 */
    @Excel(name = "处置方式")
    private String dispose;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 处理人 */
    @Excel(name = "处理人")
    private String handler;

    /** 发现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发现时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date foundTime;

    /** 关闭时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "关闭时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date closeTime;

    public void setIssueId(Long issueId)
    {
        this.issueId = issueId;
    }

    public Long getIssueId()
    {
        return issueId;
    }

    public void setIssueCode(String issueCode)
    {
        this.issueCode = issueCode;
    }

    public String getIssueCode()
    {
        return issueCode;
    }

    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
    }

    public String getSourceType()
    {
        return sourceType;
    }

    public void setSourceCode(String sourceCode)
    {
        this.sourceCode = sourceCode;
    }

    public String getSourceCode()
    {
        return sourceCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setIssueType(String issueType)
    {
        this.issueType = issueType;
    }

    public String getIssueType()
    {
        return issueType;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDispose(String dispose)
    {
        this.dispose = dispose;
    }

    public String getDispose()
    {
        return dispose;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setHandler(String handler)
    {
        this.handler = handler;
    }

    public String getHandler()
    {
        return handler;
    }

    public void setFoundTime(Date foundTime)
    {
        this.foundTime = foundTime;
    }

    public Date getFoundTime()
    {
        return foundTime;
    }

    public void setCloseTime(Date closeTime)
    {
        this.closeTime = closeTime;
    }

    public Date getCloseTime()
    {
        return closeTime;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("issueId", getIssueId())
            .append("issueCode", getIssueCode())
            .append("sourceType", getSourceType())
            .append("sourceCode", getSourceCode())
            .append("productCode", getProductCode())
            .append("batchNo", getBatchNo())
            .append("serialNo", getSerialNo())
            .append("issueType", getIssueType())
            .append("severity", getSeverity())
            .append("qty", getQty())
            .append("description", getDescription())
            .append("dispose", getDispose())
            .append("status", getStatus())
            .append("handler", getHandler())
            .append("foundTime", getFoundTime())
            .append("closeTime", getCloseTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
