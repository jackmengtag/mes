package com.ruoyi.mes.mt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 补料对象 mt_supplement_material
 *
 * @author ruoyi
 */
public class MtSupplementMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 补料单号 */
    @Excel(name = "补料单号")
    private String supplementNo;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 物料ID */
    @Excel(name = "物料ID", cellType = ColumnType.NUMERIC)
    private Long materialId;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料批次号 */
    @Excel(name = "物料批次号")
    private String materialBatchNo;

    /** 补料数量 */
    @Excel(name = "补料数量", cellType = ColumnType.NUMERIC)
    private BigDecimal supplementQty;

    /** 补料原因 */
    @Excel(name = "补料原因")
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

    public void setSupplementNo(String supplementNo)
    {
        this.supplementNo = supplementNo;
    }

    public String getSupplementNo()
    {
        return supplementNo;
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

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setMaterialCode(String materialCode)
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode()
    {
        return materialCode;
    }

    public void setMaterialBatchNo(String materialBatchNo)
    {
        this.materialBatchNo = materialBatchNo;
    }

    public String getMaterialBatchNo()
    {
        return materialBatchNo;
    }

    public void setSupplementQty(BigDecimal supplementQty)
    {
        this.supplementQty = supplementQty;
    }

    public BigDecimal getSupplementQty()
    {
        return supplementQty;
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
            .append("supplementNo", getSupplementNo())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialBatchNo", getMaterialBatchNo())
            .append("supplementQty", getSupplementQty())
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
