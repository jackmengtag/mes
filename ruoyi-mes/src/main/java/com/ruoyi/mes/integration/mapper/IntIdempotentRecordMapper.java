package com.ruoyi.mes.integration.mapper;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntIdempotentRecord;

/**
 * 幂等记录Mapper接口
 *
 * @author ruoyi
 */
public interface IntIdempotentRecordMapper
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
     * 删除幂等记录
     */
    public int deleteIntIdempotentRecordById(Long id);

    /**
     * 批量删除幂等记录
     */
    public int deleteIntIdempotentRecordByIds(Long[] ids);
}
