package com.ruoyi.mes.om.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 生产需求对象 om_demand
 *
 * @author ruoyi
 */
public class OmDemand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 需求单号 */
    @Excel(name = "需求单号")
    private String demandNo;

    /** 来源订单号 */
    @Excel(name = "来源订单号")
    private String sourceOrderNo;

    /** 来源订单行号 */
    @Excel(name = "来源订单行号")
    private String sourceOrderLineNo;

    /** 来源系统 */
    @Excel(name = "来源系统")
    private String sourceSystem;

    /** ERP需求号 */
    @Excel(name = "ERP需求号")
    private String erpDemandNo;

    /** 工厂编码 */
    @Excel(name = "工厂编码")
    private String plantCode;

    /** 产品ID */
    @Excel(name = "产品ID", cellType = ColumnType.NUMERIC)
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品版本 */
    @Excel(name = "产品版本")
    private String productVersion;

    /** 需求数量 */
    @Excel(name = "需求数量", cellType = ColumnType.NUMERIC)
    private BigDecimal requiredQty;

    /** 交期 */
    @Excel(name = "交期", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 幂等键 */
    @Excel(name = "幂等键")
    private String idempotencyKey;

    /** 变更原因 */
    @Excel(name = "变更原因")
    private String changeReason;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setDemandNo(String demandNo)
    {
        this.demandNo = demandNo;
    }

    public String getDemandNo()
    {
        return demandNo;
    }

    public void setSourceOrderNo(String sourceOrderNo)
    {
        this.sourceOrderNo = sourceOrderNo;
    }

    public String getSourceOrderNo()
    {
        return sourceOrderNo;
    }

    public void setSourceOrderLineNo(String sourceOrderLineNo)
    {
        this.sourceOrderLineNo = sourceOrderLineNo;
    }

    public String getSourceOrderLineNo()
    {
        return sourceOrderLineNo;
    }

    public void setSourceSystem(String sourceSystem)
    {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceSystem()
    {
        return sourceSystem;
    }

    public void setErpDemandNo(String erpDemandNo)
    {
        this.erpDemandNo = erpDemandNo;
    }

    public String getErpDemandNo()
    {
        return erpDemandNo;
    }

    public void setPlantCode(String plantCode)
    {
        this.plantCode = plantCode;
    }

    public String getPlantCode()
    {
        return plantCode;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductVersion(String productVersion)
    {
        this.productVersion = productVersion;
    }

    public String getProductVersion()
    {
        return productVersion;
    }

    public void setRequiredQty(BigDecimal requiredQty)
    {
        this.requiredQty = requiredQty;
    }

    public BigDecimal getRequiredQty()
    {
        return requiredQty;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setIdempotencyKey(String idempotencyKey)
    {
        this.idempotencyKey = idempotencyKey;
    }

    public String getIdempotencyKey()
    {
        return idempotencyKey;
    }

    public void setChangeReason(String changeReason)
    {
        this.changeReason = changeReason;
    }

    public String getChangeReason()
    {
        return changeReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("demandNo", getDemandNo())
            .append("sourceOrderNo", getSourceOrderNo())
            .append("sourceOrderLineNo", getSourceOrderLineNo())
            .append("sourceSystem", getSourceSystem())
            .append("erpDemandNo", getErpDemandNo())
            .append("plantCode", getPlantCode())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productVersion", getProductVersion())
            .append("requiredQty", getRequiredQty())
            .append("dueDate", getDueDate())
            .append("priority", getPriority())
            .append("status", getStatus())
            .append("idempotencyKey", getIdempotencyKey())
            .append("changeReason", getChangeReason())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
