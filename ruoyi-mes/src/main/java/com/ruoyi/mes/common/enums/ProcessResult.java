package com.ruoyi.mes.common.enums;

/**
 * 集成消息处理结果（重试判定）
 *
 * @author ruoyi
 */
public enum ProcessResult
{
    SUCCESS("成功"),
    RETRYABLE("可重试"),
    NOT_RETRYABLE("不可重试"),
    BUSINESS_CONFLICT("业务冲突");

    private final String desc;

    ProcessResult(String desc)
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
