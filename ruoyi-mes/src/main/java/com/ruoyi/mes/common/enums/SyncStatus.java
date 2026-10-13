package com.ruoyi.mes.common.enums;

/**
 * 外部同步状态
 *
 * @author ruoyi
 */
public enum SyncStatus
{
    PENDING("待同步"),
    SUCCESS("成功"),
    FAILED("失败"),
    DEAD_LETTER("死信"),
    MANUAL("人工处理");

    private final String desc;

    SyncStatus(String desc)
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
