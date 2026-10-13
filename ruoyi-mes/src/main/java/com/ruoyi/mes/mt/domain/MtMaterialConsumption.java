package com.ruoyi.mes.mt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料批次消耗对象 mt_material_consumption
 *
 * @author ruoyi
 */
public class MtMaterialConsumption extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 消耗单号 */
    @Excel(name = "消耗单号")
    private String consumeNo;

    /** 物料ID */
    @Excel(name = "物料ID", cellType = ColumnType.NUMERIC)
    private Long materialId;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料批次号 */
    @Excel(name = "物料批次号")
    private String materialBatchNo;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 产品序列号 */
    @Excel(name = "产品序列号")
    private String serialNo;

    /** 工序任务ID */
    @Excel(name = "工序任务ID", cellType = ColumnType.NUMERIC)
    private Long operationTaskId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 消耗数量 */
    @Excel(name = "消耗数量", cellType = ColumnType.NUMERIC)
    private BigDecimal consumedQty;

    /** 是否替代料 */
    @Excel(name = "是否替代料")
    private String substituteFlag;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operatorNo;

    /** WMS库存事务号 */
    @Excel(name = "WMS库存事务号")
    private String wmsTransactionNo;

    /** 同步状态 */
    @Excel(name = "同步状态")
    private String syncStatus;

    /** 消耗时间 */
    @Excel(name = "消耗时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date consumedAt;

    /** 幂等键 */
    @Excel(name = "幂等键")
    private String idempotencyKey;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setConsumeNo(String consumeNo)
    {
        this.consumeNo = consumeNo;
    }

    public String getConsumeNo()
    {
        return consumeNo;
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

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setOperationTaskId(Long operationTaskId)
    {
        this.operationTaskId = operationTaskId;
    }

    public Long getOperationTaskId()
    {
        return operationTaskId;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setConsumedQty(BigDecimal consumedQty)
    {
        this.consumedQty = consumedQty;
    }

    public BigDecimal getConsumedQty()
    {
        return consumedQty;
    }

    public void setSubstituteFlag(String substituteFlag)
    {
        this.substituteFlag = substituteFlag;
    }

    public String getSubstituteFlag()
    {
        return substituteFlag;
    }

    public void setOperatorNo(String operatorNo)
    {
        this.operatorNo = operatorNo;
    }

    public String getOperatorNo()
    {
        return operatorNo;
    }

    public void setWmsTransactionNo(String wmsTransactionNo)
    {
        this.wmsTransactionNo = wmsTransactionNo;
    }

    public String getWmsTransactionNo()
    {
        return wmsTransactionNo;
    }

    public void setSyncStatus(String syncStatus)
    {
        this.syncStatus = syncStatus;
    }

    public String getSyncStatus()
    {
        return syncStatus;
    }

    public void setConsumedAt(Date consumedAt)
    {
        this.consumedAt = consumedAt;
    }

    public Date getConsumedAt()
    {
        return consumedAt;
    }

    public void setIdempotencyKey(String idempotencyKey)
    {
        this.idempotencyKey = idempotencyKey;
    }

    public String getIdempotencyKey()
    {
        return idempotencyKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("consumeNo", getConsumeNo())
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialBatchNo", getMaterialBatchNo())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("serialNo", getSerialNo())
            .append("operationTaskId", getOperationTaskId())
            .append("operationCode", getOperationCode())
            .append("consumedQty", getConsumedQty())
            .append("substituteFlag", getSubstituteFlag())
            .append("operatorNo", getOperatorNo())
            .append("wmsTransactionNo", getWmsTransactionNo())
            .append("syncStatus", getSyncStatus())
            .append("consumedAt", getConsumedAt())
            .append("idempotencyKey", getIdempotencyKey())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
