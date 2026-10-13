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
import com.ruoyi.mes.ex.domain.ExWorkHour;
import com.ruoyi.mes.ex.service.IExWorkHourService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工时记录Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ex/workHour")
public class ExWorkHourController extends BaseController
{
    @Autowired
    private IExWorkHourService exWorkHourService;

    /**
     * 查询工时记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:workHour:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExWorkHour exWorkHour)
    {
        startPage();
        List<ExWorkHour> list = exWorkHourService.selectExWorkHourList(exWorkHour);
        return getDataTable(list);
    }

    /**
     * 导出工时记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:workHour:export')")
    @Log(title = "工时记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExWorkHour exWorkHour)
    {
        List<ExWorkHour> list = exWorkHourService.selectExWorkHourList(exWorkHour);
        ExcelUtil<ExWorkHour> util = new ExcelUtil<ExWorkHour>(ExWorkHour.class);
        util.exportExcel(response, list, "工时记录数据");
    }

    /**
     * 获取工时记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:workHour:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exWorkHourService.selectExWorkHourById(id));
    }

    /**
     * 新增工时记录
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:workHour:add')")
    @Log(title = "工时记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExWorkHour exWorkHour)
    {
        exWorkHour.setCreateBy(getUsername());
        return toAjax(exWorkHourService.insertExWorkHour(exWorkHour));
    }

    /**
     * 修改工时记录
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:workHour:edit')")
    @Log(title = "工时记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExWorkHour exWorkHour)
    {
        exWorkHour.setUpdateBy(getUsername());
        return toAjax(exWorkHourService.updateExWorkHour(exWorkHour));
    }

    /**
     * 删除工时记录
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:workHour:remove')")
    @Log(title = "工时记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exWorkHourService.deleteExWorkHourByIds(ids));
    }
}
