package com.ruoyi.mes.common.constant;

/**
 * MES 业务通用常量
 *
 * @author ruoyi
 */
public class MesConstants
{
    /** 默认工厂编码 */
    public static final String DEFAULT_PLANT = "PLANT-01";

    /** 生产需求单号前缀 */
    public static final String PREFIX_DEMAND = "RD";
    /** 生产计划单号前缀 */
    public static final String PREFIX_PLAN = "PL";
    /** 生产工单号前缀 */
    public static final String PREFIX_WORK_ORDER = "MO";
    /** 报工单号前缀 */
    public static final String PREFIX_REPORT = "RPT";
    /** 产品序列号前缀 */
    public static final String PREFIX_SERIAL = "SN";
    /** 检验单号前缀 */
    public static final String PREFIX_INSPECTION = "QC";
    /** 不合格单号前缀 */
    public static final String PREFIX_NONCONFORMITY = "NC";
    /** 物料消耗单号前缀 */
    public static final String PREFIX_CONSUMPTION = "CON";
    /** 成品入库单号前缀 */
    public static final String PREFIX_INBOUND = "IN";
    /** 物料需求单号前缀 */
    public static final String PREFIX_REQUIREMENT = "MR";
    /** 领域事件编号前缀 */
    public static final String PREFIX_EVENT = "EVT";
    /** 返工工单号前缀 */
    public static final String PREFIX_REWORK = "RW";
    /** 质量放行单号前缀 */
    public static final String PREFIX_RELEASE = "REL";
    /** 退料单号前缀 */
    public static final String PREFIX_RETURN = "RT";
    /** 补料单号前缀 */
    public static final String PREFIX_SUPPLEMENT = "SP";
    /** 对账任务单号前缀 */
    public static final String PREFIX_RECONCILE = "REC";
    /** 设备命令编号前缀 */
    public static final String PREFIX_COMMAND = "CMD";

    /** 正常状态 */
    public static final String STATUS_NORMAL = "0";
    /** 停用状态 */
    public static final String STATUS_DISABLE = "1";

    /** 是 */
    public static final String YES = "Y";
    /** 否 */
    public static final String NO = "N";

    /** 未删除 */
    public static final String NOT_DELETED = "0";
    /** 已删除 */
    public static final String DELETED = "2";

    private MesConstants()
    {
    }
}
