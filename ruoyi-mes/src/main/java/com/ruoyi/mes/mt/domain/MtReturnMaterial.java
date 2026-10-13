package com.ruoyi.mes.mt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 退料对象 mt_return_material
 *
 * @author ruoyi
 */
public class MtReturnMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 退料单号 */
    @Excel(name = "退料单号")
    private String returnNo;

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

    /** 原领料单 */
    @Excel(name = "原领料单")
    private String originPickingNo;

    /** 退料数量 */
    @Excel(name = "退料数量", cellType = ColumnType.NUMERIC)
    private BigDecimal returnQty;

    /** 退料原因 */
    @Excel(name = "退料原因")
    private String reason;

    /** 仓库确认状态 */
    @Excel(name = "仓库确认状态")
    private String warehouseConfirmStatus;

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

    public void setReturnNo(String returnNo)
    {
        this.returnNo = returnNo;
    }

    public String getReturnNo()
    {
        return returnNo;
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

    public void setOriginPickingNo(String originPickingNo)
    {
        this.originPickingNo = originPickingNo;
    }

    public String getOriginPickingNo()
    {
        return originPickingNo;
    }

    public void setReturnQty(BigDecimal returnQty)
    {
        this.returnQty = returnQty;
    }

    public BigDecimal getReturnQty()
    {
        return returnQty;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }

    public void setWarehouseConfirmStatus(String warehouseConfirmStatus)
    {
        this.warehouseConfirmStatus = warehouseConfirmStatus;
    }

    public String getWarehouseConfirmStatus()
    {
        return warehouseConfirmStatus;
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
            .append("returnNo", getReturnNo())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialBatchNo", getMaterialBatchNo())
            .append("originPickingNo", getOriginPickingNo())
            .append("returnQty", getReturnQty())
            .append("reason", getReason())
            .append("warehouseConfirmStatus", getWarehouseConfirmStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
