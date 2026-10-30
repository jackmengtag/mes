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
import com.ruoyi.mes.domain.MesPlan;
import com.ruoyi.mes.service.IMesPlanService;

/**
 * 生产计划Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/plan")
public class MesPlanController extends BaseController
{
    @Autowired
    private IMesPlanService mesPlanService;

    @PreAuthorize("@ss.hasPermi('mes:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesPlan mesPlan)
    {
        startPage();
        List<MesPlan> list = mesPlanService.selectMesPlanList(mesPlan);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:plan:export')")
    @Log(title = "生产计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesPlan mesPlan)
    {
        List<MesPlan> list = mesPlanService.selectMesPlanList(mesPlan);
        ExcelUtil<MesPlan> util = new ExcelUtil<MesPlan>(MesPlan.class);
        util.exportExcel(response, list, "生产计划数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:plan:query')")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") Long planId)
    {
        return success(mesPlanService.selectMesPlanById(planId));
    }

    @PreAuthorize("@ss.hasPermi('mes:plan:add')")
    @Log(title = "生产计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesPlan mesPlan)
    {
        mesPlan.setCreateBy(getUsername());
        return toAjax(mesPlanService.insertMesPlan(mesPlan));
    }

    @PreAuthorize("@ss.hasPermi('mes:plan:edit')")
    @Log(title = "生产计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesPlan mesPlan)
    {
        mesPlan.setUpdateBy(getUsername());
        return toAjax(mesPlanService.updateMesPlan(mesPlan));
    }

    @PreAuthorize("@ss.hasPermi('mes:plan:remove')")
    @Log(title = "生产计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable Long[] planIds)
    {
        return toAjax(mesPlanService.deleteMesPlanByIds(planIds));
    }
}
