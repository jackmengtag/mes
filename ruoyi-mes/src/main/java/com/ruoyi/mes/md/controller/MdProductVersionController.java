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
import com.ruoyi.mes.md.domain.MdProductVersion;
import com.ruoyi.mes.md.service.IMdProductVersionService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品版本Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/productVersion")
public class MdProductVersionController extends BaseController
{
    @Autowired
    private IMdProductVersionService mdProductVersionService;

    /**
     * 查询产品版本列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:productVersion:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdProductVersion mdProductVersion)
    {
        startPage();
        List<MdProductVersion> list = mdProductVersionService.selectMdProductVersionList(mdProductVersion);
        return getDataTable(list);
    }

    /**
     * 导出产品版本列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:productVersion:export')")
    @Log(title = "产品版本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdProductVersion mdProductVersion)
    {
        List<MdProductVersion> list = mdProductVersionService.selectMdProductVersionList(mdProductVersion);
        ExcelUtil<MdProductVersion> util = new ExcelUtil<MdProductVersion>(MdProductVersion.class);
        util.exportExcel(response, list, "产品版本数据");
    }

    /**
     * 获取产品版本详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:productVersion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdProductVersionService.selectMdProductVersionById(id));
    }

    /**
     * 新增产品版本
     */
    @PreAuthorize("@ss.hasPermi('mes:md:productVersion:add')")
    @Log(title = "产品版本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdProductVersion mdProductVersion)
    {
        mdProductVersion.setCreateBy(getUsername());
        return toAjax(mdProductVersionService.insertMdProductVersion(mdProductVersion));
    }

    /**
     * 修改产品版本
     */
    @PreAuthorize("@ss.hasPermi('mes:md:productVersion:edit')")
    @Log(title = "产品版本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdProductVersion mdProductVersion)
    {
        mdProductVersion.setUpdateBy(getUsername());
        return toAjax(mdProductVersionService.updateMdProductVersion(mdProductVersion));
    }

    /**
     * 删除产品版本
     */
    @PreAuthorize("@ss.hasPermi('mes:md:productVersion:remove')")
    @Log(title = "产品版本", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdProductVersionService.deleteMdProductVersionByIds(ids));
    }
}
