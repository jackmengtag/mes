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
import com.ruoyi.mes.md.domain.MdMaterial;
import com.ruoyi.mes.md.service.IMdMaterialService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料主数据Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/material")
public class MdMaterialController extends BaseController
{
    @Autowired
    private IMdMaterialService mdMaterialService;

    /**
     * 查询物料主数据列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdMaterial mdMaterial)
    {
        startPage();
        List<MdMaterial> list = mdMaterialService.selectMdMaterialList(mdMaterial);
        return getDataTable(list);
    }

    /**
     * 导出物料主数据列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:material:export')")
    @Log(title = "物料主数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdMaterial mdMaterial)
    {
        List<MdMaterial> list = mdMaterialService.selectMdMaterialList(mdMaterial);
        ExcelUtil<MdMaterial> util = new ExcelUtil<MdMaterial>(MdMaterial.class);
        util.exportExcel(response, list, "物料主数据数据");
    }

    /**
     * 获取物料主数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:material:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdMaterialService.selectMdMaterialById(id));
    }

    /**
     * 新增物料主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:material:add')")
    @Log(title = "物料主数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdMaterial mdMaterial)
    {
        mdMaterial.setCreateBy(getUsername());
        return toAjax(mdMaterialService.insertMdMaterial(mdMaterial));
    }

    /**
     * 修改物料主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:material:edit')")
    @Log(title = "物料主数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdMaterial mdMaterial)
    {
        mdMaterial.setUpdateBy(getUsername());
        return toAjax(mdMaterialService.updateMdMaterial(mdMaterial));
    }

    /**
     * 删除物料主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:material:remove')")
    @Log(title = "物料主数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdMaterialService.deleteMdMaterialByIds(ids));
    }
}
