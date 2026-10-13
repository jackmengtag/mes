package com.ruoyi.mes.ex.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 工艺参数采集对象 ex_process_param
 *
 * @author ruoyi
 */
public class ExProcessParam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工序任务ID */
    @Excel(name = "工序任务ID", cellType = ColumnType.NUMERIC)
    private Long operationTaskId;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNo;

    /** 设备ID */
    @Excel(name = "设备ID", cellType = ColumnType.NUMERIC)
    private Long equipmentId;

    /** 参数名称 */
    @Excel(name = "参数名称")
    private String paramName;

    /** 参数值 */
    @Excel(name = "参数值")
    private String paramValue;

    /** 单位 */
    @Excel(name = "单位")
    private String paramUnit;

    /** 下限 */
    @Excel(name = "下限", cellType = ColumnType.NUMERIC)
    private BigDecimal lowerLimit;

    /** 上限 */
    @Excel(name = "上限", cellType = ColumnType.NUMERIC)
    private BigDecimal upperLimit;

    /** 判定结果 */
    @Excel(name = "判定结果")
    private String result;

    /** 采集时间 */
    @Excel(name = "采集时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date collectedAt;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setOperationTaskId(Long operationTaskId)
    {
        this.operationTaskId = operationTaskId;
    }

    public Long getOperationTaskId()
    {
        return operationTaskId;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }

    public void setParamName(String paramName)
    {
        this.paramName = paramName;
    }

    public String getParamName()
    {
        return paramName;
    }

    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }

    public String getParamValue()
    {
        return paramValue;
    }

    public void setParamUnit(String paramUnit)
    {
        this.paramUnit = paramUnit;
    }

    public String getParamUnit()
    {
        return paramUnit;
    }

    public void setLowerLimit(BigDecimal lowerLimit)
    {
        this.lowerLimit = lowerLimit;
    }

    public BigDecimal getLowerLimit()
    {
        return lowerLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit)
    {
        this.upperLimit = upperLimit;
    }

    public BigDecimal getUpperLimit()
    {
        return upperLimit;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    public void setCollectedAt(Date collectedAt)
    {
        this.collectedAt = collectedAt;
    }

    public Date getCollectedAt()
    {
        return collectedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productionOrderId", getProductionOrderId())
            .append("operationTaskId", getOperationTaskId())
            .append("serialNo", getSerialNo())
            .append("equipmentId", getEquipmentId())
            .append("paramName", getParamName())
            .append("paramValue", getParamValue())
            .append("paramUnit", getParamUnit())
            .append("lowerLimit", getLowerLimit())
            .append("upperLimit", getUpperLimit())
            .append("result", getResult())
            .append("collectedAt", getCollectedAt())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
