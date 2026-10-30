package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备报警对象 mes_alarm
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesAlarm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报警ID */
    @Excel(name = "报警ID")
    private Long alarmId;

    /** 报警单号 */
    @Excel(name = "报警单号")
    private String alarmCode;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String equipmentCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equipmentName;

    /** 级别 */
    @Excel(name = "级别")
    private String level;

    /** 报警内容 */
    @Excel(name = "报警内容")
    private String content;

    /** 报警时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "报警时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date alarmTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 处理人 */
    @Excel(name = "处理人")
    private String handler;

    public void setAlarmId(Long alarmId)
    {
        this.alarmId = alarmId;
    }

    public Long getAlarmId()
    {
        return alarmId;
    }

    public void setAlarmCode(String alarmCode)
    {
        this.alarmCode = alarmCode;
    }

    public String getAlarmCode()
    {
        return alarmCode;
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

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLevel()
    {
        return level;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setAlarmTime(Date alarmTime)
    {
        this.alarmTime = alarmTime;
    }

    public Date getAlarmTime()
    {
        return alarmTime;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setHandler(String handler)
    {
        this.handler = handler;
    }

    public String getHandler()
    {
        return handler;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("alarmId", getAlarmId())
            .append("alarmCode", getAlarmCode())
            .append("equipmentCode", getEquipmentCode())
            .append("equipmentName", getEquipmentName())
            .append("level", getLevel())
            .append("content", getContent())
            .append("alarmTime", getAlarmTime())
            .append("status", getStatus())
            .append("handler", getHandler())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
