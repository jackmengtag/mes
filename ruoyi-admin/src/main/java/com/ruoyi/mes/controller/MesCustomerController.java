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
import com.ruoyi.mes.domain.MesCustomer;
import com.ruoyi.mes.service.IMesCustomerService;

/**
 * 客户档案Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/customer")
public class MesCustomerController extends BaseController
{
    @Autowired
    private IMesCustomerService mesCustomerService;

    @PreAuthorize("@ss.hasPermi('mes:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesCustomer mesCustomer)
    {
        startPage();
        List<MesCustomer> list = mesCustomerService.selectMesCustomerList(mesCustomer);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:customer:export')")
    @Log(title = "客户档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesCustomer mesCustomer)
    {
        List<MesCustomer> list = mesCustomerService.selectMesCustomerList(mesCustomer);
        ExcelUtil<MesCustomer> util = new ExcelUtil<MesCustomer>(MesCustomer.class);
        util.exportExcel(response, list, "客户档案数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:customer:query')")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") Long customerId)
    {
        return success(mesCustomerService.selectMesCustomerById(customerId));
    }

    @PreAuthorize("@ss.hasPermi('mes:customer:add')")
    @Log(title = "客户档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesCustomer mesCustomer)
    {
        mesCustomer.setCreateBy(getUsername());
        return toAjax(mesCustomerService.insertMesCustomer(mesCustomer));
    }

    @PreAuthorize("@ss.hasPermi('mes:customer:edit')")
    @Log(title = "客户档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesCustomer mesCustomer)
    {
        mesCustomer.setUpdateBy(getUsername());
        return toAjax(mesCustomerService.updateMesCustomer(mesCustomer));
    }

    @PreAuthorize("@ss.hasPermi('mes:customer:remove')")
    @Log(title = "客户档案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds)
    {
        return toAjax(mesCustomerService.deleteMesCustomerByIds(customerIds));
    }
}
