package com.ruoyi.mes.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.mes.domain.MesSupplier;
import com.ruoyi.mes.service.IMesSupplierService;

/**
 * 供应商档案Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/supplier")
public class MesSupplierController extends BaseController
{
    @Autowired
    private IMesSupplierService mesSupplierService;

    @PreAuthorize("@ss.hasPermi('mes:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesSupplier mesSupplier)
    {
        startPage();
        List<MesSupplier> list = mesSupplierService.selectMesSupplierList(mesSupplier);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:supplier:export')")
    @Log(title = "供应商档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesSupplier mesSupplier)
    {
        List<MesSupplier> list = mesSupplierService.selectMesSupplierList(mesSupplier);
        ExcelUtil<MesSupplier> util = new ExcelUtil<MesSupplier>(MesSupplier.class);
        util.exportExcel(response, list, "供应商档案数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:supplier:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") Long supplierId)
    {
        return success(mesSupplierService.selectMesSupplierById(supplierId));
    }

    @PreAuthorize("@ss.hasPermi('mes:supplier:add')")
    @Log(title = "供应商档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesSupplier mesSupplier)
    {
        mesSupplier.setCreateBy(getUsername());
        return toAjax(mesSupplierService.insertMesSupplier(mesSupplier));
    }

    @PreAuthorize("@ss.hasPermi('mes:supplier:edit')")
    @Log(title = "供应商档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesSupplier mesSupplier)
    {
        mesSupplier.setUpdateBy(getUsername());
        return toAjax(mesSupplierService.updateMesSupplier(mesSupplier));
    }

    @PreAuthorize("@ss.hasPermi('mes:supplier:remove')")
    @Log(title = "供应商档案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable Long[] supplierIds)
    {
        return toAjax(mesSupplierService.deleteMesSupplierByIds(supplierIds));
    }
}
