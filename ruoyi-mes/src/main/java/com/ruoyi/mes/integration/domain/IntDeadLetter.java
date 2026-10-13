package com.ruoyi.mes.integration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 集成死信对象 int_dead_letter
 *
 * @author ruoyi
 */
public class IntDeadLetter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 消息编号 */
    @Excel(name = "消息编号")
    private String messageId;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String bizType;

    /** 消息载荷 */
    @Excel(name = "消息载荷")
    private String payload;

    /** 错误信息 */
    @Excel(name = "错误信息")
    private String errorMsg;

    /** 责任人 */
    @Excel(name = "责任人")
    private String handler;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String handleStatus;

    /** 失败时间 */
    @Excel(name = "失败时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date failedAt;

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

    public void setBizType(String bizType)
    {
        this.bizType = bizType;
    }

    public String getBizType()
    {
        return bizType;
    }

    public void setPayload(String payload)
    {
        this.payload = payload;
    }

    public String getPayload()
    {
        return payload;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setHandler(String handler)
    {
        this.handler = handler;
    }

    public String getHandler()
    {
        return handler;
    }

    public void setHandleStatus(String handleStatus)
    {
        this.handleStatus = handleStatus;
    }

    public String getHandleStatus()
    {
        return handleStatus;
    }

    public void setFailedAt(Date failedAt)
    {
        this.failedAt = failedAt;
    }

    public Date getFailedAt()
    {
        return failedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("messageId", getMessageId())
            .append("bizType", getBizType())
            .append("payload", getPayload())
            .append("errorMsg", getErrorMsg())
            .append("handler", getHandler())
            .append("handleStatus", getHandleStatus())
            .append("failedAt", getFailedAt())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
