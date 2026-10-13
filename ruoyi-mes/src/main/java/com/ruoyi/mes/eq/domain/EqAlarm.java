package com.ruoyi.mes.eq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 设备报警对象 eq_alarm
 *
 * @author ruoyi
 */
public class EqAlarm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 设备ID */
    @Excel(name = "设备ID", cellType = ColumnType.NUMERIC)
    private Long equipmentId;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String equipmentCode;

    /** 报警代码 */
    @Excel(name = "报警代码")
    private String alarmCode;

    /** 报警等级 */
    @Excel(name = "报警等级")
    private String alarmLevel;

    /** 报警描述 */
    @Excel(name = "报警描述")
    private String alarmDesc;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 开始时间 */
    @Excel(name = "开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 恢复时间 */
    @Excel(name = "恢复时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 持续时长(分) */
    @Excel(name = "持续时长(分)", cellType = ColumnType.NUMERIC)
    private BigDecimal duration;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }

    public void setEquipmentCode(String equipmentCode)
    {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentCode()
    {
        return equipmentCode;
    }

    public void setAlarmCode(String alarmCode)
    {
        this.alarmCode = alarmCode;
    }

    public String getAlarmCode()
    {
        return alarmCode;
    }

    public void setAlarmLevel(String alarmLevel)
    {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmLevel()
    {
        return alarmLevel;
    }

    public void setAlarmDesc(String alarmDesc)
    {
        this.alarmDesc = alarmDesc;
    }

    public String getAlarmDesc()
    {
        return alarmDesc;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setDuration(BigDecimal duration)
    {
        this.duration = duration;
    }

    public BigDecimal getDuration()
    {
        return duration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentId", getEquipmentId())
            .append("equipmentCode", getEquipmentCode())
            .append("alarmCode", getAlarmCode())
            .append("alarmLevel", getAlarmLevel())
            .append("alarmDesc", getAlarmDesc())
            .append("productionOrderId", getProductionOrderId())
            .append("operationCode", getOperationCode())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("duration", getDuration())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
