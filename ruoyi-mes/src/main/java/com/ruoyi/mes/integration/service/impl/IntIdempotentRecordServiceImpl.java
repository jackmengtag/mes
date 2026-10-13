package com.ruoyi.mes.integration.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.integration.mapper.IntIdempotentRecordMapper;
import com.ruoyi.mes.integration.domain.IntIdempotentRecord;
import com.ruoyi.mes.integration.service.IIntIdempotentRecordService;

/**
 * 幂等记录Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class IntIdempotentRecordServiceImpl implements IIntIdempotentRecordService
{
    @Autowired
    private IntIdempotentRecordMapper intIdempotentRecordMapper;

    /**
     * 查询幂等记录
     */
    @Override
    public IntIdempotentRecord selectIntIdempotentRecordById(Long id)
    {
        return intIdempotentRecordMapper.selectIntIdempotentRecordById(id);
    }

    /**
     * 查询幂等记录列表
     */
    @Override
    public List<IntIdempotentRecord> selectIntIdempotentRecordList(IntIdempotentRecord intIdempotentRecord)
    {
        return intIdempotentRecordMapper.selectIntIdempotentRecordList(intIdempotentRecord);
    }

    /**
     * 新增幂等记录
     */
    @Override
    public int insertIntIdempotentRecord(IntIdempotentRecord intIdempotentRecord)
    {
        return intIdempotentRecordMapper.insertIntIdempotentRecord(intIdempotentRecord);
    }

    /**
     * 修改幂等记录
     */
    @Override
    public int updateIntIdempotentRecord(IntIdempotentRecord intIdempotentRecord)
    {
        return intIdempotentRecordMapper.updateIntIdempotentRecord(intIdempotentRecord);
    }

    /**
     * 批量删除幂等记录
     */
    @Override
    public int deleteIntIdempotentRecordByIds(Long[] ids)
    {
        return intIdempotentRecordMapper.deleteIntIdempotentRecordByIds(ids);
    }

    /**
     * 删除幂等记录信息
     */
    @Override
    public int deleteIntIdempotentRecordById(Long id)
    {
        return intIdempotentRecordMapper.deleteIntIdempotentRecordById(id);
    }
}
