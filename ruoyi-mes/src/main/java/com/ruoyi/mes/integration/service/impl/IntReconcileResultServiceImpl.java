package com.ruoyi.mes.integration.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.integration.mapper.IntReconcileResultMapper;
import com.ruoyi.mes.integration.domain.IntReconcileResult;
import com.ruoyi.mes.integration.service.IIntReconcileResultService;

/**
 * 对账结果Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class IntReconcileResultServiceImpl implements IIntReconcileResultService
{
    @Autowired
    private IntReconcileResultMapper intReconcileResultMapper;

    /**
     * 查询对账结果
     */
    @Override
    public IntReconcileResult selectIntReconcileResultById(Long id)
    {
        return intReconcileResultMapper.selectIntReconcileResultById(id);
    }

    /**
     * 查询对账结果列表
     */
    @Override
    public List<IntReconcileResult> selectIntReconcileResultList(IntReconcileResult intReconcileResult)
    {
        return intReconcileResultMapper.selectIntReconcileResultList(intReconcileResult);
    }

    /**
     * 新增对账结果
     */
    @Override
    public int insertIntReconcileResult(IntReconcileResult intReconcileResult)
    {
        return intReconcileResultMapper.insertIntReconcileResult(intReconcileResult);
    }

    /**
     * 修改对账结果
     */
    @Override
    public int updateIntReconcileResult(IntReconcileResult intReconcileResult)
    {
        return intReconcileResultMapper.updateIntReconcileResult(intReconcileResult);
    }

    /**
     * 批量删除对账结果
     */
    @Override
    public int deleteIntReconcileResultByIds(Long[] ids)
    {
        return intReconcileResultMapper.deleteIntReconcileResultByIds(ids);
    }

    /**
     * 删除对账结果信息
     */
    @Override
    public int deleteIntReconcileResultById(Long id)
    {
        return intReconcileResultMapper.deleteIntReconcileResultById(id);
    }
}
