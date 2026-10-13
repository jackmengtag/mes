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
import com.ruoyi.mes.wo.domain.WoOperationTask;
import com.ruoyi.mes.wo.service.IWoOperationTaskService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工序任务Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/wo/operationTask")
public class WoOperationTaskController extends BaseController
{
    @Autowired
    private IWoOperationTaskService woOperationTaskService;

    /**
     * 查询工序任务列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:operationTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(WoOperationTask woOperationTask)
    {
        startPage();
        List<WoOperationTask> list = woOperationTaskService.selectWoOperationTaskList(woOperationTask);
        return getDataTable(list);
    }

    /**
     * 导出工序任务列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:operationTask:export')")
    @Log(title = "工序任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WoOperationTask woOperationTask)
    {
        List<WoOperationTask> list = woOperationTaskService.selectWoOperationTaskList(woOperationTask);
        ExcelUtil<WoOperationTask> util = new ExcelUtil<WoOperationTask>(WoOperationTask.class);
        util.exportExcel(response, list, "工序任务数据");
    }

    /**
     * 获取工序任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:operationTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(woOperationTaskService.selectWoOperationTaskById(id));
    }

    /**
     * 新增工序任务
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:operationTask:add')")
    @Log(title = "工序任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WoOperationTask woOperationTask)
    {
        woOperationTask.setCreateBy(getUsername());
        return toAjax(woOperationTaskService.insertWoOperationTask(woOperationTask));
    }

    /**
     * 修改工序任务
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:operationTask:edit')")
    @Log(title = "工序任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WoOperationTask woOperationTask)
    {
        woOperationTask.setUpdateBy(getUsername());
        return toAjax(woOperationTaskService.updateWoOperationTask(woOperationTask));
    }

    /**
     * 删除工序任务
     */
    @PreAuthorize("@ss.hasPermi('mes:wo:operationTask:remove')")
    @Log(title = "工序任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(woOperationTaskService.deleteWoOperationTaskByIds(ids));
    }
}
