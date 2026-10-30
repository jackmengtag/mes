package com.ruoyi.mes.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mes.mapper.MesDashboardMapper;

/**
 * MES 看板聚合Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/dashboard")
public class MesDashboardController extends BaseController
{
    @Autowired
    private MesDashboardMapper dashboardMapper;

    /** 生产总览 */
    @PreAuthorize("@ss.hasPermi('mes:overview:list')")
    @GetMapping("/overview")
    public AjaxResult overview()
    {
        Map<String, Object> data = new HashMap<>();
        data.put("kpis", dashboardMapper.selectOverviewKpis());
        data.put("lines", dashboardMapper.selectLineStats());
        data.put("hourly", dashboardMapper.selectHourlyOutput());
        data.put("trend", dashboardMapper.selectOutputTrend(7));
        data.put("kitting", dashboardMapper.selectKitting());
        data.put("wipOrders", dashboardMapper.selectWipOrders());
        data.put("alarms", dashboardMapper.selectActiveAlarms());
        return AjaxResult.success(data);
    }

    /** 供应链总览 */
    @PreAuthorize("@ss.hasPermi('mes:scm:list')")
    @GetMapping("/scm")
    public AjaxResult scm()
    {
        Map<String, Object> data = new HashMap<>();
        data.put("kpis", dashboardMapper.selectScmKpis());
        data.put("pending", dashboardMapper.selectPendingTasks());
        data.put("exceptions", dashboardMapper.selectDeliveryExceptions());
        data.put("warnings", dashboardMapper.selectStockWarnings());
        data.put("vouchers", dashboardMapper.selectPendingVouchers());
        data.put("purchaseTrend", dashboardMapper.selectPurchaseTrend());
        data.put("salesTrend", dashboardMapper.selectSalesTrend());
        return AjaxResult.success(data);
    }

    /** 车间大屏 */
    @PreAuthorize("@ss.hasPermi('mes:board:list')")
    @GetMapping("/board")
    public AjaxResult board()
    {
        Map<String, Object> data = new HashMap<>();
        data.put("kpis", dashboardMapper.selectOverviewKpis());
        data.put("lines", dashboardMapper.selectLineStats());
        data.put("hourly", dashboardMapper.selectHourlyOutput());
        data.put("wipOrders", dashboardMapper.selectWipOrders());
        data.put("alarms", dashboardMapper.selectActiveAlarms());
        return AjaxResult.success(data);
    }

    /** 统计分析 */
    @PreAuthorize("@ss.hasPermi('mes:analysis:list')")
    @GetMapping("/analysis")
    public AjaxResult analysis(@RequestParam(defaultValue = "7") int range)
    {
        int days = range <= 0 ? 7 : range;
        Map<String, Object> data = new HashMap<>();
        data.put("kpis", dashboardMapper.selectAnalysisKpis(days));
        data.put("trend", dashboardMapper.selectOutputTrend(days));
        data.put("lines", dashboardMapper.selectAnalysisLines());
        data.put("oee", dashboardMapper.selectOeeByLine());
        data.put("defects", dashboardMapper.selectDefectPareto(days));
        return AjaxResult.success(data);
    }
}
