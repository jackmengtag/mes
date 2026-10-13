package com.ruoyi.mes.common.enums;

/**
 * 不合格品处置方式
 *
 * @author ruoyi
 */
public enum DispositionType
{
    REWORK("返工"),
    REPAIR("返修"),
    SCRAP("报废"),
    CONCESSION("让步接收"),
    RETURN_SUPPLIER("退供应商");

    private final String desc;

    DispositionType(String desc)
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
