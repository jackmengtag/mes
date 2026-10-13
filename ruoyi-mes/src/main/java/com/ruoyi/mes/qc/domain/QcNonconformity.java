package com.ruoyi.mes.qc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 不合格品记录对象 qc_nonconformity
 *
 * @author ruoyi
 */
public class QcNonconformity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 不合格单号 */
    @Excel(name = "不合格单号")
    private String nonconformityNo;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 工序任务ID */
    @Excel(name = "工序任务ID", cellType = ColumnType.NUMERIC)
    private Long operationTaskId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 产品序列号 */
    @Excel(name = "产品序列号")
    private String serialNo;

    /** 缺陷代码 */
    @Excel(name = "缺陷代码")
    private String defectCode;

    /** 缺陷描述 */
    @Excel(name = "缺陷描述")
    private String defectDesc;

    /** 发现来源 */
    @Excel(name = "发现来源")
    private String discoverySource;

    /** 发现单单据 */
    @Excel(name = "发现单单据")
    private String discoveryNo;

    /** 不合格数量 */
    @Excel(name = "不合格数量", cellType = ColumnType.NUMERIC)
    private BigDecimal qty;

    /** 处置方式 */
    @Excel(name = "处置方式")
    private String disposalType;

    /** 判定理由 */
    @Excel(name = "判定理由")
    private String dispositionReason;

    /** 判定人 */
    @Excel(name = "判定人")
    private String dispositionBy;

    /** 判定时间 */
    @Excel(name = "判定时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dispositionTime;

    /** 授权人 */
    @Excel(name = "授权人")
    private String authorizedBy;

    /** 复检结果 */
    @Excel(name = "复检结果")
    private String reinspectResult;

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

    public void setNonconformityNo(String nonconformityNo)
    {
        this.nonconformityNo = nonconformityNo;
    }

    public String getNonconformityNo()
    {
        return nonconformityNo;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOperationTaskId(Long operationTaskId)
    {
        this.operationTaskId = operationTaskId;
    }

    public Long getOperationTaskId()
    {
        return operationTaskId;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setDefectCode(String defectCode)
    {
        this.defectCode = defectCode;
    }

    public String getDefectCode()
    {
        return defectCode;
    }

    public void setDefectDesc(String defectDesc)
    {
        this.defectDesc = defectDesc;
    }

    public String getDefectDesc()
    {
        return defectDesc;
    }

    public void setDiscoverySource(String discoverySource)
    {
        this.discoverySource = discoverySource;
    }

    public String getDiscoverySource()
    {
        return discoverySource;
    }

    public void setDiscoveryNo(String discoveryNo)
    {
        this.discoveryNo = discoveryNo;
    }

    public String getDiscoveryNo()
    {
        return discoveryNo;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setDisposalType(String disposalType)
    {
        this.disposalType = disposalType;
    }

    public String getDisposalType()
    {
        return disposalType;
    }

    public void setDispositionReason(String dispositionReason)
    {
        this.dispositionReason = dispositionReason;
    }

    public String getDispositionReason()
    {
        return dispositionReason;
    }

    public void setDispositionBy(String dispositionBy)
    {
        this.dispositionBy = dispositionBy;
    }

    public String getDispositionBy()
    {
        return dispositionBy;
    }

    public void setDispositionTime(Date dispositionTime)
    {
        this.dispositionTime = dispositionTime;
    }

    public Date getDispositionTime()
    {
        return dispositionTime;
    }

    public void setAuthorizedBy(String authorizedBy)
    {
        this.authorizedBy = authorizedBy;
    }

    public String getAuthorizedBy()
    {
        return authorizedBy;
    }

    public void setReinspectResult(String reinspectResult)
    {
        this.reinspectResult = reinspectResult;
    }

    public String getReinspectResult()
    {
        return reinspectResult;
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
            .append("nonconformityNo", getNonconformityNo())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("operationTaskId", getOperationTaskId())
            .append("operationCode", getOperationCode())
            .append("serialNo", getSerialNo())
            .append("defectCode", getDefectCode())
            .append("defectDesc", getDefectDesc())
            .append("discoverySource", getDiscoverySource())
            .append("discoveryNo", getDiscoveryNo())
            .append("qty", getQty())
            .append("disposalType", getDisposalType())
            .append("dispositionReason", getDispositionReason())
            .append("dispositionBy", getDispositionBy())
            .append("dispositionTime", getDispositionTime())
            .append("authorizedBy", getAuthorizedBy())
            .append("reinspectResult", getReinspectResult())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
