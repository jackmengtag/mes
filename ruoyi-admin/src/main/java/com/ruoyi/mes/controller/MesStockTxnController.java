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
import com.ruoyi.mes.domain.MesStockTxn;
import com.ruoyi.mes.service.IMesStockTxnService;

/**
 * 出入库流水Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/stockTxn")
public class MesStockTxnController extends BaseController
{
    @Autowired
    private IMesStockTxnService mesStockTxnService;

    @PreAuthorize("@ss.hasPermi('mes:stockTxn:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesStockTxn mesStockTxn)
    {
        startPage();
        List<MesStockTxn> list = mesStockTxnService.selectMesStockTxnList(mesStockTxn);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:stockTxn:export')")
    @Log(title = "出入库流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesStockTxn mesStockTxn)
    {
        List<MesStockTxn> list = mesStockTxnService.selectMesStockTxnList(mesStockTxn);
        ExcelUtil<MesStockTxn> util = new ExcelUtil<MesStockTxn>(MesStockTxn.class);
        util.exportExcel(response, list, "出入库流水数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:stockTxn:query')")
    @GetMapping(value = "/{txnId}")
    public AjaxResult getInfo(@PathVariable("txnId") Long txnId)
    {
        return success(mesStockTxnService.selectMesStockTxnById(txnId));
    }

    @PreAuthorize("@ss.hasPermi('mes:stockTxn:add')")
    @Log(title = "出入库流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesStockTxn mesStockTxn)
    {
        mesStockTxn.setCreateBy(getUsername());
        return toAjax(mesStockTxnService.insertMesStockTxn(mesStockTxn));
    }

    @PreAuthorize("@ss.hasPermi('mes:stockTxn:edit')")
    @Log(title = "出入库流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesStockTxn mesStockTxn)
    {
        mesStockTxn.setUpdateBy(getUsername());
        return toAjax(mesStockTxnService.updateMesStockTxn(mesStockTxn));
    }

    @PreAuthorize("@ss.hasPermi('mes:stockTxn:remove')")
    @Log(title = "出入库流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{txnIds}")
    public AjaxResult remove(@PathVariable Long[] txnIds)
    {
        return toAjax(mesStockTxnService.deleteMesStockTxnByIds(txnIds));
    }
}
