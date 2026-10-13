package com.ruoyi.mes.common.enums;

/**
 * 检验类型（IQC / 首件 / 巡检 / FQC）
 *
 * @author ruoyi
 */
public enum InspectionType
{
    IQC("来料检验"),
    FIRST("首件检验"),
    PATROL("巡检"),
    FQC("完工检验");

    private final String desc;

    InspectionType(String desc)
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
