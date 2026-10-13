package com.ruoyi.mes.common.enums;

/**
 * 产品序列号状态
 *
 * @author ruoyi
 */
public enum SerialStatus
{
    CREATED("已创建"),
    IN_PROCESS("在制"),
    WAIT_INSPECT("待检验"),
    QUALIFIED("合格"),
    UNQUALIFIED("不合格"),
    SCRAPPED("已报废"),
    IN_STOCK("已入库"),
    SHIPPED("已发货");

    private final String desc;

    SerialStatus(String desc)
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
