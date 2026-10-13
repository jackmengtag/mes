package com.ruoyi.mes.ps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.ps.mapper.PsScheduleTaskMapper;
import com.ruoyi.mes.ps.domain.PsScheduleTask;
import com.ruoyi.mes.ps.service.IPsScheduleTaskService;

/**
 * 排程任务Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class PsScheduleTaskServiceImpl implements IPsScheduleTaskService
{
    @Autowired
    private PsScheduleTaskMapper psScheduleTaskMapper;

    /**
     * 查询排程任务
     */
    @Override
    public PsScheduleTask selectPsScheduleTaskById(Long id)
    {
        return psScheduleTaskMapper.selectPsScheduleTaskById(id);
    }

    /**
     * 查询排程任务列表
     */
    @Override
    public List<PsScheduleTask> selectPsScheduleTaskList(PsScheduleTask psScheduleTask)
    {
        return psScheduleTaskMapper.selectPsScheduleTaskList(psScheduleTask);
    }

    /**
     * 新增排程任务
     */
    @Override
    public int insertPsScheduleTask(PsScheduleTask psScheduleTask)
    {
        return psScheduleTaskMapper.insertPsScheduleTask(psScheduleTask);
    }

    /**
     * 修改排程任务
     */
    @Override
    public int updatePsScheduleTask(PsScheduleTask psScheduleTask)
    {
        return psScheduleTaskMapper.updatePsScheduleTask(psScheduleTask);
    }

    /**
     * 批量删除排程任务
     */
    @Override
    public int deletePsScheduleTaskByIds(Long[] ids)
    {
        return psScheduleTaskMapper.deletePsScheduleTaskByIds(ids);
    }

    /**
     * 删除排程任务信息
     */
    @Override
    public int deletePsScheduleTaskById(Long id)
    {
        return psScheduleTaskMapper.deletePsScheduleTaskById(id);
    }
}
