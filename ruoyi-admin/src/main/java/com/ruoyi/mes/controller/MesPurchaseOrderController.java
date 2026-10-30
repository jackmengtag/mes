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
import com.ruoyi.mes.domain.MesPurchaseOrder;
import com.ruoyi.mes.service.IMesPurchaseOrderService;

/**
 * 采购订单Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/purchase")
public class MesPurchaseOrderController extends BaseController
{
    @Autowired
    private IMesPurchaseOrderService mesPurchaseOrderService;

    @PreAuthorize("@ss.hasPermi('mes:purchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesPurchaseOrder mesPurchaseOrder)
    {
        startPage();
        List<MesPurchaseOrder> list = mesPurchaseOrderService.selectMesPurchaseOrderList(mesPurchaseOrder);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:purchase:export')")
    @Log(title = "采购订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesPurchaseOrder mesPurchaseOrder)
    {
        List<MesPurchaseOrder> list = mesPurchaseOrderService.selectMesPurchaseOrderList(mesPurchaseOrder);
        ExcelUtil<MesPurchaseOrder> util = new ExcelUtil<MesPurchaseOrder>(MesPurchaseOrder.class);
        util.exportExcel(response, list, "采购订单数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:purchase:query')")
    @GetMapping(value = "/{purchaseId}")
    public AjaxResult getInfo(@PathVariable("purchaseId") Long purchaseId)
    {
        return success(mesPurchaseOrderService.selectMesPurchaseOrderById(purchaseId));
    }

    @PreAuthorize("@ss.hasPermi('mes:purchase:add')")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesPurchaseOrder mesPurchaseOrder)
    {
        mesPurchaseOrder.setCreateBy(getUsername());
        return toAjax(mesPurchaseOrderService.insertMesPurchaseOrder(mesPurchaseOrder));
    }

    @PreAuthorize("@ss.hasPermi('mes:purchase:edit')")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesPurchaseOrder mesPurchaseOrder)
    {
        mesPurchaseOrder.setUpdateBy(getUsername());
        return toAjax(mesPurchaseOrderService.updateMesPurchaseOrder(mesPurchaseOrder));
    }

    @PreAuthorize("@ss.hasPermi('mes:purchase:remove')")
    @Log(title = "采购订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{purchaseIds}")
    public AjaxResult remove(@PathVariable Long[] purchaseIds)
    {
        return toAjax(mesPurchaseOrderService.deleteMesPurchaseOrderByIds(purchaseIds));
    }
}
