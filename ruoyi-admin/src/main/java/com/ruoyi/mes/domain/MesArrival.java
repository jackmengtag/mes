package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购到货对象 mes_arrival
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesArrival extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 到货ID */
    @Excel(name = "到货ID")
    private Long arrivalId;

    /** 到货单号 */
    @Excel(name = "到货单号")
    private String arrivalCode;

    /** 采购订单号 */
    @Excel(name = "采购订单号")
    private String orderCode;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 到货数量 */
    @Excel(name = "到货数量")
    private BigDecimal qty;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private BigDecimal qualifiedQty;

    /** 拒收数量 */
    @Excel(name = "拒收数量")
    private BigDecimal rejectedQty;

    /** 入库仓库 */
    @Excel(name = "入库仓库")
    private String warehouseCode;

    /** 到货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arrivalDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setArrivalId(Long arrivalId)
    {
        this.arrivalId = arrivalId;
    }

    public Long getArrivalId()
    {
        return arrivalId;
    }

    public void setArrivalCode(String arrivalCode)
    {
        this.arrivalCode = arrivalCode;
    }

    public String getArrivalCode()
    {
        return arrivalCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
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

    public void setQualifiedQty(BigDecimal qualifiedQty)
    {
        this.qualifiedQty = qualifiedQty;
    }

    public BigDecimal getQualifiedQty()
    {
        return qualifiedQty;
    }

    public void setRejectedQty(BigDecimal rejectedQty)
    {
        this.rejectedQty = rejectedQty;
    }

    public BigDecimal getRejectedQty()
    {
        return rejectedQty;
    }

    public void setWarehouseCode(String warehouseCode)
    {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseCode()
    {
        return warehouseCode;
    }

    public void setArrivalDate(Date arrivalDate)
    {
        this.arrivalDate = arrivalDate;
    }

    public Date getArrivalDate()
    {
        return arrivalDate;
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
            .append("arrivalId", getArrivalId())
            .append("arrivalCode", getArrivalCode())
            .append("orderCode", getOrderCode())
            .append("supplierName", getSupplierName())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("qty", getQty())
            .append("qualifiedQty", getQualifiedQty())
            .append("rejectedQty", getRejectedQty())
            .append("warehouseCode", getWarehouseCode())
            .append("arrivalDate", getArrivalDate())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
