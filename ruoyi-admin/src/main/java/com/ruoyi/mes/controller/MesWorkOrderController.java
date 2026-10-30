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
import com.ruoyi.mes.domain.MesWorkOrder;
import com.ruoyi.mes.service.IMesWorkOrderService;

/**
 * 生产工单Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/workOrder")
public class MesWorkOrderController extends BaseController
{
    @Autowired
    private IMesWorkOrderService mesWorkOrderService;

    @PreAuthorize("@ss.hasPermi('mes:workOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesWorkOrder mesWorkOrder)
    {
        startPage();
        List<MesWorkOrder> list = mesWorkOrderService.selectMesWorkOrderList(mesWorkOrder);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:workOrder:export')")
    @Log(title = "生产工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesWorkOrder mesWorkOrder)
    {
        List<MesWorkOrder> list = mesWorkOrderService.selectMesWorkOrderList(mesWorkOrder);
        ExcelUtil<MesWorkOrder> util = new ExcelUtil<MesWorkOrder>(MesWorkOrder.class);
        util.exportExcel(response, list, "生产工单数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:workOrder:query')")
    @GetMapping(value = "/{workOrderId}")
    public AjaxResult getInfo(@PathVariable("workOrderId") Long workOrderId)
    {
        return success(mesWorkOrderService.selectMesWorkOrderById(workOrderId));
    }

    @PreAuthorize("@ss.hasPermi('mes:workOrder:add')")
    @Log(title = "生产工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesWorkOrder mesWorkOrder)
    {
        mesWorkOrder.setCreateBy(getUsername());
        return toAjax(mesWorkOrderService.insertMesWorkOrder(mesWorkOrder));
    }

    @PreAuthorize("@ss.hasPermi('mes:workOrder:edit')")
    @Log(title = "生产工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesWorkOrder mesWorkOrder)
    {
        mesWorkOrder.setUpdateBy(getUsername());
        return toAjax(mesWorkOrderService.updateMesWorkOrder(mesWorkOrder));
    }

    @PreAuthorize("@ss.hasPermi('mes:workOrder:remove')")
    @Log(title = "生产工单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{workOrderIds}")
    public AjaxResult remove(@PathVariable Long[] workOrderIds)
    {
        return toAjax(mesWorkOrderService.deleteMesWorkOrderByIds(workOrderIds));
    }
}
