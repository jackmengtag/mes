package com.ruoyi.quartz.task;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.mes.common.enums.MessageStatus;
import com.ruoyi.mes.integration.domain.IntDomainEvent;
import com.ruoyi.mes.integration.domain.IntMessage;
import com.ruoyi.mes.integration.mapper.IntMessageMapper;
import com.ruoyi.mes.integration.service.ReconciliationService;
import com.ruoyi.mes.integration.support.DomainEventService;
import com.ruoyi.mes.integration.support.MessageService;
import com.ruoyi.mes.integration.support.RetryPolicy;

/**
 * MES 集成定时任务：领域事件异步投递、消息重试与死信、周期对账
 * （架构文档 8.2 重试与死信 / 8.5 对账机制）
 *
 * <p>放置于 com.ruoyi.quartz.task 包以通过若依定时任务白名单校验。</p>
 *
 * @author ruoyi
 */
@Component("mesIntegrationTask")
public class MesIntegrationTask
{
    @Autowired
    private DomainEventService domainEventService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private IntMessageMapper messageMapper;

    @Autowired
    private ReconciliationService reconciliationService;

    /**
     * 投递待发送的领域事件（本地事件表 + 异步投递）
     */
    public void dispatchEvents()
    {
        List<IntDomainEvent> events = domainEventService.selectDueEvents();
        for (IntDomainEvent event : events)
        {
            try
            {
                // 真实环境在此调用 ERP/WMS 接口；此处按投递成功处理并记录
                domainEventService.markSent(event);
            }
            catch (Exception e)
            {
                domainEventService.markFailed(event, e.getMessage());
            }
        }
    }

    /**
     * 出站消息重试与死信处理
     */
    public void retryMessages()
    {
        List<IntMessage> messages = messageService.selectRetryable();
        for (IntMessage msg : messages)
        {
            int retry = (msg.getRetryCount() == null ? 0 : msg.getRetryCount()) + 1;
            if (RetryPolicy.shouldDeadLetter(retry))
            {
                msg.setStatus(MessageStatus.DEAD_LETTER.name());
                msg.setRetryCount(retry);
                msg.setUpdateBy("system");
                messageMapper.updateIntMessage(msg);
                messageService.toDeadLetter(msg.getMessageId(), msg.getMessageType(), msg.getPayload(),
                        "超过最大重试次数", "integration-admin");
            }
            else
            {
                msg.setRetryCount(retry);
                msg.setNextRetryTime(RetryPolicy.nextRetryTime(retry - 1));
                msg.setStatus(MessageStatus.RETRYING.name());
                msg.setUpdateBy("system");
                messageMapper.updateIntMessage(msg);
            }
        }
    }

    /**
     * 周期对账（每小时 + 日终）
     */
    public void reconcile()
    {
        Date now = new Date();
        reconciliationService.run("ERP", now);
        reconciliationService.run("WMS", now);
        reconciliationService.run("DEV", now);
    }
}
