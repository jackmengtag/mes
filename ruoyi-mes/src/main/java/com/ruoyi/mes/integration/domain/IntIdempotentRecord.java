package com.ruoyi.mes.integration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 幂等记录对象 int_idempotent_record
 *
 * @author ruoyi
 */
public class IntIdempotentRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 幂等键 */
    @Excel(name = "幂等键")
    private String idempotencyKey;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String bizType;

    /** 业务ID */
    @Excel(name = "业务ID", cellType = ColumnType.NUMERIC)
    private Long bizId;

    /** 结果码 */
    @Excel(name = "结果码")
    private String resultCode;

    /** 结果内容 */
    @Excel(name = "结果内容")
    private String resultBody;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setIdempotencyKey(String idempotencyKey)
    {
        this.idempotencyKey = idempotencyKey;
    }

    public String getIdempotencyKey()
    {
        return idempotencyKey;
    }

    public void setBizType(String bizType)
    {
        this.bizType = bizType;
    }

    public String getBizType()
    {
        return bizType;
    }

    public void setBizId(Long bizId)
    {
        this.bizId = bizId;
    }

    public Long getBizId()
    {
        return bizId;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultBody(String resultBody)
    {
        this.resultBody = resultBody;
    }

    public String getResultBody()
    {
        return resultBody;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("idempotencyKey", getIdempotencyKey())
            .append("bizType", getBizType())
            .append("bizId", getBizId())
            .append("resultCode", getResultCode())
            .append("resultBody", getResultBody())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
