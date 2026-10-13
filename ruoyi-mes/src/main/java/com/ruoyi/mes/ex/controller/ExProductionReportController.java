package com.ruoyi.mes.ex.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.ex.domain.ExProductionReport;
import com.ruoyi.mes.ex.service.IExProductionReportService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 报工记录Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ex/report")
public class ExProductionReportController extends BaseController
{
    @Autowired
    private IExProductionReportService exProductionReportService;

    /**
     * 查询报工记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExProductionReport exProductionReport)
    {
        startPage();
        List<ExProductionReport> list = exProductionReportService.selectExProductionReportList(exProductionReport);
        return getDataTable(list);
    }

    /**
     * 导出报工记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:report:export')")
    @Log(title = "报工记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExProductionReport exProductionReport)
    {
        List<ExProductionReport> list = exProductionReportService.selectExProductionReportList(exProductionReport);
        ExcelUtil<ExProductionReport> util = new ExcelUtil<ExProductionReport>(ExProductionReport.class);
        util.exportExcel(response, list, "报工记录数据");
    }

    /**
     * 获取报工记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exProductionReportService.selectExProductionReportById(id));
    }

    /**
     * 新增报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:report:add')")
    @Log(title = "报工记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExProductionReport exProductionReport)
    {
        exProductionReport.setCreateBy(getUsername());
        return toAjax(exProductionReportService.insertExProductionReport(exProductionReport));
    }

    /**
     * 修改报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:report:edit')")
    @Log(title = "报工记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExProductionReport exProductionReport)
    {
        exProductionReport.setUpdateBy(getUsername());
        return toAjax(exProductionReportService.updateExProductionReport(exProductionReport));
    }

    /**
     * 删除报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:report:remove')")
    @Log(title = "报工记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exProductionReportService.deleteExProductionReportByIds(ids));
    }
}
