package com.ruoyi.mes.ex.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 工时记录对象 ex_work_hour
 *
 * @author ruoyi
 */
public class ExWorkHour extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工序任务ID */
    @Excel(name = "工序任务ID", cellType = ColumnType.NUMERIC)
    private Long operationTaskId;

    /** 工时类型 */
    @Excel(name = "工时类型")
    private String workType;

    /** 工时(分) */
    @Excel(name = "工时(分)", cellType = ColumnType.NUMERIC)
    private BigDecimal minutes;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operatorNo;

    /** 记录时间 */
    @Excel(name = "记录时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setOperationTaskId(Long operationTaskId)
    {
        this.operationTaskId = operationTaskId;
    }

    public Long getOperationTaskId()
    {
        return operationTaskId;
    }

    public void setWorkType(String workType)
    {
        this.workType = workType;
    }

    public String getWorkType()
    {
        return workType;
    }

    public void setMinutes(BigDecimal minutes)
    {
        this.minutes = minutes;
    }

    public BigDecimal getMinutes()
    {
        return minutes;
    }

    public void setOperatorNo(String operatorNo)
    {
        this.operatorNo = operatorNo;
    }

    public String getOperatorNo()
    {
        return operatorNo;
    }

    public void setRecordTime(Date recordTime)
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime()
    {
        return recordTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productionOrderId", getProductionOrderId())
            .append("operationTaskId", getOperationTaskId())
            .append("workType", getWorkType())
            .append("minutes", getMinutes())
            .append("operatorNo", getOperatorNo())
            .append("recordTime", getRecordTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
