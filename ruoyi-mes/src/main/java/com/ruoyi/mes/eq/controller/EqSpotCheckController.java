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
import com.ruoyi.mes.eq.domain.EqSpotCheck;
import com.ruoyi.mes.eq.service.IEqSpotCheckService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备点检Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/eq/spotCheck")
public class EqSpotCheckController extends BaseController
{
    @Autowired
    private IEqSpotCheckService eqSpotCheckService;

    /**
     * 查询设备点检列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:spotCheck:list')")
    @GetMapping("/list")
    public TableDataInfo list(EqSpotCheck eqSpotCheck)
    {
        startPage();
        List<EqSpotCheck> list = eqSpotCheckService.selectEqSpotCheckList(eqSpotCheck);
        return getDataTable(list);
    }

    /**
     * 导出设备点检列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:spotCheck:export')")
    @Log(title = "设备点检", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EqSpotCheck eqSpotCheck)
    {
        List<EqSpotCheck> list = eqSpotCheckService.selectEqSpotCheckList(eqSpotCheck);
        ExcelUtil<EqSpotCheck> util = new ExcelUtil<EqSpotCheck>(EqSpotCheck.class);
        util.exportExcel(response, list, "设备点检数据");
    }

    /**
     * 获取设备点检详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:spotCheck:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eqSpotCheckService.selectEqSpotCheckById(id));
    }

    /**
     * 新增设备点检
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:spotCheck:add')")
    @Log(title = "设备点检", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EqSpotCheck eqSpotCheck)
    {
        eqSpotCheck.setCreateBy(getUsername());
        return toAjax(eqSpotCheckService.insertEqSpotCheck(eqSpotCheck));
    }

    /**
     * 修改设备点检
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:spotCheck:edit')")
    @Log(title = "设备点检", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EqSpotCheck eqSpotCheck)
    {
        eqSpotCheck.setUpdateBy(getUsername());
        return toAjax(eqSpotCheckService.updateEqSpotCheck(eqSpotCheck));
    }

    /**
     * 删除设备点检
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:spotCheck:remove')")
    @Log(title = "设备点检", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eqSpotCheckService.deleteEqSpotCheckByIds(ids));
    }
}
