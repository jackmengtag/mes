package com.ruoyi.mes.tr.service;

import java.util.List;
import com.ruoyi.mes.tr.domain.TrTraceIndex;

/**
 * 追溯索引Service接口
 *
 * @author ruoyi
 */
public interface ITrTraceIndexService
{
    /**
     * 查询追溯索引
     */
    public TrTraceIndex selectTrTraceIndexById(Long id);

    /**
     * 查询追溯索引列表
     */
    public List<TrTraceIndex> selectTrTraceIndexList(TrTraceIndex trTraceIndex);

    /**
     * 新增追溯索引
     */
    public int insertTrTraceIndex(TrTraceIndex trTraceIndex);

    /**
     * 修改追溯索引
     */
    public int updateTrTraceIndex(TrTraceIndex trTraceIndex);

    /**
     * 批量删除追溯索引
     */
    public int deleteTrTraceIndexByIds(Long[] ids);

    /**
     * 删除追溯索引信息
     */
    public int deleteTrTraceIndexById(Long id);
}
