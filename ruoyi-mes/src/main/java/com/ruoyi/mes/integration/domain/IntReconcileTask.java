package com.ruoyi.mes.integration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 对账任务对象 int_reconcile_task
 *
 * @author ruoyi
 */
public class IntReconcileTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private String taskNo;

    /** 对账对象 */
    @Excel(name = "对账对象")
    private String targetSystem;

    /** 对账日期 */
    @Excel(name = "对账日期", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date bizDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 差异数量 */
    @Excel(name = "差异数量", cellType = ColumnType.NUMERIC)
    private Integer diffCount;

    /** 开始时间 */
    @Excel(name = "开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTaskNo(String taskNo)
    {
        this.taskNo = taskNo;
    }

    public String getTaskNo()
    {
        return taskNo;
    }

    public void setTargetSystem(String targetSystem)
    {
        this.targetSystem = targetSystem;
    }

    public String getTargetSystem()
    {
        return targetSystem;
    }

    public void setBizDate(Date bizDate)
    {
        this.bizDate = bizDate;
    }

    public Date getBizDate()
    {
        return bizDate;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setDiffCount(Integer diffCount)
    {
        this.diffCount = diffCount;
    }

    public Integer getDiffCount()
    {
        return diffCount;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskNo", getTaskNo())
            .append("targetSystem", getTargetSystem())
            .append("bizDate", getBizDate())
            .append("status", getStatus())
            .append("diffCount", getDiffCount())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
