package com.ruoyi.mes.common.domain;

import java.io.Serializable;

/**
 * 统一消息头（架构文档 7.3 统一消息契约）
 *
 * @author ruoyi
 */
public class MesMessageHeader implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 消息编号 */
    private String messageId;

    /** 消息类型 */
    private String messageType;

    /** 消息版本 */
    private String messageVersion = "1.0";

    /** 来源系统 */
    private String sourceSystem;

    /** 目标系统 */
    private String targetSystem;

    /** 工厂编码 */
    private String plantCode;

    /** 发生时间 ISO8601 */
    private String occurredAt;

    /** 链路追踪编号 */
    private String traceId;

    /** 幂等键 */
    private String idempotencyKey;

    public String getMessageId()
    {
        return messageId;
    }

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public String getMessageType()
    {
        return messageType;
    }

    public void setMessageType(String messageType)
    {
        this.messageType = messageType;
    }

    public String getMessageVersion()
    {
        return messageVersion;
    }

    public void setMessageVersion(String messageVersion)
    {
        this.messageVersion = messageVersion;
    }

    public String getSourceSystem()
    {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem)
    {
        this.sourceSystem = sourceSystem;
    }

    public String getTargetSystem()
    {
        return targetSystem;
    }

    public void setTargetSystem(String targetSystem)
    {
        this.targetSystem = targetSystem;
    }

    public String getPlantCode()
    {
        return plantCode;
    }

    public void setPlantCode(String plantCode)
    {
        this.plantCode = plantCode;
    }

    public String getOccurredAt()
    {
        return occurredAt;
    }

    public void setOccurredAt(String occurredAt)
    {
        this.occurredAt = occurredAt;
    }

    public String getTraceId()
    {
        return traceId;
    }

    public void setTraceId(String traceId)
    {
        this.traceId = traceId;
    }

    public String getIdempotencyKey()
    {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey)
    {
        this.idempotencyKey = idempotencyKey;
    }
}
