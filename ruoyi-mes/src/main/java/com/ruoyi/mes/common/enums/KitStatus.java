package com.ruoyi.mes.common.enums;

/**
 * 物料齐套状态（架构文档 7.5.3 物料齐套模型）
 *
 * @author ruoyi
 */
public enum KitStatus
{
    NOT_CALCULATED("未计算"),
    LACK("缺料"),
    PARTIAL("部分齐套"),
    KITTED("已齐套"),
    DELIVERED("已配送"),
    PARTIAL_CONSUMED("部分消耗"),
    CONSUMED("已消耗");

    private final String desc;

    KitStatus(String desc)
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
