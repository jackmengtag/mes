package com.ruoyi.mes.wo.service;

import java.util.List;
import com.ruoyi.mes.wo.domain.WoOperationTask;

/**
 * 工序任务Service接口
 *
 * @author ruoyi
 */
public interface IWoOperationTaskService
{
    /**
     * 查询工序任务
     */
    public WoOperationTask selectWoOperationTaskById(Long id);

    /**
     * 查询工序任务列表
     */
    public List<WoOperationTask> selectWoOperationTaskList(WoOperationTask woOperationTask);

    /**
     * 新增工序任务
     */
    public int insertWoOperationTask(WoOperationTask woOperationTask);

    /**
     * 修改工序任务
     */
    public int updateWoOperationTask(WoOperationTask woOperationTask);

    /**
     * 批量删除工序任务
     */
    public int deleteWoOperationTaskByIds(Long[] ids);

    /**
     * 删除工序任务信息
     */
    public int deleteWoOperationTaskById(Long id);
}
