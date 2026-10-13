package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 主数据版本审批对象 md_version_approval
 *
 * @author ruoyi
 */
public class MdVersionApproval extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String bizType;

    /** 业务ID */
    @Excel(name = "业务ID", cellType = ColumnType.NUMERIC)
    private Long bizId;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionNo;

    /** 版本状态 */
    @Excel(name = "版本状态")
    private String docStatus;

    /** 变更原因 */
    @Excel(name = "变更原因")
    private String changeReason;

    /** 变更人 */
    @Excel(name = "变更人")
    private String changeBy;

    /** 变更时间 */
    @Excel(name = "变更时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date changeTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
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

    public void setVersionNo(String versionNo)
    {
        this.versionNo = versionNo;
    }

    public String getVersionNo()
    {
        return versionNo;
    }

    public void setDocStatus(String docStatus)
    {
        this.docStatus = docStatus;
    }

    public String getDocStatus()
    {
        return docStatus;
    }

    public void setChangeReason(String changeReason)
    {
        this.changeReason = changeReason;
    }

    public String getChangeReason()
    {
        return changeReason;
    }

    public void setChangeBy(String changeBy)
    {
        this.changeBy = changeBy;
    }

    public String getChangeBy()
    {
        return changeBy;
    }

    public void setChangeTime(Date changeTime)
    {
        this.changeTime = changeTime;
    }

    public Date getChangeTime()
    {
        return changeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bizType", getBizType())
            .append("bizId", getBizId())
            .append("versionNo", getVersionNo())
            .append("docStatus", getDocStatus())
            .append("changeReason", getChangeReason())
            .append("changeBy", getChangeBy())
            .append("changeTime", getChangeTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
