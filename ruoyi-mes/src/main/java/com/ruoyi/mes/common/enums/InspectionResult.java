package com.ruoyi.mes.common.enums;

/**
 * 检验结果
 *
 * @author ruoyi
 */
public enum InspectionResult
{
    PASS("合格"),
    FAIL("不合格"),
    PENDING("待判定");

    private final String desc;

    InspectionResult(String desc)
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
