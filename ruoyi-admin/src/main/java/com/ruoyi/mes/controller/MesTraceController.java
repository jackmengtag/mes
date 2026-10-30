package com.ruoyi.mes.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.mes.domain.MesTrace;
import com.ruoyi.mes.service.IMesTraceService;

/**
 * 质量追溯Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/trace")
public class MesTraceController extends BaseController
{
    @Autowired
    private IMesTraceService mesTraceService;

    @PreAuthorize("@ss.hasPermi('mes:trace:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesTrace mesTrace)
    {
        startPage();
        List<MesTrace> list = mesTraceService.selectMesTraceList(mesTrace);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:trace:export')")
    @Log(title = "质量追溯", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesTrace mesTrace)
    {
        List<MesTrace> list = mesTraceService.selectMesTraceList(mesTrace);
        ExcelUtil<MesTrace> util = new ExcelUtil<MesTrace>(MesTrace.class);
        util.exportExcel(response, list, "质量追溯数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:trace:query')")
    @GetMapping(value = "/{traceId}")
    public AjaxResult getInfo(@PathVariable("traceId") Long traceId)
    {
        return success(mesTraceService.selectMesTraceById(traceId));
    }

    @PreAuthorize("@ss.hasPermi('mes:trace:add')")
    @Log(title = "质量追溯", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesTrace mesTrace)
    {
        mesTrace.setCreateBy(getUsername());
        return toAjax(mesTraceService.insertMesTrace(mesTrace));
    }

    @PreAuthorize("@ss.hasPermi('mes:trace:edit')")
    @Log(title = "质量追溯", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesTrace mesTrace)
    {
        mesTrace.setUpdateBy(getUsername());
        return toAjax(mesTraceService.updateMesTrace(mesTrace));
    }

    @PreAuthorize("@ss.hasPermi('mes:trace:remove')")
    @Log(title = "质量追溯", businessType = BusinessType.DELETE)
    @DeleteMapping("/{traceIds}")
    public AjaxResult remove(@PathVariable Long[] traceIds)
    {
        return toAjax(mesTraceService.deleteMesTraceByIds(traceIds));
    }
}
