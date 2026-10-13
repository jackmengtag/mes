package com.ruoyi.mes.integration.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.integration.mapper.IntReconcileTaskMapper;
import com.ruoyi.mes.integration.domain.IntReconcileTask;
import com.ruoyi.mes.integration.service.IIntReconcileTaskService;

/**
 * 对账任务Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class IntReconcileTaskServiceImpl implements IIntReconcileTaskService
{
    @Autowired
    private IntReconcileTaskMapper intReconcileTaskMapper;

    /**
     * 查询对账任务
     */
    @Override
    public IntReconcileTask selectIntReconcileTaskById(Long id)
    {
        return intReconcileTaskMapper.selectIntReconcileTaskById(id);
    }

    /**
     * 查询对账任务列表
     */
    @Override
    public List<IntReconcileTask> selectIntReconcileTaskList(IntReconcileTask intReconcileTask)
    {
        return intReconcileTaskMapper.selectIntReconcileTaskList(intReconcileTask);
    }

    /**
     * 新增对账任务
     */
    @Override
    public int insertIntReconcileTask(IntReconcileTask intReconcileTask)
    {
        return intReconcileTaskMapper.insertIntReconcileTask(intReconcileTask);
    }

    /**
     * 修改对账任务
     */
    @Override
    public int updateIntReconcileTask(IntReconcileTask intReconcileTask)
    {
        return intReconcileTaskMapper.updateIntReconcileTask(intReconcileTask);
    }

    /**
     * 批量删除对账任务
     */
    @Override
    public int deleteIntReconcileTaskByIds(Long[] ids)
    {
        return intReconcileTaskMapper.deleteIntReconcileTaskByIds(ids);
    }

    /**
     * 删除对账任务信息
     */
    @Override
    public int deleteIntReconcileTaskById(Long id)
    {
        return intReconcileTaskMapper.deleteIntReconcileTaskById(id);
    }
}
