package com.ruoyi.mes.integration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 集成消息对象 int_message
 *
 * @author ruoyi
 */
public class IntMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 消息编号 */
    @Excel(name = "消息编号")
    private String messageId;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private String messageType;

    /** 消息版本 */
    @Excel(name = "消息版本")
    private String messageVersion;

    /** 来源系统 */
    @Excel(name = "来源系统")
    private String sourceSystem;

    /** 目标系统 */
    @Excel(name = "目标系统")
    private String targetSystem;

    /** 工厂编码 */
    @Excel(name = "工厂编码")
    private String plantCode;

    /** 链路追踪编号 */
    @Excel(name = "链路追踪编号")
    private String traceId;

    /** 幂等键 */
    @Excel(name = "幂等键")
    private String idempotencyKey;

    /** 方向 */
    @Excel(name = "方向")
    private String direction;

    /** 消息载荷 */
    @Excel(name = "消息载荷")
    private String payload;

    /** 消息状态 */
    @Excel(name = "消息状态")
    private String status;

    /** 重试次数 */
    @Excel(name = "重试次数", cellType = ColumnType.NUMERIC)
    private Integer retryCount;

    /** 下次重试时间 */
    @Excel(name = "下次重试时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date nextRetryTime;

    /** 错误信息 */
    @Excel(name = "错误信息")
    private String errorMsg;

    /** 发生时间 */
    @Excel(name = "发生时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date occurredAt;

    /** 处理时间 */
    @Excel(name = "处理时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date processedAt;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public void setMessageType(String messageType)
    {
        this.messageType = messageType;
    }

    public String getMessageType()
    {
        return messageType;
    }

    public void setMessageVersion(String messageVersion)
    {
        this.messageVersion = messageVersion;
    }

    public String getMessageVersion()
    {
        return messageVersion;
    }

    public void setSourceSystem(String sourceSystem)
    {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceSystem()
    {
        return sourceSystem;
    }

    public void setTargetSystem(String targetSystem)
    {
        this.targetSystem = targetSystem;
    }

    public String getTargetSystem()
    {
        return targetSystem;
    }

    public void setPlantCode(String plantCode)
    {
        this.plantCode = plantCode;
    }

    public String getPlantCode()
    {
        return plantCode;
    }

    public void setTraceId(String traceId)
    {
        this.traceId = traceId;
    }

    public String getTraceId()
    {
        return traceId;
    }

    public void setIdempotencyKey(String idempotencyKey)
    {
        this.idempotencyKey = idempotencyKey;
    }

    public String getIdempotencyKey()
    {
        return idempotencyKey;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setPayload(String payload)
    {
        this.payload = payload;
    }

    public String getPayload()
    {
        return payload;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setRetryCount(Integer retryCount)
    {
        this.retryCount = retryCount;
    }

    public Integer getRetryCount()
    {
        return retryCount;
    }

    public void setNextRetryTime(Date nextRetryTime)
    {
        this.nextRetryTime = nextRetryTime;
    }

    public Date getNextRetryTime()
    {
        return nextRetryTime;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setOccurredAt(Date occurredAt)
    {
        this.occurredAt = occurredAt;
    }

    public Date getOccurredAt()
    {
        return occurredAt;
    }

    public void setProcessedAt(Date processedAt)
    {
        this.processedAt = processedAt;
    }

    public Date getProcessedAt()
    {
        return processedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("messageId", getMessageId())
            .append("messageType", getMessageType())
            .append("messageVersion", getMessageVersion())
            .append("sourceSystem", getSourceSystem())
            .append("targetSystem", getTargetSystem())
            .append("plantCode", getPlantCode())
            .append("traceId", getTraceId())
            .append("idempotencyKey", getIdempotencyKey())
            .append("direction", getDirection())
            .append("payload", getPayload())
            .append("status", getStatus())
            .append("retryCount", getRetryCount())
            .append("nextRetryTime", getNextRetryTime())
            .append("errorMsg", getErrorMsg())
            .append("occurredAt", getOccurredAt())
            .append("processedAt", getProcessedAt())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
