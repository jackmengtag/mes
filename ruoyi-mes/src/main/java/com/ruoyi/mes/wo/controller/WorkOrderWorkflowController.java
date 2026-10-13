package com.ruoyi.mes.wo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.wo.domain.WoOperationTask;
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.service.WorkOrderWorkflowService;

/**
 * 工单业务流控制器（FR-WO-002/003/004/006，FR-PS-004）
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/wo/workflow")
public class WorkOrderWorkflowController extends BaseController
{
    @Autowired
    private WorkOrderWorkflowService workflowService;

    /** 工单审核 */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:edit')")
    @Log(title = "工单审核", businessType = BusinessType.UPDATE)
    @PostMapping("/audit/{id}")
    public AjaxResult audit(@PathVariable("id") Long id)
    {
        return success(workflowService.audit(id, getUsername()));
    }

    /** 工单下达（生成工序任务与物料需求） */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:edit')")
    @Log(title = "工单下达", businessType = BusinessType.UPDATE)
    @PostMapping("/release/{id}")
    public AjaxResult release(@PathVariable("id") Long id)
    {
        return success(workflowService.release(id, getUsername(), null));
    }

    /** 工单暂停 */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:edit')")
    @Log(title = "工单暂停", businessType = BusinessType.UPDATE)
    @PostMapping("/pause/{id}")
    public AjaxResult pause(@PathVariable("id") Long id, @RequestBody(required = false) Map<String, Object> body)
    {
        String reason = body == null ? null : String.valueOf(body.get("reason"));
        return success(workflowService.pause(id, reason, getUsername()));
    }

    /** 工单恢复 */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:edit')")
    @Log(title = "工单恢复", businessType = BusinessType.UPDATE)
    @PostMapping("/resume/{id}")
    public AjaxResult resume(@PathVariable("id") Long id)
    {
        return success(workflowService.resume(id, getUsername()));
    }

    /** 工单完工 */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:edit')")
    @Log(title = "工单完工", businessType = BusinessType.UPDATE)
    @PostMapping("/complete/{id}")
    public AjaxResult complete(@PathVariable("id") Long id)
    {
        return success(workflowService.complete(id, getUsername(), null));
    }

    /** 工单关闭 */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:edit')")
    @Log(title = "工单关闭", businessType = BusinessType.UPDATE)
    @PostMapping("/close/{id}")
    public AjaxResult close(@PathVariable("id") Long id)
    {
        return success(workflowService.close(id, getUsername()));
    }

    /** 工单拆分（拆分数量之和必须等于原计划数量） */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:edit')")
    @Log(title = "工单拆分", businessType = BusinessType.INSERT)
    @PostMapping("/split/{id}")
    public AjaxResult split(@PathVariable("id") Long id, @RequestBody Map<String, Object> body)
    {
        @SuppressWarnings("unchecked")
        List<Object> qtyList = (List<Object>) body.get("quantities");
        List<BigDecimal> quantities = new java.util.ArrayList<>();
        if (qtyList != null)
        {
            for (Object q : qtyList)
            {
                quantities.add(new BigDecimal(String.valueOf(q)));
            }
        }
        String reason = body.get("reason") == null ? null : String.valueOf(body.get("reason"));
        return success(workflowService.split(id, quantities, reason, getUsername()));
    }

    /** 查询工单工序任务 */
    @PreAuthorize("@ss.hasPermi('mes:wo:operationTask:list')")
    @GetMapping("/operationTasks/{orderId}")
    public AjaxResult operationTasks(@PathVariable("orderId") Long orderId)
    {
        List<WoOperationTask> list = workflowService.listOperationTask(orderId);
        return success(list);
    }

    /** 查询工单详情（含工序任务） */
    @PreAuthorize("@ss.hasPermi('mes:wo:order:query')")
    @GetMapping("/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Long id)
    {
        AjaxResult ajax = success(workflowService.getOrder(id));
        ajax.put("operationTasks", workflowService.listOperationTask(id));
        return ajax;
    }
}
