package com.ruoyi.mes.common.enums;

/**
 * 生产计划状态
 *
 * @author ruoyi
 */
public enum PlanStatus
{
    CREATED("新建"),
    SCHEDULED("已排程"),
    RELEASED("已下达"),
    CLOSED("已关闭");

    private final String desc;

    PlanStatus(String desc)
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
