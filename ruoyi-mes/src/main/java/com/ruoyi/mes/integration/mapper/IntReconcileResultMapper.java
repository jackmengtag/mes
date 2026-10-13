package com.ruoyi.mes.integration.mapper;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntReconcileResult;

/**
 * 对账结果Mapper接口
 *
 * @author ruoyi
 */
public interface IntReconcileResultMapper
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
     * 删除对账结果
     */
    public int deleteIntReconcileResultById(Long id);

    /**
     * 批量删除对账结果
     */
    public int deleteIntReconcileResultByIds(Long[] ids);
}
