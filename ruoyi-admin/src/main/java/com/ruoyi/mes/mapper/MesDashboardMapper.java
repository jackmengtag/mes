package com.ruoyi.mes.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * MES 看板聚合查询
 *
 * @author ruoyi
 */
public interface MesDashboardMapper
{
    Map<String, Object> selectOverviewKpis();

    List<Map<String, Object>> selectLineStats();

    List<Map<String, Object>> selectHourlyOutput();

    List<Map<String, Object>> selectOutputTrend(@Param("days") int days);

    List<Map<String, Object>> selectKitting();

    List<Map<String, Object>> selectWipOrders();

    List<Map<String, Object>> selectActiveAlarms();

    Map<String, Object> selectScmKpis();

    List<Map<String, Object>> selectPendingTasks();

    List<Map<String, Object>> selectDeliveryExceptions();

    List<Map<String, Object>> selectStockWarnings();

    List<Map<String, Object>> selectPendingVouchers();

    List<Map<String, Object>> selectPurchaseTrend();

    List<Map<String, Object>> selectSalesTrend();

    List<Map<String, Object>> selectDefectPareto(@Param("days") int days);

    List<Map<String, Object>> selectOeeByLine();

    List<Map<String, Object>> selectAnalysisLines();

    Map<String, Object> selectAnalysisKpis(@Param("days") int days);
}
