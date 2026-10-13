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
import com.ruoyi.mes.eq.domain.EqStatus;
import com.ruoyi.mes.eq.service.IEqStatusService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备状态Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/eq/status")
public class EqStatusController extends BaseController
{
    @Autowired
    private IEqStatusService eqStatusService;

    /**
     * 查询设备状态列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:status:list')")
    @GetMapping("/list")
    public TableDataInfo list(EqStatus eqStatus)
    {
        startPage();
        List<EqStatus> list = eqStatusService.selectEqStatusList(eqStatus);
        return getDataTable(list);
    }

    /**
     * 导出设备状态列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:status:export')")
    @Log(title = "设备状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EqStatus eqStatus)
    {
        List<EqStatus> list = eqStatusService.selectEqStatusList(eqStatus);
        ExcelUtil<EqStatus> util = new ExcelUtil<EqStatus>(EqStatus.class);
        util.exportExcel(response, list, "设备状态数据");
    }

    /**
     * 获取设备状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:status:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eqStatusService.selectEqStatusById(id));
    }

    /**
     * 新增设备状态
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:status:add')")
    @Log(title = "设备状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EqStatus eqStatus)
    {
        eqStatus.setCreateBy(getUsername());
        return toAjax(eqStatusService.insertEqStatus(eqStatus));
    }

    /**
     * 修改设备状态
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:status:edit')")
    @Log(title = "设备状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EqStatus eqStatus)
    {
        eqStatus.setUpdateBy(getUsername());
        return toAjax(eqStatusService.updateEqStatus(eqStatus));
    }

    /**
     * 删除设备状态
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:status:remove')")
    @Log(title = "设备状态", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eqStatusService.deleteEqStatusByIds(ids));
    }
}
