package com.ruoyi.mes.integration.service;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntReconcileTask;

/**
 * 对账任务Service接口
 *
 * @author ruoyi
 */
public interface IIntReconcileTaskService
{
    /**
     * 查询对账任务
     */
    public IntReconcileTask selectIntReconcileTaskById(Long id);

    /**
     * 查询对账任务列表
     */
    public List<IntReconcileTask> selectIntReconcileTaskList(IntReconcileTask intReconcileTask);

    /**
     * 新增对账任务
     */
    public int insertIntReconcileTask(IntReconcileTask intReconcileTask);

    /**
     * 修改对账任务
     */
    public int updateIntReconcileTask(IntReconcileTask intReconcileTask);

    /**
     * 批量删除对账任务
     */
    public int deleteIntReconcileTaskByIds(Long[] ids);

    /**
     * 删除对账任务信息
     */
    public int deleteIntReconcileTaskById(Long id);
}
