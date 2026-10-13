package com.ruoyi.mes.ps.mapper;

import java.util.List;
import com.ruoyi.mes.ps.domain.PsScheduleTask;

/**
 * 排程任务Mapper接口
 *
 * @author ruoyi
 */
public interface PsScheduleTaskMapper
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
     * 删除排程任务
     */
    public int deletePsScheduleTaskById(Long id);

    /**
     * 批量删除排程任务
     */
    public int deletePsScheduleTaskByIds(Long[] ids);
}
