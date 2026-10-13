package com.ruoyi.mes.integration.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.integration.domain.IntDomainEvent;
import com.ruoyi.mes.integration.mapper.IntDomainEventMapper;

/**
 * 领域事件服务（架构文档 8.4 事务边界 / 本地事件表 + 异步投递）
 *
 * <p>生产事务只写本地事件表（待发送），由定时任务异步投递，外部系统失败不回滚现场作业。</p>
 *
 * @author ruoyi
 */
@Service
public class DomainEventService
{
    /** 待发送 */
    public static final String STATUS_NEW = "NEW";
    /** 已发送 */
    public static final String STATUS_SENT = "SENT";
    /** 死信 */
    public static final String STATUS_DEAD = "DEAD";

    @Autowired
    private IntDomainEventMapper domainEventMapper;

    /**
     * 发布领域事件（写入本地事件表，待异步投递）
     */
    public IntDomainEvent publish(String eventType, String aggregateType, Long aggregateId, String payload, String traceId)
    {
        IntDomainEvent event = new IntDomainEvent();
        event.setEventId(MesSeqUtils.generate(MesConstants.PREFIX_EVENT));
        event.setEventType(eventType);
        event.setAggregateType(aggregateType);
        event.setAggregateId(aggregateId);
        event.setPayload(payload);
        event.setTraceId(traceId);
        event.setStatus(STATUS_NEW);
        event.setRetryCount(0);
        event.setOccurredAt(new Date());
        event.setCreateBy("system");
        domainEventMapper.insertIntDomainEvent(event);
        return event;
    }

    /**
     * 查询到期的待投递事件
     */
    public List<IntDomainEvent> selectDueEvents()
    {
        List<IntDomainEvent> result = new ArrayList<>();
        Date now = new Date();
        for (String status : new String[] { STATUS_NEW })
        {
            IntDomainEvent query = new IntDomainEvent();
            query.setStatus(status);
            for (IntDomainEvent e : domainEventMapper.selectIntDomainEventList(query))
            {
                if (e.getNextRetryTime() == null || !e.getNextRetryTime().after(now))
                {
                    result.add(e);
                }
            }
        }
        return result;
    }

    /**
     * 标记事件发送成功
     */
    public void markSent(IntDomainEvent event)
    {
        event.setStatus(STATUS_SENT);
        event.setProcessedAt(new Date());
        event.setErrorMsg(null);
        event.setUpdateBy("system");
        domainEventMapper.updateIntDomainEvent(event);
    }

    /**
     * 标记事件失败：按重试策略决定重试或进入死信
     */
    public void markFailed(IntDomainEvent event, String errorMsg)
    {
        int retry = event.getRetryCount() == null ? 0 : event.getRetryCount();
        retry = retry + 1;
        event.setRetryCount(retry);
        event.setErrorMsg(StringUtils.substring(errorMsg, 0, 2000));
        if (RetryPolicy.shouldDeadLetter(retry))
        {
            event.setStatus(STATUS_DEAD);
        }
        else
        {
            event.setStatus(STATUS_NEW);
            event.setNextRetryTime(RetryPolicy.nextRetryTime(retry - 1));
        }
        event.setUpdateBy("system");
        domainEventMapper.updateIntDomainEvent(event);
    }
}
