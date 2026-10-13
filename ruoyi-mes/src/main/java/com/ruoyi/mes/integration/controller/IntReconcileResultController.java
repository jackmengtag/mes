package com.ruoyi.mes.integration.controller;

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
import com.ruoyi.mes.integration.domain.IntReconcileResult;
import com.ruoyi.mes.integration.service.IIntReconcileResultService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 对账结果Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/integration/reconcileResult")
public class IntReconcileResultController extends BaseController
{
    @Autowired
    private IIntReconcileResultService intReconcileResultService;

    /**
     * 查询对账结果列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileResult:list')")
    @GetMapping("/list")
    public TableDataInfo list(IntReconcileResult intReconcileResult)
    {
        startPage();
        List<IntReconcileResult> list = intReconcileResultService.selectIntReconcileResultList(intReconcileResult);
        return getDataTable(list);
    }

    /**
     * 导出对账结果列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileResult:export')")
    @Log(title = "对账结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntReconcileResult intReconcileResult)
    {
        List<IntReconcileResult> list = intReconcileResultService.selectIntReconcileResultList(intReconcileResult);
        ExcelUtil<IntReconcileResult> util = new ExcelUtil<IntReconcileResult>(IntReconcileResult.class);
        util.exportExcel(response, list, "对账结果数据");
    }

    /**
     * 获取对账结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileResult:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(intReconcileResultService.selectIntReconcileResultById(id));
    }

    /**
     * 新增对账结果
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileResult:add')")
    @Log(title = "对账结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntReconcileResult intReconcileResult)
    {
        intReconcileResult.setCreateBy(getUsername());
        return toAjax(intReconcileResultService.insertIntReconcileResult(intReconcileResult));
    }

    /**
     * 修改对账结果
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileResult:edit')")
    @Log(title = "对账结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntReconcileResult intReconcileResult)
    {
        intReconcileResult.setUpdateBy(getUsername());
        return toAjax(intReconcileResultService.updateIntReconcileResult(intReconcileResult));
    }

    /**
     * 删除对账结果
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileResult:remove')")
    @Log(title = "对账结果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(intReconcileResultService.deleteIntReconcileResultByIds(ids));
    }
}
