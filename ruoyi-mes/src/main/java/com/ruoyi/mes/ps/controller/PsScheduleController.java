package com.ruoyi.mes.ps.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.ps.service.SchedulingService;

/**
 * 计划排程控制器（FR-PS-002/003/005）
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ps/schedule")
public class PsScheduleController extends BaseController
{
    @Autowired
    private SchedulingService schedulingService;

    /** 规则排程（含冲突检测） */
    @PreAuthorize("@ss.hasPermi('mes:ps:plan:edit')")
    @Log(title = "规则排程", businessType = BusinessType.UPDATE)
    @PostMapping("/rule")
    public AjaxResult rule(@RequestBody Map<String, Object> body)
    {
        List<Long> planIds = new ArrayList<>();
        Object ids = body.get("planIds");
        if (ids instanceof List)
        {
            for (Object id : (List<?>) ids)
            {
                planIds.add(Long.valueOf(String.valueOf(id)));
            }
        }
        String reason = body.get("reason") == null ? null : String.valueOf(body.get("reason"));
        return success(schedulingService.ruleSchedule(planIds, reason, getUsername()));
    }
}
