package com.ruoyi.mes.integration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 领域事件对象 int_domain_event
 *
 * @author ruoyi
 */
public class IntDomainEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 事件编号 */
    @Excel(name = "事件编号")
    private String eventId;

    /** 事件类型 */
    @Excel(name = "事件类型")
    private String eventType;

    /** 聚合类型 */
    @Excel(name = "聚合类型")
    private String aggregateType;

    /** 聚合ID */
    @Excel(name = "聚合ID", cellType = ColumnType.NUMERIC)
    private Long aggregateId;

    /** 事件载荷 */
    @Excel(name = "事件载荷")
    private String payload;

    /** 链路追踪编号 */
    @Excel(name = "链路追踪编号")
    private String traceId;

    /** 状态 */
    @Excel(name = "状态")
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

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    public String getEventId()
    {
        return eventId;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setAggregateType(String aggregateType)
    {
        this.aggregateType = aggregateType;
    }

    public String getAggregateType()
    {
        return aggregateType;
    }

    public void setAggregateId(Long aggregateId)
    {
        this.aggregateId = aggregateId;
    }

    public Long getAggregateId()
    {
        return aggregateId;
    }

    public void setPayload(String payload)
    {
        this.payload = payload;
    }

    public String getPayload()
    {
        return payload;
    }

    public void setTraceId(String traceId)
    {
        this.traceId = traceId;
    }

    public String getTraceId()
    {
        return traceId;
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
            .append("eventId", getEventId())
            .append("eventType", getEventType())
            .append("aggregateType", getAggregateType())
            .append("aggregateId", getAggregateId())
            .append("payload", getPayload())
            .append("traceId", getTraceId())
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
