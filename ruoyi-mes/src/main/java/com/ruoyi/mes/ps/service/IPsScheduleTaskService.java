package com.ruoyi.mes.ps.service;

import java.util.List;
import com.ruoyi.mes.ps.domain.PsScheduleTask;

/**
 * 排程任务Service接口
 *
 * @author ruoyi
 */
public interface IPsScheduleTaskService
{
    /**
     * 查询排程任务
     */
    public PsScheduleTask selectPsScheduleTaskById(Long id);

    /**
     * 查询排程任务列表
     */
    public List<PsScheduleTask> selectPsScheduleTaskList(PsScheduleTask psScheduleTask);

    /**
     * 新增排程任务
     */
    public int insertPsScheduleTask(PsScheduleTask psScheduleTask);

    /**
     * 修改排程任务
     */
    public int updatePsScheduleTask(PsScheduleTask psScheduleTask);

    /**
     * 批量删除排程任务
     */
    public int deletePsScheduleTaskByIds(Long[] ids);

    /**
     * 删除排程任务信息
     */
    public int deletePsScheduleTaskById(Long id);
}
