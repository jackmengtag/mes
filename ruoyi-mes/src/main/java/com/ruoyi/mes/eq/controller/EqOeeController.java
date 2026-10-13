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
import com.ruoyi.mes.eq.domain.EqOee;
import com.ruoyi.mes.eq.service.IEqOeeService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * OEEController
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/eq/oee")
public class EqOeeController extends BaseController
{
    @Autowired
    private IEqOeeService eqOeeService;

    /**
     * 查询OEE列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:oee:list')")
    @GetMapping("/list")
    public TableDataInfo list(EqOee eqOee)
    {
        startPage();
        List<EqOee> list = eqOeeService.selectEqOeeList(eqOee);
        return getDataTable(list);
    }

    /**
     * 导出OEE列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:oee:export')")
    @Log(title = "OEE", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EqOee eqOee)
    {
        List<EqOee> list = eqOeeService.selectEqOeeList(eqOee);
        ExcelUtil<EqOee> util = new ExcelUtil<EqOee>(EqOee.class);
        util.exportExcel(response, list, "OEE数据");
    }

    /**
     * 获取OEE详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:oee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eqOeeService.selectEqOeeById(id));
    }

    /**
     * 新增OEE
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:oee:add')")
    @Log(title = "OEE", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EqOee eqOee)
    {
        eqOee.setCreateBy(getUsername());
        return toAjax(eqOeeService.insertEqOee(eqOee));
    }

    /**
     * 修改OEE
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:oee:edit')")
    @Log(title = "OEE", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EqOee eqOee)
    {
        eqOee.setUpdateBy(getUsername());
        return toAjax(eqOeeService.updateEqOee(eqOee));
    }

    /**
     * 删除OEE
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:oee:remove')")
    @Log(title = "OEE", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eqOeeService.deleteEqOeeByIds(ids));
    }
}
