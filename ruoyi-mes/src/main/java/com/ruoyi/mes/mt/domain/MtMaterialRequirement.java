package com.ruoyi.mes.mt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 物料需求对象 mt_material_requirement
 *
 * @author ruoyi
 */
public class MtMaterialRequirement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 需求单号 */
    @Excel(name = "需求单号")
    private String requirementNo;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 投料工序 */
    @Excel(name = "投料工序")
    private String operationCode;

    /** 物料ID */
    @Excel(name = "物料ID", cellType = ColumnType.NUMERIC)
    private Long materialId;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 需求数量 */
    @Excel(name = "需求数量", cellType = ColumnType.NUMERIC)
    private BigDecimal requiredQty;

    /** 分配数量 */
    @Excel(name = "分配数量", cellType = ColumnType.NUMERIC)
    private BigDecimal allocatedQty;

    /** 拣料数量 */
    @Excel(name = "拣料数量", cellType = ColumnType.NUMERIC)
    private BigDecimal pickedQty;

    /** 消耗数量 */
    @Excel(name = "消耗数量", cellType = ColumnType.NUMERIC)
    private BigDecimal consumedQty;

    /** 缺料数量 */
    @Excel(name = "缺料数量", cellType = ColumnType.NUMERIC)
    private BigDecimal lackQty;

    /** 是否关键物料 */
    @Excel(name = "是否关键物料")
    private String keyFlag;

    /** 齐套状态 */
    @Excel(name = "齐套状态")
    private String kitStatus;

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

    public void setRequirementNo(String requirementNo)
    {
        this.requirementNo = requirementNo;
    }

    public String getRequirementNo()
    {
        return requirementNo;
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

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
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

    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public String getMaterialName()
    {
        return materialName;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setRequiredQty(BigDecimal requiredQty)
    {
        this.requiredQty = requiredQty;
    }

    public BigDecimal getRequiredQty()
    {
        return requiredQty;
    }

    public void setAllocatedQty(BigDecimal allocatedQty)
    {
        this.allocatedQty = allocatedQty;
    }

    public BigDecimal getAllocatedQty()
    {
        return allocatedQty;
    }

    public void setPickedQty(BigDecimal pickedQty)
    {
        this.pickedQty = pickedQty;
    }

    public BigDecimal getPickedQty()
    {
        return pickedQty;
    }

    public void setConsumedQty(BigDecimal consumedQty)
    {
        this.consumedQty = consumedQty;
    }

    public BigDecimal getConsumedQty()
    {
        return consumedQty;
    }

    public void setLackQty(BigDecimal lackQty)
    {
        this.lackQty = lackQty;
    }

    public BigDecimal getLackQty()
    {
        return lackQty;
    }

    public void setKeyFlag(String keyFlag)
    {
        this.keyFlag = keyFlag;
    }

    public String getKeyFlag()
    {
        return keyFlag;
    }

    public void setKitStatus(String kitStatus)
    {
        this.kitStatus = kitStatus;
    }

    public String getKitStatus()
    {
        return kitStatus;
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
            .append("requirementNo", getRequirementNo())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("operationCode", getOperationCode())
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("unit", getUnit())
            .append("requiredQty", getRequiredQty())
            .append("allocatedQty", getAllocatedQty())
            .append("pickedQty", getPickedQty())
            .append("consumedQty", getConsumedQty())
            .append("lackQty", getLackQty())
            .append("keyFlag", getKeyFlag())
            .append("kitStatus", getKitStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
