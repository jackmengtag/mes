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
import com.ruoyi.mes.eq.domain.EqBind;
import com.ruoyi.mes.eq.service.IEqBindService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备与工单绑定Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/eq/bind")
public class EqBindController extends BaseController
{
    @Autowired
    private IEqBindService eqBindService;

    /**
     * 查询设备与工单绑定列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:bind:list')")
    @GetMapping("/list")
    public TableDataInfo list(EqBind eqBind)
    {
        startPage();
        List<EqBind> list = eqBindService.selectEqBindList(eqBind);
        return getDataTable(list);
    }

    /**
     * 导出设备与工单绑定列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:bind:export')")
    @Log(title = "设备与工单绑定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EqBind eqBind)
    {
        List<EqBind> list = eqBindService.selectEqBindList(eqBind);
        ExcelUtil<EqBind> util = new ExcelUtil<EqBind>(EqBind.class);
        util.exportExcel(response, list, "设备与工单绑定数据");
    }

    /**
     * 获取设备与工单绑定详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:bind:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eqBindService.selectEqBindById(id));
    }

    /**
     * 新增设备与工单绑定
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:bind:add')")
    @Log(title = "设备与工单绑定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EqBind eqBind)
    {
        eqBind.setCreateBy(getUsername());
        return toAjax(eqBindService.insertEqBind(eqBind));
    }

    /**
     * 修改设备与工单绑定
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:bind:edit')")
    @Log(title = "设备与工单绑定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EqBind eqBind)
    {
        eqBind.setUpdateBy(getUsername());
        return toAjax(eqBindService.updateEqBind(eqBind));
    }

    /**
     * 删除设备与工单绑定
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:bind:remove')")
    @Log(title = "设备与工单绑定", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eqBindService.deleteEqBindByIds(ids));
    }
}
