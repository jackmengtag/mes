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
import com.ruoyi.mes.ex.domain.ExExceptionReport;
import com.ruoyi.mes.ex.service.IExExceptionReportService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 现场异常上报Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ex/exceptionReport")
public class ExExceptionReportController extends BaseController
{
    @Autowired
    private IExExceptionReportService exExceptionReportService;

    /**
     * 查询现场异常上报列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:exceptionReport:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExExceptionReport exExceptionReport)
    {
        startPage();
        List<ExExceptionReport> list = exExceptionReportService.selectExExceptionReportList(exExceptionReport);
        return getDataTable(list);
    }

    /**
     * 导出现场异常上报列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:exceptionReport:export')")
    @Log(title = "现场异常上报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExExceptionReport exExceptionReport)
    {
        List<ExExceptionReport> list = exExceptionReportService.selectExExceptionReportList(exExceptionReport);
        ExcelUtil<ExExceptionReport> util = new ExcelUtil<ExExceptionReport>(ExExceptionReport.class);
        util.exportExcel(response, list, "现场异常上报数据");
    }

    /**
     * 获取现场异常上报详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:exceptionReport:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exExceptionReportService.selectExExceptionReportById(id));
    }

    /**
     * 新增现场异常上报
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:exceptionReport:add')")
    @Log(title = "现场异常上报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExExceptionReport exExceptionReport)
    {
        exExceptionReport.setCreateBy(getUsername());
        return toAjax(exExceptionReportService.insertExExceptionReport(exExceptionReport));
    }

    /**
     * 修改现场异常上报
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:exceptionReport:edit')")
    @Log(title = "现场异常上报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExExceptionReport exExceptionReport)
    {
        exExceptionReport.setUpdateBy(getUsername());
        return toAjax(exExceptionReportService.updateExExceptionReport(exExceptionReport));
    }

    /**
     * 删除现场异常上报
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:exceptionReport:remove')")
    @Log(title = "现场异常上报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exExceptionReportService.deleteExExceptionReportByIds(ids));
    }
}
