package com.ruoyi.mes.ps.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 排程任务对象 ps_schedule_task
 *
 * @author ruoyi
 */
public class PsScheduleTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 计划ID */
    @Excel(name = "计划ID", cellType = ColumnType.NUMERIC)
    private Long planId;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long workOrderId;

    /** 产线 */
    @Excel(name = "产线", cellType = ColumnType.NUMERIC)
    private Long lineId;

    /** 排序号 */
    @Excel(name = "排序号", cellType = ColumnType.NUMERIC)
    private Integer sequenceNo;

    /** 计划开始 */
    @Excel(name = "计划开始", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date plannedStartTime;

    /** 计划结束 */
    @Excel(name = "计划结束", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date plannedEndTime;

    /** 排程方式 */
    @Excel(name = "排程方式")
    private String scheduleType;

    /** 是否冲突 */
    @Excel(name = "是否冲突")
    private String conflictFlag;

    /** 冲突原因 */
    @Excel(name = "冲突原因")
    private String conflictReason;

    /** 调整原因 */
    @Excel(name = "调整原因")
    private String adjustReason;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getPlanId()
    {
        return planId;
    }

    public void setWorkOrderId(Long workOrderId)
    {
        this.workOrderId = workOrderId;
    }

    public Long getWorkOrderId()
    {
        return workOrderId;
    }

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setSequenceNo(Integer sequenceNo)
    {
        this.sequenceNo = sequenceNo;
    }

    public Integer getSequenceNo()
    {
        return sequenceNo;
    }

    public void setPlannedStartTime(Date plannedStartTime)
    {
        this.plannedStartTime = plannedStartTime;
    }

    public Date getPlannedStartTime()
    {
        return plannedStartTime;
    }

    public void setPlannedEndTime(Date plannedEndTime)
    {
        this.plannedEndTime = plannedEndTime;
    }

    public Date getPlannedEndTime()
    {
        return plannedEndTime;
    }

    public void setScheduleType(String scheduleType)
    {
        this.scheduleType = scheduleType;
    }

    public String getScheduleType()
    {
        return scheduleType;
    }

    public void setConflictFlag(String conflictFlag)
    {
        this.conflictFlag = conflictFlag;
    }

    public String getConflictFlag()
    {
        return conflictFlag;
    }

    public void setConflictReason(String conflictReason)
    {
        this.conflictReason = conflictReason;
    }

    public String getConflictReason()
    {
        return conflictReason;
    }

    public void setAdjustReason(String adjustReason)
    {
        this.adjustReason = adjustReason;
    }

    public String getAdjustReason()
    {
        return adjustReason;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planId", getPlanId())
            .append("workOrderId", getWorkOrderId())
            .append("lineId", getLineId())
            .append("sequenceNo", getSequenceNo())
            .append("plannedStartTime", getPlannedStartTime())
            .append("plannedEndTime", getPlannedEndTime())
            .append("scheduleType", getScheduleType())
            .append("conflictFlag", getConflictFlag())
            .append("conflictReason", getConflictReason())
            .append("adjustReason", getAdjustReason())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
