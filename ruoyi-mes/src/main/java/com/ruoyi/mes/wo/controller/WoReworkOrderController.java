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
import com.ruoyi.mes.wo.domain.WoReworkOrder;
import com.ruoyi.mes.wo.service.IWoReworkOrderService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 返工工单Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/wo/reworkOrder")
public class WoReworkOrderController extends BaseController
{
    @Autowired
    private IWoReworkOrderService woReworkOrderService;

    /**
     * 查询返工工单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:reworkOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(WoReworkOrder woReworkOrder)
    {
        startPage();
        List<WoReworkOrder> list = woReworkOrderService.selectWoReworkOrderList(woReworkOrder);
        return getDataTable(list);
    }

    /**
     * 导出返工工单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:reworkOrder:export')")
    @Log(title = "返工工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WoReworkOrder woReworkOrder)
    {
        List<WoReworkOrder> list = woReworkOrderService.selectWoReworkOrderList(woReworkOrder);
        ExcelUtil<WoReworkOrder> util = new ExcelUtil<WoReworkOrder>(WoReworkOrder.class);
        util.exportExcel(response, list, "返工工单数据");
    }

    /**
     * 获取返工工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:reworkOrder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(woReworkOrderService.selectWoReworkOrderById(id));
    }

    /**
     * 新增返工工单
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:reworkOrder:add')")
    @Log(title = "返工工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WoReworkOrder woReworkOrder)
    {
        woReworkOrder.setCreateBy(getUsername());
        return toAjax(woReworkOrderService.insertWoReworkOrder(woReworkOrder));
    }

    /**
     * 修改返工工单
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:reworkOrder:edit')")
    @Log(title = "返工工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WoReworkOrder woReworkOrder)
    {
        woReworkOrder.setUpdateBy(getUsername());
        return toAjax(woReworkOrderService.updateWoReworkOrder(woReworkOrder));
    }

    /**
     * 删除返工工单
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:reworkOrder:remove')")
    @Log(title = "返工工单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(woReworkOrderService.deleteWoReworkOrderByIds(ids));
    }
}
