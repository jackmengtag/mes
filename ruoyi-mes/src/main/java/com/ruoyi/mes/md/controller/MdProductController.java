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
import com.ruoyi.mes.md.domain.MdProduct;
import com.ruoyi.mes.md.service.IMdProductService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品主数据Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/product")
public class MdProductController extends BaseController
{
    @Autowired
    private IMdProductService mdProductService;

    /**
     * 查询产品主数据列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdProduct mdProduct)
    {
        startPage();
        List<MdProduct> list = mdProductService.selectMdProductList(mdProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品主数据列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:product:export')")
    @Log(title = "产品主数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdProduct mdProduct)
    {
        List<MdProduct> list = mdProductService.selectMdProductList(mdProduct);
        ExcelUtil<MdProduct> util = new ExcelUtil<MdProduct>(MdProduct.class);
        util.exportExcel(response, list, "产品主数据数据");
    }

    /**
     * 获取产品主数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdProductService.selectMdProductById(id));
    }

    /**
     * 新增产品主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:product:add')")
    @Log(title = "产品主数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdProduct mdProduct)
    {
        mdProduct.setCreateBy(getUsername());
        return toAjax(mdProductService.insertMdProduct(mdProduct));
    }

    /**
     * 修改产品主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:product:edit')")
    @Log(title = "产品主数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdProduct mdProduct)
    {
        mdProduct.setUpdateBy(getUsername());
        return toAjax(mdProductService.updateMdProduct(mdProduct));
    }

    /**
     * 删除产品主数据
     */
    @PreAuthorize("@ss.hasPermi('mes:md:product:remove')")
    @Log(title = "产品主数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdProductService.deleteMdProductByIds(ids));
    }
}
