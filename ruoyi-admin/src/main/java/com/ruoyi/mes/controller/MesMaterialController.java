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
import com.ruoyi.mes.domain.MesMaterial;
import com.ruoyi.mes.service.IMesMaterialService;

/**
 * 物料主数据Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/material")
public class MesMaterialController extends BaseController
{
    @Autowired
    private IMesMaterialService mesMaterialService;

    @PreAuthorize("@ss.hasPermi('mes:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesMaterial mesMaterial)
    {
        startPage();
        List<MesMaterial> list = mesMaterialService.selectMesMaterialList(mesMaterial);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:material:export')")
    @Log(title = "物料主数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesMaterial mesMaterial)
    {
        List<MesMaterial> list = mesMaterialService.selectMesMaterialList(mesMaterial);
        ExcelUtil<MesMaterial> util = new ExcelUtil<MesMaterial>(MesMaterial.class);
        util.exportExcel(response, list, "物料主数据数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:material:query')")
    @GetMapping(value = "/{materialId}")
    public AjaxResult getInfo(@PathVariable("materialId") Long materialId)
    {
        return success(mesMaterialService.selectMesMaterialById(materialId));
    }

    @PreAuthorize("@ss.hasPermi('mes:material:add')")
    @Log(title = "物料主数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesMaterial mesMaterial)
    {
        mesMaterial.setCreateBy(getUsername());
        return toAjax(mesMaterialService.insertMesMaterial(mesMaterial));
    }

    @PreAuthorize("@ss.hasPermi('mes:material:edit')")
    @Log(title = "物料主数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesMaterial mesMaterial)
    {
        mesMaterial.setUpdateBy(getUsername());
        return toAjax(mesMaterialService.updateMesMaterial(mesMaterial));
    }

    @PreAuthorize("@ss.hasPermi('mes:material:remove')")
    @Log(title = "物料主数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{materialIds}")
    public AjaxResult remove(@PathVariable Long[] materialIds)
    {
        return toAjax(mesMaterialService.deleteMesMaterialByIds(materialIds));
    }
}
