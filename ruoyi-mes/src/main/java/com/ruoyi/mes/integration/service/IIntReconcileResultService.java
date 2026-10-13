package com.ruoyi.mes.integration.service;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntReconcileResult;

/**
 * 对账结果Service接口
 *
 * @author ruoyi
 */
public interface IIntReconcileResultService
{
    /**
     * 查询对账结果
     */
    public IntReconcileResult selectIntReconcileResultById(Long id);

    /**
     * 查询对账结果列表
     */
    public List<IntReconcileResult> selectIntReconcileResultList(IntReconcileResult intReconcileResult);

    /**
     * 新增对账结果
     */
    public int insertIntReconcileResult(IntReconcileResult intReconcileResult);

    /**
     * 修改对账结果
     */
    public int updateIntReconcileResult(IntReconcileResult intReconcileResult);

    /**
     * 批量删除对账结果
     */
    public int deleteIntReconcileResultByIds(Long[] ids);

    /**
     * 删除对账结果信息
     */
    public int deleteIntReconcileResultById(Long id);
}
