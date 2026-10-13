package com.ruoyi.mes.md.controller;

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
import com.ruoyi.mes.md.domain.MdInspectionPlan;
import com.ruoyi.mes.md.service.IMdInspectionPlanService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 检验方案Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/inspectionPlan")
public class MdInspectionPlanController extends BaseController
{
    @Autowired
    private IMdInspectionPlanService mdInspectionPlanService;

    /**
     * 查询检验方案列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlan:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdInspectionPlan mdInspectionPlan)
    {
        startPage();
        List<MdInspectionPlan> list = mdInspectionPlanService.selectMdInspectionPlanList(mdInspectionPlan);
        return getDataTable(list);
    }

    /**
     * 导出检验方案列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlan:export')")
    @Log(title = "检验方案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdInspectionPlan mdInspectionPlan)
    {
        List<MdInspectionPlan> list = mdInspectionPlanService.selectMdInspectionPlanList(mdInspectionPlan);
        ExcelUtil<MdInspectionPlan> util = new ExcelUtil<MdInspectionPlan>(MdInspectionPlan.class);
        util.exportExcel(response, list, "检验方案数据");
    }

    /**
     * 获取检验方案详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdInspectionPlanService.selectMdInspectionPlanById(id));
    }

    /**
     * 新增检验方案
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlan:add')")
    @Log(title = "检验方案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdInspectionPlan mdInspectionPlan)
    {
        mdInspectionPlan.setCreateBy(getUsername());
        return toAjax(mdInspectionPlanService.insertMdInspectionPlan(mdInspectionPlan));
    }

    /**
     * 修改检验方案
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlan:edit')")
    @Log(title = "检验方案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdInspectionPlan mdInspectionPlan)
    {
        mdInspectionPlan.setUpdateBy(getUsername());
        return toAjax(mdInspectionPlanService.updateMdInspectionPlan(mdInspectionPlan));
    }

    /**
     * 删除检验方案
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlan:remove')")
    @Log(title = "检验方案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdInspectionPlanService.deleteMdInspectionPlanByIds(ids));
    }
}
