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
import com.ruoyi.mes.domain.MesInspection;
import com.ruoyi.mes.service.IMesInspectionService;

/**
 * 质量检验Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/inspection")
public class MesInspectionController extends BaseController
{
    @Autowired
    private IMesInspectionService mesInspectionService;

    @PreAuthorize("@ss.hasPermi('mes:inspection:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesInspection mesInspection)
    {
        startPage();
        List<MesInspection> list = mesInspectionService.selectMesInspectionList(mesInspection);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:inspection:export')")
    @Log(title = "质量检验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesInspection mesInspection)
    {
        List<MesInspection> list = mesInspectionService.selectMesInspectionList(mesInspection);
        ExcelUtil<MesInspection> util = new ExcelUtil<MesInspection>(MesInspection.class);
        util.exportExcel(response, list, "质量检验数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:inspection:query')")
    @GetMapping(value = "/{inspectionId}")
    public AjaxResult getInfo(@PathVariable("inspectionId") Long inspectionId)
    {
        return success(mesInspectionService.selectMesInspectionById(inspectionId));
    }

    @PreAuthorize("@ss.hasPermi('mes:inspection:add')")
    @Log(title = "质量检验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesInspection mesInspection)
    {
        mesInspection.setCreateBy(getUsername());
        return toAjax(mesInspectionService.insertMesInspection(mesInspection));
    }

    @PreAuthorize("@ss.hasPermi('mes:inspection:edit')")
    @Log(title = "质量检验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesInspection mesInspection)
    {
        mesInspection.setUpdateBy(getUsername());
        return toAjax(mesInspectionService.updateMesInspection(mesInspection));
    }

    @PreAuthorize("@ss.hasPermi('mes:inspection:remove')")
    @Log(title = "质量检验", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inspectionIds}")
    public AjaxResult remove(@PathVariable Long[] inspectionIds)
    {
        return toAjax(mesInspectionService.deleteMesInspectionByIds(inspectionIds));
    }
}
