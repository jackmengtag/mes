package com.ruoyi.mes.common.enums;

/**
 * 集成消息状态（需求说明书 11.2.3）
 *
 * @author ruoyi
 */
public enum MessageStatus
{
    CREATED("已创建"),
    VALIDATED("已校验"),
    SENT("已发送"),
    ACCEPTED("已受理"),
    PROCESSED("已处理"),
    CONFIRMED("已确认"),
    VALIDATION_FAILED("校验失败"),
    RETRYING("重试中"),
    DEAD_LETTER("死信"),
    MANUAL_PROCESSING("人工处理中"),
    CANCELLED("已取消");

    private final String desc;

    MessageStatus(String desc)
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
