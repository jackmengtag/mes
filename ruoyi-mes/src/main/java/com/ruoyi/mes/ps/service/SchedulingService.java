package com.ruoyi.mes.ps.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.mes.common.enums.PlanStatus;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.ps.domain.PsPlan;
import com.ruoyi.mes.ps.domain.PsScheduleTask;
import com.ruoyi.mes.ps.mapper.PsPlanMapper;
import com.ruoyi.mes.ps.mapper.PsScheduleTaskMapper;

/**
 * 计划排程服务：规则排程与冲突检测（FR-PS-002/003/005）
 *
 * @author ruoyi
 */
@Service
public class SchedulingService
{
    @Autowired
    private PsPlanMapper planMapper;

    @Autowired
    private PsScheduleTaskMapper scheduleTaskMapper;

    /**
     * 规则排程：按优先级与交期排序，为计划生成排程任务并检测产能冲突
     */
    @Transactional
    public List<PsScheduleTask> ruleSchedule(List<Long> planIds, String adjustReason, String operator)
    {
        List<PsPlan> plans = new ArrayList<>();
        for (Long id : planIds)
        {
            PsPlan plan = planMapper.selectPsPlanById(id);
            if (plan == null)
            {
                throw new ServiceException("生产计划不存在：" + id);
            }
            plans.add(plan);
        }
        // 规则排序：优先级(HIGH>NORMAL>LOW) 再交期
        plans.sort((a, b) -> {
            int pa = priorityWeight(a.getPriority());
            int pb = priorityWeight(b.getPriority());
            if (pa != pb)
            {
                return pb - pa;
            }
            Date da = a.getPlannedStartTime();
            Date db = b.getPlannedStartTime();
            if (da == null || db == null)
            {
                return 0;
            }
            return da.compareTo(db);
        });

        List<PsScheduleTask> result = new ArrayList<>();
        int seq = 1;
        for (PsPlan plan : plans)
        {
            PsScheduleTask task = new PsScheduleTask();
            task.setPlanId(plan.getId());
            task.setLineId(plan.getLineId());
            task.setSequenceNo(seq++);
            task.setPlannedStartTime(plan.getPlannedStartTime());
            task.setPlannedEndTime(plan.getPlannedEndTime());
            task.setScheduleType("RULE");
            task.setAdjustReason(adjustReason);
            task.setStatus("SCHEDULED");
            // 冲突检测：同产线时间窗口重叠
            String conflict = detectConflict(plan);
            task.setConflictFlag(conflict == null ? "N" : "Y");
            task.setConflictReason(conflict);
            task.setCreateBy(operator);
            scheduleTaskMapper.insertPsScheduleTask(task);
            result.add(task);

            plan.setStatus(PlanStatus.SCHEDULED.name());
            plan.setUpdateBy(operator);
            planMapper.updatePsPlan(plan);
        }
        return result;
    }

    private String detectConflict(PsPlan plan)
    {
        if (plan.getLineId() == null || plan.getPlannedStartTime() == null || plan.getPlannedEndTime() == null)
        {
            return null;
        }
        PsScheduleTask query = new PsScheduleTask();
        query.setLineId(plan.getLineId());
        for (PsScheduleTask other : scheduleTaskMapper.selectPsScheduleTaskList(query))
        {
            if (other.getPlanId() != null && other.getPlanId().equals(plan.getId()))
            {
                continue;
            }
            if (other.getPlannedStartTime() == null || other.getPlannedEndTime() == null)
            {
                continue;
            }
            boolean overlap = plan.getPlannedStartTime().before(other.getPlannedEndTime())
                    && other.getPlannedStartTime().before(plan.getPlannedEndTime());
            if (overlap)
            {
                return "产线时间窗口冲突，与排程任务#" + other.getId() + "重叠";
            }
        }
        return null;
    }

    private static int priorityWeight(String priority)
    {
        if ("HIGH".equals(priority))
        {
            return 3;
        }
        if ("LOW".equals(priority))
        {
            return 1;
        }
        return 2;
    }
}
