package com.ruoyi.mes.common.mapper;

import java.util.List;
import java.util.Map;

/**
 * MES 跨模块统计与校验查询
 *
 * @author ruoyi
 */
public interface MesQueryMapper
{
    /** 统计工单未关闭的不合格品数量 */
    int countOpenNonconformity(Long productionOrderId);

    /** 统计工单未完成的强制检验工序数量 */
    int countPendingForceInspection(Long productionOrderId);

    /** 统计工单关键物料未齐套数量 */
    int countKeyMaterialLack(Long productionOrderId);

    /** 统计工单未完成工序数量 */
    int countUnfinishedOperation(Long productionOrderId);

    /** 生产看板指标 */
    Map<String, Object> selectProductionKpi();

    /** 质量看板指标 */
    Map<String, Object> selectQualityKpi();

    /** 设备看板指标 */
    Map<String, Object> selectEquipmentKpi();

    /** 物料看板指标 */
    Map<String, Object> selectMaterialKpi();

    /** 报工明细报表 */
    List<Map<String, Object>> selectReportDetail(Map<String, Object> params);

    /** 工单进度报表 */
    List<Map<String, Object>> selectOrderProgress(Map<String, Object> params);

    /** 物料消耗报表 */
    List<Map<String, Object>> selectConsumptionReport(Map<String, Object> params);

    /** 质量检验报表 */
    List<Map<String, Object>> selectInspectionReport(Map<String, Object> params);
}
