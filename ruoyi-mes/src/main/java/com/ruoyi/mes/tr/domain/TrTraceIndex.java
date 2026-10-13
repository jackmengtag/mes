package com.ruoyi.mes.tr.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 追溯索引对象 tr_trace_index
 *
 * @author ruoyi
 */
public class TrTraceIndex extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 产品序列号 */
    @Excel(name = "产品序列号")
    private String serialNo;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 物料批次号 */
    @Excel(name = "物料批次号")
    private String materialBatchNo;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String equipmentCode;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operatorNo;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 工艺版本 */
    @Excel(name = "工艺版本")
    private String routeVersion;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String refType;

    /** 业务ID */
    @Excel(name = "业务ID", cellType = ColumnType.NUMERIC)
    private Long refId;

    /** 业务时间 */
    @Excel(name = "业务时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date bizTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
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

    public void setMaterialBatchNo(String materialBatchNo)
    {
        this.materialBatchNo = materialBatchNo;
    }

    public String getMaterialBatchNo()
    {
        return materialBatchNo;
    }

    public void setEquipmentCode(String equipmentCode)
    {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentCode()
    {
        return equipmentCode;
    }

    public void setOperatorNo(String operatorNo)
    {
        this.operatorNo = operatorNo;
    }

    public String getOperatorNo()
    {
        return operatorNo;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setRouteVersion(String routeVersion)
    {
        this.routeVersion = routeVersion;
    }

    public String getRouteVersion()
    {
        return routeVersion;
    }

    public void setRefType(String refType)
    {
        this.refType = refType;
    }

    public String getRefType()
    {
        return refType;
    }

    public void setRefId(Long refId)
    {
        this.refId = refId;
    }

    public Long getRefId()
    {
        return refId;
    }

    public void setBizTime(Date bizTime)
    {
        this.bizTime = bizTime;
    }

    public Date getBizTime()
    {
        return bizTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("serialNo", getSerialNo())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("materialBatchNo", getMaterialBatchNo())
            .append("equipmentCode", getEquipmentCode())
            .append("operatorNo", getOperatorNo())
            .append("operationCode", getOperationCode())
            .append("routeVersion", getRouteVersion())
            .append("refType", getRefType())
            .append("refId", getRefId())
            .append("bizTime", getBizTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
