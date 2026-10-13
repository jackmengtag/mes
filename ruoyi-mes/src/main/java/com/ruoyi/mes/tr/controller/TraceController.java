package com.ruoyi.mes.tr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mes.tr.service.TraceService;

/**
 * 追溯控制器（FR-TR-001~004）：正向、反向、召回、报告
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/tr/trace")
public class TraceController extends BaseController
{
    @Autowired
    private TraceService traceService;

    /** 反向追溯：输入产品序列号，输出完整履历 */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:query')")
    @GetMapping("/backward/{serialNo}")
    public AjaxResult backward(@PathVariable("serialNo") String serialNo)
    {
        return success(traceService.backward(serialNo));
    }

    /** 正向追溯：输入物料批次或设备编号，输出受影响产品与工单 */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:query')")
    @GetMapping("/forward")
    public AjaxResult forward(@RequestParam(value = "materialBatchNo", required = false) String materialBatchNo,
            @RequestParam(value = "equipmentCode", required = false) String equipmentCode)
    {
        return success(traceService.forward(materialBatchNo, equipmentCode));
    }

    /** 批次召回分析 */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:query')")
    @GetMapping("/recall/{materialBatchNo}")
    public AjaxResult recall(@PathVariable("materialBatchNo") String materialBatchNo)
    {
        return success(traceService.recall(materialBatchNo));
    }

    /** 追溯报告导出数据 */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:query')")
    @GetMapping("/report/{serialNo}")
    public AjaxResult report(@PathVariable("serialNo") String serialNo)
    {
        return success(traceService.exportReport(serialNo));
    }
}
