package com.ruoyi.mes.ps.controller;

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
import com.ruoyi.mes.ps.domain.PsScheduleTask;
import com.ruoyi.mes.ps.service.IPsScheduleTaskService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 排程任务Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ps/scheduleTask")
public class PsScheduleTaskController extends BaseController
{
    @Autowired
    private IPsScheduleTaskService psScheduleTaskService;

    /**
     * 查询排程任务列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:scheduleTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsScheduleTask psScheduleTask)
    {
        startPage();
        List<PsScheduleTask> list = psScheduleTaskService.selectPsScheduleTaskList(psScheduleTask);
        return getDataTable(list);
    }

    /**
     * 导出排程任务列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:scheduleTask:export')")
    @Log(title = "排程任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsScheduleTask psScheduleTask)
    {
        List<PsScheduleTask> list = psScheduleTaskService.selectPsScheduleTaskList(psScheduleTask);
        ExcelUtil<PsScheduleTask> util = new ExcelUtil<PsScheduleTask>(PsScheduleTask.class);
        util.exportExcel(response, list, "排程任务数据");
    }

    /**
     * 获取排程任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:scheduleTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(psScheduleTaskService.selectPsScheduleTaskById(id));
    }

    /**
     * 新增排程任务
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:scheduleTask:add')")
    @Log(title = "排程任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsScheduleTask psScheduleTask)
    {
        psScheduleTask.setCreateBy(getUsername());
        return toAjax(psScheduleTaskService.insertPsScheduleTask(psScheduleTask));
    }

    /**
     * 修改排程任务
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:scheduleTask:edit')")
    @Log(title = "排程任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsScheduleTask psScheduleTask)
    {
        psScheduleTask.setUpdateBy(getUsername());
        return toAjax(psScheduleTaskService.updatePsScheduleTask(psScheduleTask));
    }

    /**
     * 删除排程任务
     */
    @PreAuthorize("@ss.hasPermi('mes:ps:scheduleTask:remove')")
    @Log(title = "排程任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psScheduleTaskService.deletePsScheduleTaskByIds(ids));
    }
}
