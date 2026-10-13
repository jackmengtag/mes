package com.ruoyi.mes.wo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.wo.mapper.WoOperationTaskMapper;
import com.ruoyi.mes.wo.domain.WoOperationTask;
import com.ruoyi.mes.wo.service.IWoOperationTaskService;

/**
 * 工序任务Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class WoOperationTaskServiceImpl implements IWoOperationTaskService
{
    @Autowired
    private WoOperationTaskMapper woOperationTaskMapper;

    /**
     * 查询工序任务
     */
    @Override
    public WoOperationTask selectWoOperationTaskById(Long id)
    {
        return woOperationTaskMapper.selectWoOperationTaskById(id);
    }

    /**
     * 查询工序任务列表
     */
    @Override
    public List<WoOperationTask> selectWoOperationTaskList(WoOperationTask woOperationTask)
    {
        return woOperationTaskMapper.selectWoOperationTaskList(woOperationTask);
    }

    /**
     * 新增工序任务
     */
    @Override
    public int insertWoOperationTask(WoOperationTask woOperationTask)
    {
        return woOperationTaskMapper.insertWoOperationTask(woOperationTask);
    }

    /**
     * 修改工序任务
     */
    @Override
    public int updateWoOperationTask(WoOperationTask woOperationTask)
    {
        return woOperationTaskMapper.updateWoOperationTask(woOperationTask);
    }

    /**
     * 批量删除工序任务
     */
    @Override
    public int deleteWoOperationTaskByIds(Long[] ids)
    {
        return woOperationTaskMapper.deleteWoOperationTaskByIds(ids);
    }

    /**
     * 删除工序任务信息
     */
    @Override
    public int deleteWoOperationTaskById(Long id)
    {
        return woOperationTaskMapper.deleteWoOperationTaskById(id);
    }
}
