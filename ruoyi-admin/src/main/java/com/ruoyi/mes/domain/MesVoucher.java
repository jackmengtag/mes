package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存货核算凭证对象 mes_voucher
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesVoucher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 凭证ID */
    @Excel(name = "凭证ID")
    private Long voucherId;

    /** 凭证号 */
    @Excel(name = "凭证号")
    private String voucherCode;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String bizType;

    /** 来源单号 */
    @Excel(name = "来源单号")
    private String bizCode;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal qty;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 会计期间 */
    @Excel(name = "会计期间")
    private String accountPeriod;

    /** 记账状态 */
    @Excel(name = "记账状态")
    private String status;

    /** U8C状态 */
    @Excel(name = "U8C状态")
    private String u8cStatus;

    /** 同步时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "同步时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date syncTime;

    /** 同步信息 */
    @Excel(name = "同步信息")
    private String message;

    public void setVoucherId(Long voucherId)
    {
        this.voucherId = voucherId;
    }

    public Long getVoucherId()
    {
        return voucherId;
    }

    public void setVoucherCode(String voucherCode)
    {
        this.voucherCode = voucherCode;
    }

    public String getVoucherCode()
    {
        return voucherCode;
    }

    public void setBizType(String bizType)
    {
        this.bizType = bizType;
    }

    public String getBizType()
    {
        return bizType;
    }

    public void setBizCode(String bizCode)
    {
        this.bizCode = bizCode;
    }

    public String getBizCode()
    {
        return bizCode;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAccountPeriod(String accountPeriod)
    {
        this.accountPeriod = accountPeriod;
    }

    public String getAccountPeriod()
    {
        return accountPeriod;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setU8cStatus(String u8cStatus)
    {
        this.u8cStatus = u8cStatus;
    }

    public String getU8cStatus()
    {
        return u8cStatus;
    }

    public void setSyncTime(Date syncTime)
    {
        this.syncTime = syncTime;
    }

    public Date getSyncTime()
    {
        return syncTime;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("voucherId", getVoucherId())
            .append("voucherCode", getVoucherCode())
            .append("bizType", getBizType())
            .append("bizCode", getBizCode())
            .append("qty", getQty())
            .append("amount", getAmount())
            .append("accountPeriod", getAccountPeriod())
            .append("status", getStatus())
            .append("u8cStatus", getU8cStatus())
            .append("syncTime", getSyncTime())
            .append("message", getMessage())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
