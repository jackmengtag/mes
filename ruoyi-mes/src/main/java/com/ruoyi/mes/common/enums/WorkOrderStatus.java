package com.ruoyi.mes.common.enums;

/**
 * 工单状态（FR-WO / 需求说明书 11.2.1）
 *
 * @author ruoyi
 */
public enum WorkOrderStatus
{
    CREATED("新建"),
    AUDITED("已审核"),
    SCHEDULED("已排产"),
    RELEASED("已下达"),
    WAIT_MATERIAL("待料"),
    EXECUTING("执行中"),
    PAUSED("暂停"),
    ABNORMAL("异常"),
    PARTIAL("部分完成"),
    COMPLETED("已完成"),
    CLOSED("已关闭");

    private final String desc;

    WorkOrderStatus(String desc)
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
