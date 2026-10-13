package com.ruoyi.mes.common.enums;

/**
 * 不合格品处理状态（需求说明书 11.2.4）
 *
 * @author ruoyi
 */
public enum NonconformityStatus
{
    REGISTERED("已登记"),
    ISOLATED("已隔离"),
    WAIT_DISPOSITION("待判定"),
    REWORKING("返工中"),
    REPAIRING("返修中"),
    SCRAPPED("已报废"),
    CONCESSION("让步接收"),
    WAIT_REINSPECT("待复检"),
    CLOSED("已关闭");

    private final String desc;

    NonconformityStatus(String desc)
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
