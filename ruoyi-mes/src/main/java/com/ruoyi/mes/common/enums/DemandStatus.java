package com.ruoyi.mes.common.enums;

/**
 * 生产需求状态
 *
 * @author ruoyi
 */
public enum DemandStatus
{
    CONFIRMED("已确认"),
    DECOMPOSED("已分解"),
    PRODUCING("生产中"),
    PARTIAL("部分完成"),
    COMPLETED("已完成"),
    CLOSED("已关闭"),
    VALIDATION_FAILED("校验失败");

    private final String desc;

    DemandStatus(String desc)
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
