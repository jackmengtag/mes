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
import com.ruoyi.mes.md.domain.MdBom;
import com.ruoyi.mes.md.service.IMdBomService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * BOM主数据Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/bom")
public class MdBomController extends BaseController
{
    @Autowired
    private IMdBomService mdBomService;

    /**
     * 查询BOM主数据列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdBom mdBom)
    {
        startPage();
        List<MdBom> list = mdBomService.selectMdBomList(mdBom);
        return getDataTable(list);
    }

    /**
     * 导出BOM主数据列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:export')")
    @Log(title = "BOM主数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdBom mdBom)
    {
        List<MdBom> list = mdBomService.selectMdBomList(mdBom);
        ExcelUtil<MdBom> util = new ExcelUtil<MdBom>(MdBom.class);
        util.exportExcel(response, list, "BOM主数据数据");
    }

    /**
     * 获取BOM主数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdBomService.selectMdBomById(id));
    }

    /**
     * 新增BOM主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:add')")
    @Log(title = "BOM主数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdBom mdBom)
    {
        mdBom.setCreateBy(getUsername());
        return toAjax(mdBomService.insertMdBom(mdBom));
    }

    /**
     * 修改BOM主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:edit')")
    @Log(title = "BOM主数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdBom mdBom)
    {
        mdBom.setUpdateBy(getUsername());
        return toAjax(mdBomService.updateMdBom(mdBom));
    }

    /**
     * 删除BOM主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bom:remove')")
    @Log(title = "BOM主数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdBomService.deleteMdBomByIds(ids));
    }
}
