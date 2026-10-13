package com.ruoyi.mes.integration.support;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.domain.MesMessageHeader;
import com.ruoyi.mes.common.enums.MessageStatus;
import com.ruoyi.mes.common.enums.SyncStatus;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.integration.domain.IntDeadLetter;
import com.ruoyi.mes.integration.domain.IntMessage;
import com.ruoyi.mes.integration.mapper.IntDeadLetterMapper;
import com.ruoyi.mes.integration.mapper.IntMessageMapper;

/**
 * 集成消息服务：记录入站/出站报文，管理重试与死信（架构文档 7.2 / 8.2）
 *
 * @author ruoyi
 */
@Service
public class MessageService
{
    /** 入站 */
    public static final String DIRECTION_IN = "IN";
    /** 出站 */
    public static final String DIRECTION_OUT = "OUT";

    @Autowired
    private IntMessageMapper messageMapper;

    @Autowired
    private IntDeadLetterMapper deadLetterMapper;

    /**
     * 记录入站消息
     */
    public IntMessage recordInbound(MesMessageHeader header, String payload)
    {
        return record(header, payload, DIRECTION_IN);
    }

    /**
     * 记录出站消息（MES -> 外部系统）
     */
    public IntMessage recordOutbound(String messageType, String targetSystem, String idempotencyKey, String payload)
    {
        MesMessageHeader header = new MesMessageHeader();
        header.setMessageId(MesSeqUtils.generate("MSG"));
        header.setMessageType(messageType);
        header.setSourceSystem("MES");
        header.setTargetSystem(targetSystem);
        header.setPlantCode(MesConstants.DEFAULT_PLANT);
        header.setIdempotencyKey(idempotencyKey);
        return record(header, payload, DIRECTION_OUT);
    }

    private IntMessage record(MesMessageHeader header, String payload, String direction)
    {
        IntMessage msg = new IntMessage();
        if (header != null)
        {
            msg.setMessageId(header.getMessageId() != null ? header.getMessageId() : MesSeqUtils.generate("MSG"));
            msg.setMessageType(header.getMessageType());
            msg.setMessageVersion(header.getMessageVersion());
            msg.setSourceSystem(header.getSourceSystem());
            msg.setTargetSystem(header.getTargetSystem());
            msg.setPlantCode(header.getPlantCode());
            msg.setTraceId(header.getTraceId());
            msg.setIdempotencyKey(header.getIdempotencyKey());
        }
        msg.setDirection(direction);
        msg.setPayload(payload);
        msg.setStatus(MessageStatus.CREATED.name());
        msg.setRetryCount(0);
        msg.setOccurredAt(new Date());
        msg.setCreateBy("system");
        messageMapper.insertIntMessage(msg);
        return msg;
    }

    /**
     * 标记出站消息处理结果
     */
    public void markResult(IntMessage msg, boolean success, String errorMsg)
    {
        if (msg == null || msg.getId() == null)
        {
            return;
        }
        msg.setStatus(success ? MessageStatus.CONFIRMED.name() : MessageStatus.RETRYING.name());
        msg.setErrorMsg(StringUtils.substring(errorMsg, 0, 2000));
        msg.setProcessedAt(new Date());
        msg.setUpdateBy("system");
        messageMapper.updateIntMessage(msg);
    }

    /**
     * 进入死信队列并生成异常单
     */
    public void toDeadLetter(String messageId, String bizType, String payload, String errorMsg, String handler)
    {
        IntDeadLetter dead = new IntDeadLetter();
        dead.setMessageId(messageId);
        dead.setBizType(bizType);
        dead.setPayload(payload);
        dead.setErrorMsg(StringUtils.substring(errorMsg, 0, 2000));
        dead.setHandler(handler);
        dead.setHandleStatus(SyncStatus.DEAD_LETTER.name());
        dead.setFailedAt(new Date());
        dead.setCreateBy("system");
        deadLetterMapper.insertIntDeadLetter(dead);
    }

    /**
     * 按幂等键查找消息（用于外部回执确认）
     */
    public IntMessage findByIdempotencyKey(String key)
    {
        if (StringUtils.isEmpty(key))
        {
            return null;
        }
        IntMessage query = new IntMessage();
        query.setIdempotencyKey(key);
        for (IntMessage m : messageMapper.selectIntMessageList(query))
        {
            if (key.equals(m.getIdempotencyKey()))
            {
                return m;
            }
        }
        return null;
    }

    /**
     * 查询待重试的出站消息
     */
    public List<IntMessage> selectRetryable()
    {
        IntMessage query = new IntMessage();
        query.setDirection(DIRECTION_OUT);
        query.setStatus(MessageStatus.RETRYING.name());
        return messageMapper.selectIntMessageList(query);
    }
}
