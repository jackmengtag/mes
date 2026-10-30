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
import com.ruoyi.mes.domain.MesEquipment;
import com.ruoyi.mes.service.IMesEquipmentService;

/**
 * 设备台账Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/equipment")
public class MesEquipmentController extends BaseController
{
    @Autowired
    private IMesEquipmentService mesEquipmentService;

    @PreAuthorize("@ss.hasPermi('mes:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesEquipment mesEquipment)
    {
        startPage();
        List<MesEquipment> list = mesEquipmentService.selectMesEquipmentList(mesEquipment);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:equipment:export')")
    @Log(title = "设备台账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesEquipment mesEquipment)
    {
        List<MesEquipment> list = mesEquipmentService.selectMesEquipmentList(mesEquipment);
        ExcelUtil<MesEquipment> util = new ExcelUtil<MesEquipment>(MesEquipment.class);
        util.exportExcel(response, list, "设备台账数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:equipment:query')")
    @GetMapping(value = "/{equipmentId}")
    public AjaxResult getInfo(@PathVariable("equipmentId") Long equipmentId)
    {
        return success(mesEquipmentService.selectMesEquipmentById(equipmentId));
    }

    @PreAuthorize("@ss.hasPermi('mes:equipment:add')")
    @Log(title = "设备台账", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesEquipment mesEquipment)
    {
        mesEquipment.setCreateBy(getUsername());
        return toAjax(mesEquipmentService.insertMesEquipment(mesEquipment));
    }

    @PreAuthorize("@ss.hasPermi('mes:equipment:edit')")
    @Log(title = "设备台账", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesEquipment mesEquipment)
    {
        mesEquipment.setUpdateBy(getUsername());
        return toAjax(mesEquipmentService.updateMesEquipment(mesEquipment));
    }

    @PreAuthorize("@ss.hasPermi('mes:equipment:remove')")
    @Log(title = "设备台账", businessType = BusinessType.DELETE)
    @DeleteMapping("/{equipmentIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentIds)
    {
        return toAjax(mesEquipmentService.deleteMesEquipmentByIds(equipmentIds));
    }
}
