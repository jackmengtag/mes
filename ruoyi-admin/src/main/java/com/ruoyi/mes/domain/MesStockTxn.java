package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出入库流水对象 mes_stock_txn
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesStockTxn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水ID */
    @Excel(name = "流水ID")
    private Long txnId;

    /** 流水号 */
    @Excel(name = "流水号")
    private String txnCode;

    /** 类型 */
    @Excel(name = "类型")
    private String txnType;

    /** 来源 */
    @Excel(name = "来源")
    private String sourceType;

    /** 来源单号 */
    @Excel(name = "来源单号")
    private String sourceCode;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 仓库 */
    @Excel(name = "仓库")
    private String warehouseCode;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal qty;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发生时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date txnTime;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    public void setTxnId(Long txnId)
    {
        this.txnId = txnId;
    }

    public Long getTxnId()
    {
        return txnId;
    }

    public void setTxnCode(String txnCode)
    {
        this.txnCode = txnCode;
    }

    public String getTxnCode()
    {
        return txnCode;
    }

    public void setTxnType(String txnType)
    {
        this.txnType = txnType;
    }

    public String getTxnType()
    {
        return txnType;
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

    public void setWarehouseCode(String warehouseCode)
    {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseCode()
    {
        return warehouseCode;
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

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
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

    public void setTxnTime(Date txnTime)
    {
        this.txnTime = txnTime;
    }

    public Date getTxnTime()
    {
        return txnTime;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getOperator()
    {
        return operator;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("txnId", getTxnId())
            .append("txnCode", getTxnCode())
            .append("txnType", getTxnType())
            .append("sourceType", getSourceType())
            .append("sourceCode", getSourceCode())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("warehouseCode", getWarehouseCode())
            .append("batchNo", getBatchNo())
            .append("qty", getQty())
            .append("unit", getUnit())
            .append("price", getPrice())
            .append("amount", getAmount())
            .append("txnTime", getTxnTime())
            .append("operator", getOperator())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
