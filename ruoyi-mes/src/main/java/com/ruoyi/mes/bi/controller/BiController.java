package com.ruoyi.mes.bi.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mes.bi.service.DashboardService;

/**
 * 看板与分析控制器（FR-BI）
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/bi")
public class BiController extends BaseController
{
    @Autowired
    private DashboardService dashboardService;

    /** 生产看板指标 */
    @GetMapping("/kpi/production")
    public AjaxResult production()
    {
        return success(dashboardService.productionKpi());
    }

    /** 质量看板指标 */
    @GetMapping("/kpi/quality")
    public AjaxResult quality()
    {
        return success(dashboardService.qualityKpi());
    }

    /** 设备看板指标 */
    @GetMapping("/kpi/equipment")
    public AjaxResult equipment()
    {
        return success(dashboardService.equipmentKpi());
    }

    /** 物料看板指标 */
    @GetMapping("/kpi/material")
    public AjaxResult material()
    {
        return success(dashboardService.materialKpi());
    }

    /** 报表查询 */
    @PostMapping("/report/{reportType}")
    public AjaxResult report(@PathVariable("reportType") String reportType,
            @RequestBody(required = false) Map<String, Object> params)
    {
        return success(dashboardService.report(reportType, params));
    }
}
