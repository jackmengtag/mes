package com.ruoyi.mes.common.enums;

/**
 * 设备状态
 *
 * @author ruoyi
 */
public enum EquipmentStatus
{
    RUNNING("运行"),
    STANDBY("待机"),
    STOPPED("停机"),
    FAULT("故障"),
    MAINTENANCE("维护"),
    OFFLINE("离线");

    private final String desc;

    EquipmentStatus(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }

    public String getCode()
    {
        return name();
    }
}
