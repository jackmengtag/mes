package com.ruoyi.mes.eq.controller;

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
import com.ruoyi.mes.eq.domain.EqMaintenance;
import com.ruoyi.mes.eq.service.IEqMaintenanceService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备维护与停机Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/eq/maintenance")
public class EqMaintenanceController extends BaseController
{
    @Autowired
    private IEqMaintenanceService eqMaintenanceService;

    /**
     * 查询设备维护与停机列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:maintenance:list')")
    @GetMapping("/list")
    public TableDataInfo list(EqMaintenance eqMaintenance)
    {
        startPage();
        List<EqMaintenance> list = eqMaintenanceService.selectEqMaintenanceList(eqMaintenance);
        return getDataTable(list);
    }

    /**
     * 导出设备维护与停机列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:maintenance:export')")
    @Log(title = "设备维护与停机", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EqMaintenance eqMaintenance)
    {
        List<EqMaintenance> list = eqMaintenanceService.selectEqMaintenanceList(eqMaintenance);
        ExcelUtil<EqMaintenance> util = new ExcelUtil<EqMaintenance>(EqMaintenance.class);
        util.exportExcel(response, list, "设备维护与停机数据");
    }

    /**
     * 获取设备维护与停机详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:maintenance:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eqMaintenanceService.selectEqMaintenanceById(id));
    }

    /**
     * 新增设备维护与停机
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:maintenance:add')")
    @Log(title = "设备维护与停机", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EqMaintenance eqMaintenance)
    {
        eqMaintenance.setCreateBy(getUsername());
        return toAjax(eqMaintenanceService.insertEqMaintenance(eqMaintenance));
    }

    /**
     * 修改设备维护与停机
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:maintenance:edit')")
    @Log(title = "设备维护与停机", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EqMaintenance eqMaintenance)
    {
        eqMaintenance.setUpdateBy(getUsername());
        return toAjax(eqMaintenanceService.updateEqMaintenance(eqMaintenance));
    }

    /**
     * 删除设备维护与停机
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:maintenance:remove')")
    @Log(title = "设备维护与停机", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eqMaintenanceService.deleteEqMaintenanceByIds(ids));
    }
}
