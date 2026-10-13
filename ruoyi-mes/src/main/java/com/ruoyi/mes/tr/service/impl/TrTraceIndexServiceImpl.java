package com.ruoyi.mes.tr.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.tr.mapper.TrTraceIndexMapper;
import com.ruoyi.mes.tr.domain.TrTraceIndex;
import com.ruoyi.mes.tr.service.ITrTraceIndexService;

/**
 * 追溯索引Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class TrTraceIndexServiceImpl implements ITrTraceIndexService
{
    @Autowired
    private TrTraceIndexMapper trTraceIndexMapper;

    /**
     * 查询追溯索引
     */
    @Override
    public TrTraceIndex selectTrTraceIndexById(Long id)
    {
        return trTraceIndexMapper.selectTrTraceIndexById(id);
    }

    /**
     * 查询追溯索引列表
     */
    @Override
    public List<TrTraceIndex> selectTrTraceIndexList(TrTraceIndex trTraceIndex)
    {
        return trTraceIndexMapper.selectTrTraceIndexList(trTraceIndex);
    }

    /**
     * 新增追溯索引
     */
    @Override
    public int insertTrTraceIndex(TrTraceIndex trTraceIndex)
    {
        return trTraceIndexMapper.insertTrTraceIndex(trTraceIndex);
    }

    /**
     * 修改追溯索引
     */
    @Override
    public int updateTrTraceIndex(TrTraceIndex trTraceIndex)
    {
        return trTraceIndexMapper.updateTrTraceIndex(trTraceIndex);
    }

    /**
     * 批量删除追溯索引
     */
    @Override
    public int deleteTrTraceIndexByIds(Long[] ids)
    {
        return trTraceIndexMapper.deleteTrTraceIndexByIds(ids);
    }

    /**
     * 删除追溯索引信息
     */
    @Override
    public int deleteTrTraceIndexById(Long id)
    {
        return trTraceIndexMapper.deleteTrTraceIndexById(id);
    }
}
