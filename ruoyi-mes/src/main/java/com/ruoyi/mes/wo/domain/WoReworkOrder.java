package com.ruoyi.mes.wo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 返工工单对象 wo_rework_order
 *
 * @author ruoyi
 */
public class WoReworkOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 返工单号 */
    @Excel(name = "返工单号")
    private String reworkNo;

    /** 原工单ID */
    @Excel(name = "原工单ID", cellType = ColumnType.NUMERIC)
    private Long originOrderId;

    /** 原工单号 */
    @Excel(name = "原工单号")
    private String originOrderNo;

    /** 不合格记录ID */
    @Excel(name = "不合格记录ID", cellType = ColumnType.NUMERIC)
    private Long nonconformityId;

    /** 产品序列号 */
    @Excel(name = "产品序列号")
    private String serialNo;

    /** 返工工序 */
    @Excel(name = "返工工序")
    private String operationCode;

    /** 返工数量 */
    @Excel(name = "返工数量", cellType = ColumnType.NUMERIC)
    private BigDecimal reworkQty;

    /** 返工原因 */
    @Excel(name = "返工原因")
    private String reason;

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

    public void setReworkNo(String reworkNo)
    {
        this.reworkNo = reworkNo;
    }

    public String getReworkNo()
    {
        return reworkNo;
    }

    public void setOriginOrderId(Long originOrderId)
    {
        this.originOrderId = originOrderId;
    }

    public Long getOriginOrderId()
    {
        return originOrderId;
    }

    public void setOriginOrderNo(String originOrderNo)
    {
        this.originOrderNo = originOrderNo;
    }

    public String getOriginOrderNo()
    {
        return originOrderNo;
    }

    public void setNonconformityId(Long nonconformityId)
    {
        this.nonconformityId = nonconformityId;
    }

    public Long getNonconformityId()
    {
        return nonconformityId;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setReworkQty(BigDecimal reworkQty)
    {
        this.reworkQty = reworkQty;
    }

    public BigDecimal getReworkQty()
    {
        return reworkQty;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
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
            .append("reworkNo", getReworkNo())
            .append("originOrderId", getOriginOrderId())
            .append("originOrderNo", getOriginOrderNo())
            .append("nonconformityId", getNonconformityId())
            .append("serialNo", getSerialNo())
            .append("operationCode", getOperationCode())
            .append("reworkQty", getReworkQty())
            .append("reason", getReason())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
