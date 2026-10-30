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
import com.ruoyi.mes.domain.MesArrival;
import com.ruoyi.mes.service.IMesArrivalService;

/**
 * 采购到货Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/arrival")
public class MesArrivalController extends BaseController
{
    @Autowired
    private IMesArrivalService mesArrivalService;

    @PreAuthorize("@ss.hasPermi('mes:arrival:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesArrival mesArrival)
    {
        startPage();
        List<MesArrival> list = mesArrivalService.selectMesArrivalList(mesArrival);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:arrival:export')")
    @Log(title = "采购到货", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesArrival mesArrival)
    {
        List<MesArrival> list = mesArrivalService.selectMesArrivalList(mesArrival);
        ExcelUtil<MesArrival> util = new ExcelUtil<MesArrival>(MesArrival.class);
        util.exportExcel(response, list, "采购到货数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:arrival:query')")
    @GetMapping(value = "/{arrivalId}")
    public AjaxResult getInfo(@PathVariable("arrivalId") Long arrivalId)
    {
        return success(mesArrivalService.selectMesArrivalById(arrivalId));
    }

    @PreAuthorize("@ss.hasPermi('mes:arrival:add')")
    @Log(title = "采购到货", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesArrival mesArrival)
    {
        mesArrival.setCreateBy(getUsername());
        return toAjax(mesArrivalService.insertMesArrival(mesArrival));
    }

    @PreAuthorize("@ss.hasPermi('mes:arrival:edit')")
    @Log(title = "采购到货", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesArrival mesArrival)
    {
        mesArrival.setUpdateBy(getUsername());
        return toAjax(mesArrivalService.updateMesArrival(mesArrival));
    }

    @PreAuthorize("@ss.hasPermi('mes:arrival:remove')")
    @Log(title = "采购到货", businessType = BusinessType.DELETE)
    @DeleteMapping("/{arrivalIds}")
    public AjaxResult remove(@PathVariable Long[] arrivalIds)
    {
        return toAjax(mesArrivalService.deleteMesArrivalByIds(arrivalIds));
    }
}
