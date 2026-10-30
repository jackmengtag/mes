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
import com.ruoyi.mes.domain.MesShipment;
import com.ruoyi.mes.service.IMesShipmentService;

/**
 * 销售出库Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/shipment")
public class MesShipmentController extends BaseController
{
    @Autowired
    private IMesShipmentService mesShipmentService;

    @PreAuthorize("@ss.hasPermi('mes:shipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesShipment mesShipment)
    {
        startPage();
        List<MesShipment> list = mesShipmentService.selectMesShipmentList(mesShipment);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:shipment:export')")
    @Log(title = "销售出库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesShipment mesShipment)
    {
        List<MesShipment> list = mesShipmentService.selectMesShipmentList(mesShipment);
        ExcelUtil<MesShipment> util = new ExcelUtil<MesShipment>(MesShipment.class);
        util.exportExcel(response, list, "销售出库数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:shipment:query')")
    @GetMapping(value = "/{shipmentId}")
    public AjaxResult getInfo(@PathVariable("shipmentId") Long shipmentId)
    {
        return success(mesShipmentService.selectMesShipmentById(shipmentId));
    }

    @PreAuthorize("@ss.hasPermi('mes:shipment:add')")
    @Log(title = "销售出库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesShipment mesShipment)
    {
        mesShipment.setCreateBy(getUsername());
        return toAjax(mesShipmentService.insertMesShipment(mesShipment));
    }

    @PreAuthorize("@ss.hasPermi('mes:shipment:edit')")
    @Log(title = "销售出库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesShipment mesShipment)
    {
        mesShipment.setUpdateBy(getUsername());
        return toAjax(mesShipmentService.updateMesShipment(mesShipment));
    }

    @PreAuthorize("@ss.hasPermi('mes:shipment:remove')")
    @Log(title = "销售出库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{shipmentIds}")
    public AjaxResult remove(@PathVariable Long[] shipmentIds)
    {
        return toAjax(mesShipmentService.deleteMesShipmentByIds(shipmentIds));
    }
}
