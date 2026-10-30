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
import com.ruoyi.mes.domain.MesVoucher;
import com.ruoyi.mes.service.IMesVoucherService;

/**
 * 存货核算凭证Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/voucher")
public class MesVoucherController extends BaseController
{
    @Autowired
    private IMesVoucherService mesVoucherService;

    @PreAuthorize("@ss.hasPermi('mes:voucher:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesVoucher mesVoucher)
    {
        startPage();
        List<MesVoucher> list = mesVoucherService.selectMesVoucherList(mesVoucher);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:voucher:export')")
    @Log(title = "存货核算凭证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesVoucher mesVoucher)
    {
        List<MesVoucher> list = mesVoucherService.selectMesVoucherList(mesVoucher);
        ExcelUtil<MesVoucher> util = new ExcelUtil<MesVoucher>(MesVoucher.class);
        util.exportExcel(response, list, "存货核算凭证数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:voucher:query')")
    @GetMapping(value = "/{voucherId}")
    public AjaxResult getInfo(@PathVariable("voucherId") Long voucherId)
    {
        return success(mesVoucherService.selectMesVoucherById(voucherId));
    }

    @PreAuthorize("@ss.hasPermi('mes:voucher:add')")
    @Log(title = "存货核算凭证", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesVoucher mesVoucher)
    {
        mesVoucher.setCreateBy(getUsername());
        return toAjax(mesVoucherService.insertMesVoucher(mesVoucher));
    }

    @PreAuthorize("@ss.hasPermi('mes:voucher:edit')")
    @Log(title = "存货核算凭证", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesVoucher mesVoucher)
    {
        mesVoucher.setUpdateBy(getUsername());
        return toAjax(mesVoucherService.updateMesVoucher(mesVoucher));
    }

    @PreAuthorize("@ss.hasPermi('mes:voucher:remove')")
    @Log(title = "存货核算凭证", businessType = BusinessType.DELETE)
    @DeleteMapping("/{voucherIds}")
    public AjaxResult remove(@PathVariable Long[] voucherIds)
    {
        return toAjax(mesVoucherService.deleteMesVoucherByIds(voucherIds));
    }
}
