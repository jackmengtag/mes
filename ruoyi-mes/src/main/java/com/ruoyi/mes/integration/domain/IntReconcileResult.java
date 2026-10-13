package com.ruoyi.mes.integration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 对账结果对象 int_reconcile_result
 *
 * @author ruoyi
 */
public class IntReconcileResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 对账任务ID */
    @Excel(name = "对账任务ID", cellType = ColumnType.NUMERIC)
    private Long taskId;

    /** 对账对象 */
    @Excel(name = "对账对象")
    private String targetSystem;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String bizType;

    /** 业务键 */
    @Excel(name = "业务键")
    private String bizKey;

    /** 差异类型 */
    @Excel(name = "差异类型")
    private String diffType;

    /** MES值 */
    @Excel(name = "MES值")
    private String mesValue;

    /** 外部值 */
    @Excel(name = "外部值")
    private String extValue;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private String handleStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }

    public void setTargetSystem(String targetSystem)
    {
        this.targetSystem = targetSystem;
    }

    public String getTargetSystem()
    {
        return targetSystem;
    }

    public void setBizType(String bizType)
    {
        this.bizType = bizType;
    }

    public String getBizType()
    {
        return bizType;
    }

    public void setBizKey(String bizKey)
    {
        this.bizKey = bizKey;
    }

    public String getBizKey()
    {
        return bizKey;
    }

    public void setDiffType(String diffType)
    {
        this.diffType = diffType;
    }

    public String getDiffType()
    {
        return diffType;
    }

    public void setMesValue(String mesValue)
    {
        this.mesValue = mesValue;
    }

    public String getMesValue()
    {
        return mesValue;
    }

    public void setExtValue(String extValue)
    {
        this.extValue = extValue;
    }

    public String getExtValue()
    {
        return extValue;
    }

    public void setHandleStatus(String handleStatus)
    {
        this.handleStatus = handleStatus;
    }

    public String getHandleStatus()
    {
        return handleStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("targetSystem", getTargetSystem())
            .append("bizType", getBizType())
            .append("bizKey", getBizKey())
            .append("diffType", getDiffType())
            .append("mesValue", getMesValue())
            .append("extValue", getExtValue())
            .append("handleStatus", getHandleStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
