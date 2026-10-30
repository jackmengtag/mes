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
import com.ruoyi.mes.domain.MesReport;
import com.ruoyi.mes.service.IMesReportService;

/**
 * 生产报工Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/report")
public class MesReportController extends BaseController
{
    @Autowired
    private IMesReportService mesReportService;

    @PreAuthorize("@ss.hasPermi('mes:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesReport mesReport)
    {
        startPage();
        List<MesReport> list = mesReportService.selectMesReportList(mesReport);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:report:export')")
    @Log(title = "生产报工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesReport mesReport)
    {
        List<MesReport> list = mesReportService.selectMesReportList(mesReport);
        ExcelUtil<MesReport> util = new ExcelUtil<MesReport>(MesReport.class);
        util.exportExcel(response, list, "生产报工数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:report:query')")
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return success(mesReportService.selectMesReportById(reportId));
    }

    @PreAuthorize("@ss.hasPermi('mes:report:add')")
    @Log(title = "生产报工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesReport mesReport)
    {
        mesReport.setCreateBy(getUsername());
        return toAjax(mesReportService.insertMesReport(mesReport));
    }

    @PreAuthorize("@ss.hasPermi('mes:report:edit')")
    @Log(title = "生产报工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesReport mesReport)
    {
        mesReport.setUpdateBy(getUsername());
        return toAjax(mesReportService.updateMesReport(mesReport));
    }

    @PreAuthorize("@ss.hasPermi('mes:report:remove')")
    @Log(title = "生产报工", businessType = BusinessType.DELETE)
    @DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(mesReportService.deleteMesReportByIds(reportIds));
    }
}
