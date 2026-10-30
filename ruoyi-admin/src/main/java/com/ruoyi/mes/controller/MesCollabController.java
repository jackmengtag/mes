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
import com.ruoyi.mes.domain.MesCollab;
import com.ruoyi.mes.service.IMesCollabService;

/**
 * 供应链协同Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/collab")
public class MesCollabController extends BaseController
{
    @Autowired
    private IMesCollabService mesCollabService;

    @PreAuthorize("@ss.hasPermi('mes:collab:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesCollab mesCollab)
    {
        startPage();
        List<MesCollab> list = mesCollabService.selectMesCollabList(mesCollab);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:collab:export')")
    @Log(title = "供应链协同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesCollab mesCollab)
    {
        List<MesCollab> list = mesCollabService.selectMesCollabList(mesCollab);
        ExcelUtil<MesCollab> util = new ExcelUtil<MesCollab>(MesCollab.class);
        util.exportExcel(response, list, "供应链协同数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:collab:query')")
    @GetMapping(value = "/{collabId}")
    public AjaxResult getInfo(@PathVariable("collabId") Long collabId)
    {
        return success(mesCollabService.selectMesCollabById(collabId));
    }

    @PreAuthorize("@ss.hasPermi('mes:collab:add')")
    @Log(title = "供应链协同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesCollab mesCollab)
    {
        mesCollab.setCreateBy(getUsername());
        return toAjax(mesCollabService.insertMesCollab(mesCollab));
    }

    @PreAuthorize("@ss.hasPermi('mes:collab:edit')")
    @Log(title = "供应链协同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesCollab mesCollab)
    {
        mesCollab.setUpdateBy(getUsername());
        return toAjax(mesCollabService.updateMesCollab(mesCollab));
    }

    @PreAuthorize("@ss.hasPermi('mes:collab:remove')")
    @Log(title = "供应链协同", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collabIds}")
    public AjaxResult remove(@PathVariable Long[] collabIds)
    {
        return toAjax(mesCollabService.deleteMesCollabByIds(collabIds));
    }
}
