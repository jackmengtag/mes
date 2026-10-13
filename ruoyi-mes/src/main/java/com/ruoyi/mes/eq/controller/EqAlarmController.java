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
import com.ruoyi.mes.eq.domain.EqAlarm;
import com.ruoyi.mes.eq.service.IEqAlarmService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备报警Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/eq/alarm")
public class EqAlarmController extends BaseController
{
    @Autowired
    private IEqAlarmService eqAlarmService;

    /**
     * 查询设备报警列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:alarm:list')")
    @GetMapping("/list")
    public TableDataInfo list(EqAlarm eqAlarm)
    {
        startPage();
        List<EqAlarm> list = eqAlarmService.selectEqAlarmList(eqAlarm);
        return getDataTable(list);
    }

    /**
     * 导出设备报警列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:alarm:export')")
    @Log(title = "设备报警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EqAlarm eqAlarm)
    {
        List<EqAlarm> list = eqAlarmService.selectEqAlarmList(eqAlarm);
        ExcelUtil<EqAlarm> util = new ExcelUtil<EqAlarm>(EqAlarm.class);
        util.exportExcel(response, list, "设备报警数据");
    }

    /**
     * 获取设备报警详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:alarm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eqAlarmService.selectEqAlarmById(id));
    }

    /**
     * 新增设备报警
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:alarm:add')")
    @Log(title = "设备报警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EqAlarm eqAlarm)
    {
        eqAlarm.setCreateBy(getUsername());
        return toAjax(eqAlarmService.insertEqAlarm(eqAlarm));
    }

    /**
     * 修改设备报警
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:alarm:edit')")
    @Log(title = "设备报警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EqAlarm eqAlarm)
    {
        eqAlarm.setUpdateBy(getUsername());
        return toAjax(eqAlarmService.updateEqAlarm(eqAlarm));
    }

    /**
     * 删除设备报警
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:alarm:remove')")
    @Log(title = "设备报警", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eqAlarmService.deleteEqAlarmByIds(ids));
    }
}
