package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售出库对象 mes_shipment
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesShipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出库ID */
    @Excel(name = "出库ID")
    private Long shipmentId;

    /** 出库单号 */
    @Excel(name = "出库单号")
    private String shipmentCode;

    /** 销售订单号 */
    @Excel(name = "销售订单号")
    private String orderCode;

    /** 客户 */
    @Excel(name = "客户")
    private String customerName;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 出库数量 */
    @Excel(name = "出库数量")
    private BigDecimal qty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 出库仓库 */
    @Excel(name = "出库仓库")
    private String warehouseCode;

    /** 出库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shipDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setShipmentId(Long shipmentId)
    {
        this.shipmentId = shipmentId;
    }

    public Long getShipmentId()
    {
        return shipmentId;
    }

    public void setShipmentCode(String shipmentCode)
    {
        this.shipmentCode = shipmentCode;
    }

    public String getShipmentCode()
    {
        return shipmentCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerName()
    {
        return customerName;
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

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
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

    public void setWarehouseCode(String warehouseCode)
    {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseCode()
    {
        return warehouseCode;
    }

    public void setShipDate(Date shipDate)
    {
        this.shipDate = shipDate;
    }

    public Date getShipDate()
    {
        return shipDate;
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
            .append("shipmentId", getShipmentId())
            .append("shipmentCode", getShipmentCode())
            .append("orderCode", getOrderCode())
            .append("customerName", getCustomerName())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("qty", getQty())
            .append("price", getPrice())
            .append("amount", getAmount())
            .append("warehouseCode", getWarehouseCode())
            .append("shipDate", getShipDate())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
