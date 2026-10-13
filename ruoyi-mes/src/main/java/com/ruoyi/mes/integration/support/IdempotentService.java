package com.ruoyi.mes.integration.support;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.integration.domain.IntIdempotentRecord;
import com.ruoyi.mes.integration.mapper.IntIdempotentRecordMapper;

/**
 * 幂等处理服务（架构文档 8.1 幂等设计）
 *
 * <p>依赖数据库唯一约束保证幂等：重复请求(键相同)直接返回首次业务结果。</p>
 *
 * @author ruoyi
 */
@Service
public class IdempotentService
{
    @Autowired
    private IntIdempotentRecordMapper idempotentMapper;

    /**
     * 幂等结果
     */
    public static class Result
    {
        /** 是否为重复请求 */
        private final boolean duplicated;

        /** 首次业务记录 */
        private final IntIdempotentRecord record;

        public Result(boolean duplicated, IntIdempotentRecord record)
        {
            this.duplicated = duplicated;
            this.record = record;
        }

        public boolean isDuplicated()
        {
            return duplicated;
        }

        public IntIdempotentRecord getRecord()
        {
            return record;
        }
    }

    /**
     * 查找幂等记录
     */
    public IntIdempotentRecord find(String key)
    {
        if (key == null || key.isEmpty())
        {
            return null;
        }
        IntIdempotentRecord query = new IntIdempotentRecord();
        query.setIdempotencyKey(key);
        List<IntIdempotentRecord> list = idempotentMapper.selectIntIdempotentRecordList(query);
        for (IntIdempotentRecord r : list)
        {
            if (key.equals(r.getIdempotencyKey()))
            {
                return r;
            }
        }
        return null;
    }

    /**
     * 尝试登记幂等键。若已存在则返回 duplicated=true（携带首次结果），不产生重复业务。
     */
    public Result tryRegister(String key, String bizType)
    {
        IntIdempotentRecord existing = find(key);
        if (existing != null)
        {
            return new Result(true, existing);
        }
        IntIdempotentRecord record = new IntIdempotentRecord();
        record.setIdempotencyKey(key);
        record.setBizType(bizType);
        record.setResultCode("PROCESSING");
        record.setCreateBy("system");
        try
        {
            idempotentMapper.insertIntIdempotentRecord(record);
            return new Result(false, record);
        }
        catch (DuplicateKeyException e)
        {
            // 并发重复提交，读取首次记录返回
            return new Result(true, find(key));
        }
    }

    /**
     * 标记幂等处理完成
     */
    public void complete(IntIdempotentRecord record, Long bizId, String resultCode, String resultBody)
    {
        if (record == null || record.getId() == null)
        {
            return;
        }
        record.setBizId(bizId);
        record.setResultCode(resultCode);
        record.setResultBody(resultBody);
        record.setUpdateBy("system");
        idempotentMapper.updateIntIdempotentRecord(record);
    }
}
