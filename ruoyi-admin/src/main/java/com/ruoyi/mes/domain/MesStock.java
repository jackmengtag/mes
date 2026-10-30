package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实时库存对象 mes_stock
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库存ID */
    @Excel(name = "库存ID")
    private Long stockId;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 规格 */
    @Excel(name = "规格")
    private String spec;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String warehouseCode;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String warehouseName;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private BigDecimal qty;

    /** 锁定数量 */
    @Excel(name = "锁定数量")
    private BigDecimal lockedQty;

    /** 安全库存 */
    @Excel(name = "安全库存")
    private BigDecimal safetyQty;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 库位 */
    @Excel(name = "库位")
    private String location;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setStockId(Long stockId)
    {
        this.stockId = stockId;
    }

    public Long getStockId()
    {
        return stockId;
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

    public void setSpec(String spec)
    {
        this.spec = spec;
    }

    public String getSpec()
    {
        return spec;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setWarehouseCode(String warehouseCode)
    {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseCode()
    {
        return warehouseCode;
    }

    public void setWarehouseName(String warehouseName)
    {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName()
    {
        return warehouseName;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setLockedQty(BigDecimal lockedQty)
    {
        this.lockedQty = lockedQty;
    }

    public BigDecimal getLockedQty()
    {
        return lockedQty;
    }

    public void setSafetyQty(BigDecimal safetyQty)
    {
        this.safetyQty = safetyQty;
    }

    public BigDecimal getSafetyQty()
    {
        return safetyQty;
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

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
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
            .append("stockId", getStockId())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("spec", getSpec())
            .append("unit", getUnit())
            .append("warehouseCode", getWarehouseCode())
            .append("warehouseName", getWarehouseName())
            .append("batchNo", getBatchNo())
            .append("qty", getQty())
            .append("lockedQty", getLockedQty())
            .append("safetyQty", getSafetyQty())
            .append("price", getPrice())
            .append("amount", getAmount())
            .append("location", getLocation())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
