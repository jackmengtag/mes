package com.ruoyi.mes.eq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 设备维护与停机对象 eq_maintenance
 *
 * @author ruoyi
 */
public class EqMaintenance extends BaseEntity
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

    /** 维护类型 */
    @Excel(name = "维护类型")
    private String maintenanceType;

    /** 停机原因分类 */
    @Excel(name = "停机原因分类")
    private String stopReason;

    /** 开始时间 */
    @Excel(name = "开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 停机时长(分) */
    @Excel(name = "停机时长(分)", cellType = ColumnType.NUMERIC)
    private BigDecimal stopMinutes;

    /** 执行人 */
    @Excel(name = "执行人")
    private String executorNo;

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

    public void setMaintenanceType(String maintenanceType)
    {
        this.maintenanceType = maintenanceType;
    }

    public String getMaintenanceType()
    {
        return maintenanceType;
    }

    public void setStopReason(String stopReason)
    {
        this.stopReason = stopReason;
    }

    public String getStopReason()
    {
        return stopReason;
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

    public void setStopMinutes(BigDecimal stopMinutes)
    {
        this.stopMinutes = stopMinutes;
    }

    public BigDecimal getStopMinutes()
    {
        return stopMinutes;
    }

    public void setExecutorNo(String executorNo)
    {
        this.executorNo = executorNo;
    }

    public String getExecutorNo()
    {
        return executorNo;
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
            .append("equipmentId", getEquipmentId())
            .append("equipmentCode", getEquipmentCode())
            .append("maintenanceType", getMaintenanceType())
            .append("stopReason", getStopReason())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("stopMinutes", getStopMinutes())
            .append("executorNo", getExecutorNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
