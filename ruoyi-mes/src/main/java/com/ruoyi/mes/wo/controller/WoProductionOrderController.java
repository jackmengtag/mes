package com.ruoyi.mes.wo.controller;

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
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.service.IWoProductionOrderService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 生产工单Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/wo/order")
public class WoProductionOrderController extends BaseController
{
    @Autowired
    private IWoProductionOrderService woProductionOrderService;

    /**
     * 查询生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(WoProductionOrder woProductionOrder)
    {
        startPage();
        List<WoProductionOrder> list = woProductionOrderService.selectWoProductionOrderList(woProductionOrder);
        return getDataTable(list);
    }

    /**
     * 导出生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:export')")
    @Log(title = "生产工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WoProductionOrder woProductionOrder)
    {
        List<WoProductionOrder> list = woProductionOrderService.selectWoProductionOrderList(woProductionOrder);
        ExcelUtil<WoProductionOrder> util = new ExcelUtil<WoProductionOrder>(WoProductionOrder.class);
        util.exportExcel(response, list, "生产工单数据");
    }

    /**
     * 获取生产工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(woProductionOrderService.selectWoProductionOrderById(id));
    }

    /**
     * 新增生产工单
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:add')")
    @Log(title = "生产工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WoProductionOrder woProductionOrder)
    {
        woProductionOrder.setCreateBy(getUsername());
        return toAjax(woProductionOrderService.insertWoProductionOrder(woProductionOrder));
    }

    /**
     * 修改生产工单
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:edit')")
    @Log(title = "生产工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WoProductionOrder woProductionOrder)
    {
        woProductionOrder.setUpdateBy(getUsername());
        return toAjax(woProductionOrderService.updateWoProductionOrder(woProductionOrder));
    }

    /**
     * 删除生产工单
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:remove')")
    @Log(title = "生产工单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(woProductionOrderService.deleteWoProductionOrderByIds(ids));
    }
}
