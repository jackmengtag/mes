package com.ruoyi.mes.mt.controller;

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
import com.ruoyi.mes.mt.domain.MtMaterialRequirement;
import com.ruoyi.mes.mt.service.IMtMaterialRequirementService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料需求Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/mt/requirement")
public class MtMaterialRequirementController extends BaseController
{
    @Autowired
    private IMtMaterialRequirementService mtMaterialRequirementService;

    /**
     * 查询物料需求列表
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:list')")
    @GetMapping("/list")
    public TableDataInfo list(MtMaterialRequirement mtMaterialRequirement)
    {
        startPage();
        List<MtMaterialRequirement> list = mtMaterialRequirementService.selectMtMaterialRequirementList(mtMaterialRequirement);
        return getDataTable(list);
    }

    /**
     * 导出物料需求列表
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:export')")
    @Log(title = "物料需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MtMaterialRequirement mtMaterialRequirement)
    {
        List<MtMaterialRequirement> list = mtMaterialRequirementService.selectMtMaterialRequirementList(mtMaterialRequirement);
        ExcelUtil<MtMaterialRequirement> util = new ExcelUtil<MtMaterialRequirement>(MtMaterialRequirement.class);
        util.exportExcel(response, list, "物料需求数据");
    }

    /**
     * 获取物料需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mtMaterialRequirementService.selectMtMaterialRequirementById(id));
    }

    /**
     * 新增物料需求
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:add')")
    @Log(title = "物料需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MtMaterialRequirement mtMaterialRequirement)
    {
        mtMaterialRequirement.setCreateBy(getUsername());
        return toAjax(mtMaterialRequirementService.insertMtMaterialRequirement(mtMaterialRequirement));
    }

    /**
     * 修改物料需求
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:edit')")
    @Log(title = "物料需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MtMaterialRequirement mtMaterialRequirement)
    {
        mtMaterialRequirement.setUpdateBy(getUsername());
        return toAjax(mtMaterialRequirementService.updateMtMaterialRequirement(mtMaterialRequirement));
    }

    /**
     * 删除物料需求
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:remove')")
    @Log(title = "物料需求", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mtMaterialRequirementService.deleteMtMaterialRequirementByIds(ids));
    }
}
