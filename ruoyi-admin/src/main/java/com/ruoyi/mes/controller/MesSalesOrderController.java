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
import com.ruoyi.mes.domain.MesSalesOrder;
import com.ruoyi.mes.service.IMesSalesOrderService;

/**
 * 销售订单Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/sales")
public class MesSalesOrderController extends BaseController
{
    @Autowired
    private IMesSalesOrderService mesSalesOrderService;

    @PreAuthorize("@ss.hasPermi('mes:sales:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesSalesOrder mesSalesOrder)
    {
        startPage();
        List<MesSalesOrder> list = mesSalesOrderService.selectMesSalesOrderList(mesSalesOrder);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:sales:export')")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesSalesOrder mesSalesOrder)
    {
        List<MesSalesOrder> list = mesSalesOrderService.selectMesSalesOrderList(mesSalesOrder);
        ExcelUtil<MesSalesOrder> util = new ExcelUtil<MesSalesOrder>(MesSalesOrder.class);
        util.exportExcel(response, list, "销售订单数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:sales:query')")
    @GetMapping(value = "/{salesId}")
    public AjaxResult getInfo(@PathVariable("salesId") Long salesId)
    {
        return success(mesSalesOrderService.selectMesSalesOrderById(salesId));
    }

    @PreAuthorize("@ss.hasPermi('mes:sales:add')")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesSalesOrder mesSalesOrder)
    {
        mesSalesOrder.setCreateBy(getUsername());
        return toAjax(mesSalesOrderService.insertMesSalesOrder(mesSalesOrder));
    }

    @PreAuthorize("@ss.hasPermi('mes:sales:edit')")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesSalesOrder mesSalesOrder)
    {
        mesSalesOrder.setUpdateBy(getUsername());
        return toAjax(mesSalesOrderService.updateMesSalesOrder(mesSalesOrder));
    }

    @PreAuthorize("@ss.hasPermi('mes:sales:remove')")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{salesIds}")
    public AjaxResult remove(@PathVariable Long[] salesIds)
    {
        return toAjax(mesSalesOrderService.deleteMesSalesOrderByIds(salesIds));
    }
}
