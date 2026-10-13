package com.ruoyi.mes.ps.controller;

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
import com.ruoyi.mes.ps.domain.PsPlan;
import com.ruoyi.mes.ps.service.IPsPlanService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 生产计划Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ps/plan")
public class PsPlanController extends BaseController
{
    @Autowired
    private IPsPlanService psPlanService;

    /**
     * 查询生产计划列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsPlan psPlan)
    {
        startPage();
        List<PsPlan> list = psPlanService.selectPsPlanList(psPlan);
        return getDataTable(list);
    }

    /**
     * 导出生产计划列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:plan:export')")
    @Log(title = "生产计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsPlan psPlan)
    {
        List<PsPlan> list = psPlanService.selectPsPlanList(psPlan);
        ExcelUtil<PsPlan> util = new ExcelUtil<PsPlan>(PsPlan.class);
        util.exportExcel(response, list, "生产计划数据");
    }

    /**
     * 获取生产计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:plan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(psPlanService.selectPsPlanById(id));
    }

    /**
     * 新增生产计划
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:plan:add')")
    @Log(title = "生产计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsPlan psPlan)
    {
        psPlan.setCreateBy(getUsername());
        return toAjax(psPlanService.insertPsPlan(psPlan));
    }

    /**
     * 修改生产计划
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:plan:edit')")
    @Log(title = "生产计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsPlan psPlan)
    {
        psPlan.setUpdateBy(getUsername());
        return toAjax(psPlanService.updatePsPlan(psPlan));
    }

    /**
     * 删除生产计划
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:plan:remove')")
    @Log(title = "生产计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psPlanService.deletePsPlanByIds(ids));
    }
}
