package com.ruoyi.mes.integration.service;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntIdempotentRecord;

/**
 * 幂等记录Service接口
 *
 * @author ruoyi
 */
public interface IIntIdempotentRecordService
{
    /**
     * 查询幂等记录
     */
    public IntIdempotentRecord selectIntIdempotentRecordById(Long id);

    /**
     * 查询幂等记录列表
     */
    public List<IntIdempotentRecord> selectIntIdempotentRecordList(IntIdempotentRecord intIdempotentRecord);

    /**
     * 新增幂等记录
     */
    public int insertIntIdempotentRecord(IntIdempotentRecord intIdempotentRecord);

    /**
     * 修改幂等记录
     */
    public int updateIntIdempotentRecord(IntIdempotentRecord intIdempotentRecord);

    /**
     * 批量删除幂等记录
     */
    public int deleteIntIdempotentRecordByIds(Long[] ids);

    /**
     * 删除幂等记录信息
     */
    public int deleteIntIdempotentRecordById(Long id);
}
