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
import com.ruoyi.mes.integration.domain.IntReconcileTask;
import com.ruoyi.mes.integration.service.IIntReconcileTaskService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 对账任务Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/integration/reconcileTask")
public class IntReconcileTaskController extends BaseController
{
    @Autowired
    private IIntReconcileTaskService intReconcileTaskService;

    /**
     * 查询对账任务列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(IntReconcileTask intReconcileTask)
    {
        startPage();
        List<IntReconcileTask> list = intReconcileTaskService.selectIntReconcileTaskList(intReconcileTask);
        return getDataTable(list);
    }

    /**
     * 导出对账任务列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileTask:export')")
    @Log(title = "对账任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntReconcileTask intReconcileTask)
    {
        List<IntReconcileTask> list = intReconcileTaskService.selectIntReconcileTaskList(intReconcileTask);
        ExcelUtil<IntReconcileTask> util = new ExcelUtil<IntReconcileTask>(IntReconcileTask.class);
        util.exportExcel(response, list, "对账任务数据");
    }

    /**
     * 获取对账任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(intReconcileTaskService.selectIntReconcileTaskById(id));
    }

    /**
     * 新增对账任务
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileTask:add')")
    @Log(title = "对账任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntReconcileTask intReconcileTask)
    {
        intReconcileTask.setCreateBy(getUsername());
        return toAjax(intReconcileTaskService.insertIntReconcileTask(intReconcileTask));
    }

    /**
     * 修改对账任务
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileTask:edit')")
    @Log(title = "对账任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntReconcileTask intReconcileTask)
    {
        intReconcileTask.setUpdateBy(getUsername());
        return toAjax(intReconcileTaskService.updateIntReconcileTask(intReconcileTask));
    }

    /**
     * 删除对账任务
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:reconcileTask:remove')")
    @Log(title = "对账任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(intReconcileTaskService.deleteIntReconcileTaskByIds(ids));
    }
}
