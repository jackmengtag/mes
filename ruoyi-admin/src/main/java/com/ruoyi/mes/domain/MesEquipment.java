package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备台账对象 mes_equipment
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long equipmentId;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String equipmentCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equipmentName;

    /** 产线 */
    @Excel(name = "产线")
    private String lineCode;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String equipmentType;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** OEE */
    @Excel(name = "OEE")
    private BigDecimal oee;

    /** 运行小时 */
    @Excel(name = "运行小时")
    private BigDecimal runHours;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 最近点检 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最近点检", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastCheckTime;

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

    public void setEquipmentName(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName()
    {
        return equipmentName;
    }

    public void setLineCode(String lineCode)
    {
        this.lineCode = lineCode;
    }

    public String getLineCode()
    {
        return lineCode;
    }

    public void setEquipmentType(String equipmentType)
    {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentType()
    {
        return equipmentType;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setOee(BigDecimal oee)
    {
        this.oee = oee;
    }

    public BigDecimal getOee()
    {
        return oee;
    }

    public void setRunHours(BigDecimal runHours)
    {
        this.runHours = runHours;
    }

    public BigDecimal getRunHours()
    {
        return runHours;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLastCheckTime(Date lastCheckTime)
    {
        this.lastCheckTime = lastCheckTime;
    }

    public Date getLastCheckTime()
    {
        return lastCheckTime;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("equipmentId", getEquipmentId())
            .append("equipmentCode", getEquipmentCode())
            .append("equipmentName", getEquipmentName())
            .append("lineCode", getLineCode())
            .append("equipmentType", getEquipmentType())
            .append("status", getStatus())
            .append("oee", getOee())
            .append("runHours", getRunHours())
            .append("location", getLocation())
            .append("lastCheckTime", getLastCheckTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
