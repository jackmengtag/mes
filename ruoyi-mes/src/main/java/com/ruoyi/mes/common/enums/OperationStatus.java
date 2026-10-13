package com.ruoyi.mes.common.enums;

/**
 * 工序任务状态（需求说明书 11.2.2）
 *
 * @author ruoyi
 */
public enum OperationStatus
{
    NOT_STARTED("未开始"),
    DISPATCHED("已派工"),
    EXECUTING("执行中"),
    WAIT_INSPECT("待检验"),
    COMPLETED("已完成"),
    PAUSED("暂停"),
    ABNORMAL("异常"),
    CANCELLED("已取消");

    private final String desc;

    OperationStatus(String desc)
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
