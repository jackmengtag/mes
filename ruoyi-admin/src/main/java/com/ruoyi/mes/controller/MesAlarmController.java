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
import com.ruoyi.mes.domain.MesAlarm;
import com.ruoyi.mes.service.IMesAlarmService;

/**
 * 设备报警Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/alarm")
public class MesAlarmController extends BaseController
{
    @Autowired
    private IMesAlarmService mesAlarmService;

    @PreAuthorize("@ss.hasPermi('mes:alarm:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesAlarm mesAlarm)
    {
        startPage();
        List<MesAlarm> list = mesAlarmService.selectMesAlarmList(mesAlarm);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:alarm:export')")
    @Log(title = "设备报警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesAlarm mesAlarm)
    {
        List<MesAlarm> list = mesAlarmService.selectMesAlarmList(mesAlarm);
        ExcelUtil<MesAlarm> util = new ExcelUtil<MesAlarm>(MesAlarm.class);
        util.exportExcel(response, list, "设备报警数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:alarm:query')")
    @GetMapping(value = "/{alarmId}")
    public AjaxResult getInfo(@PathVariable("alarmId") Long alarmId)
    {
        return success(mesAlarmService.selectMesAlarmById(alarmId));
    }

    @PreAuthorize("@ss.hasPermi('mes:alarm:add')")
    @Log(title = "设备报警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesAlarm mesAlarm)
    {
        mesAlarm.setCreateBy(getUsername());
        return toAjax(mesAlarmService.insertMesAlarm(mesAlarm));
    }

    @PreAuthorize("@ss.hasPermi('mes:alarm:edit')")
    @Log(title = "设备报警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesAlarm mesAlarm)
    {
        mesAlarm.setUpdateBy(getUsername());
        return toAjax(mesAlarmService.updateMesAlarm(mesAlarm));
    }

    @PreAuthorize("@ss.hasPermi('mes:alarm:remove')")
    @Log(title = "设备报警", businessType = BusinessType.DELETE)
    @DeleteMapping("/{alarmIds}")
    public AjaxResult remove(@PathVariable Long[] alarmIds)
    {
        return toAjax(mesAlarmService.deleteMesAlarmByIds(alarmIds));
    }
}
