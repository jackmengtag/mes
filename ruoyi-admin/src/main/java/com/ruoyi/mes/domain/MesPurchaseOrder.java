package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单对象 mes_purchase_order
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesPurchaseOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购ID */
    @Excel(name = "采购ID")
    private Long purchaseId;

    /** 采购订单号 */
    @Excel(name = "采购订单号")
    private String orderCode;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 订购数量 */
    @Excel(name = "订购数量")
    private BigDecimal qty;

    /** 已到货数量 */
    @Excel(name = "已到货数量")
    private BigDecimal receivedQty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 下单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 期望到货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "期望到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expectDate;

    public void setPurchaseId(Long purchaseId)
    {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId()
    {
        return purchaseId;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
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

    public void setReceivedQty(BigDecimal receivedQty)
    {
        this.receivedQty = receivedQty;
    }

    public BigDecimal getReceivedQty()
    {
        return receivedQty;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setExpectDate(Date expectDate)
    {
        this.expectDate = expectDate;
    }

    public Date getExpectDate()
    {
        return expectDate;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("purchaseId", getPurchaseId())
            .append("orderCode", getOrderCode())
            .append("supplierCode", getSupplierCode())
            .append("supplierName", getSupplierName())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("qty", getQty())
            .append("receivedQty", getReceivedQty())
            .append("price", getPrice())
            .append("amount", getAmount())
            .append("status", getStatus())
            .append("orderDate", getOrderDate())
            .append("expectDate", getExpectDate())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
