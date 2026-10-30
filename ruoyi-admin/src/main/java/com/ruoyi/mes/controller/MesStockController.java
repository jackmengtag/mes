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
import com.ruoyi.mes.domain.MesStock;
import com.ruoyi.mes.service.IMesStockService;

/**
 * 实时库存Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/stock")
public class MesStockController extends BaseController
{
    @Autowired
    private IMesStockService mesStockService;

    @PreAuthorize("@ss.hasPermi('mes:stock:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesStock mesStock)
    {
        startPage();
        List<MesStock> list = mesStockService.selectMesStockList(mesStock);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:stock:export')")
    @Log(title = "实时库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesStock mesStock)
    {
        List<MesStock> list = mesStockService.selectMesStockList(mesStock);
        ExcelUtil<MesStock> util = new ExcelUtil<MesStock>(MesStock.class);
        util.exportExcel(response, list, "实时库存数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:stock:query')")
    @GetMapping(value = "/{stockId}")
    public AjaxResult getInfo(@PathVariable("stockId") Long stockId)
    {
        return success(mesStockService.selectMesStockById(stockId));
    }

    @PreAuthorize("@ss.hasPermi('mes:stock:add')")
    @Log(title = "实时库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesStock mesStock)
    {
        mesStock.setCreateBy(getUsername());
        return toAjax(mesStockService.insertMesStock(mesStock));
    }

    @PreAuthorize("@ss.hasPermi('mes:stock:edit')")
    @Log(title = "实时库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesStock mesStock)
    {
        mesStock.setUpdateBy(getUsername());
        return toAjax(mesStockService.updateMesStock(mesStock));
    }

    @PreAuthorize("@ss.hasPermi('mes:stock:remove')")
    @Log(title = "实时库存", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stockIds}")
    public AjaxResult remove(@PathVariable Long[] stockIds)
    {
        return toAjax(mesStockService.deleteMesStockByIds(stockIds));
    }
}
