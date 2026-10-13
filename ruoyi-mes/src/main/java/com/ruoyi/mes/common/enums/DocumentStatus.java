package com.ruoyi.mes.common.enums;

/**
 * 主数据文档版本状态（架构文档 6.4 主数据版本策略）
 *
 * @author ruoyi
 */
public enum DocumentStatus
{
    DRAFT("草稿"),
    PENDING_APPROVAL("待审批"),
    PUBLISHED("已发布"),
    INVALID("已失效");

    private final String desc;

    DocumentStatus(String desc)
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
