package com.ruoyi.mes.ex.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 现场异常上报对象 ex_exception_report
 *
 * @author ruoyi
 */
public class ExExceptionReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 异常单号 */
    @Excel(name = "异常单号")
    private String exceptionNo;

    /** 异常类型 */
    @Excel(name = "异常类型")
    private String exceptionType;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工序任务ID */
    @Excel(name = "工序任务ID", cellType = ColumnType.NUMERIC)
    private Long operationTaskId;

    /** 设备ID */
    @Excel(name = "设备ID", cellType = ColumnType.NUMERIC)
    private Long equipmentId;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNo;

    /** 严重程度 */
    @Excel(name = "严重程度")
    private String severity;

    /** 异常描述 */
    @Excel(name = "异常描述")
    private String description;

    /** 上报人 */
    @Excel(name = "上报人")
    private String reporterNo;

    /** 上报时间 */
    @Excel(name = "上报时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reportedAt;

    /** 处理人 */
    @Excel(name = "处理人")
    private String handlerNo;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String handleResult;

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

    public void setExceptionNo(String exceptionNo)
    {
        this.exceptionNo = exceptionNo;
    }

    public String getExceptionNo()
    {
        return exceptionNo;
    }

    public void setExceptionType(String exceptionType)
    {
        this.exceptionType = exceptionType;
    }

    public String getExceptionType()
    {
        return exceptionType;
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

    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setReporterNo(String reporterNo)
    {
        this.reporterNo = reporterNo;
    }

    public String getReporterNo()
    {
        return reporterNo;
    }

    public void setReportedAt(Date reportedAt)
    {
        this.reportedAt = reportedAt;
    }

    public Date getReportedAt()
    {
        return reportedAt;
    }

    public void setHandlerNo(String handlerNo)
    {
        this.handlerNo = handlerNo;
    }

    public String getHandlerNo()
    {
        return handlerNo;
    }

    public void setHandleResult(String handleResult)
    {
        this.handleResult = handleResult;
    }

    public String getHandleResult()
    {
        return handleResult;
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
            .append("exceptionNo", getExceptionNo())
            .append("exceptionType", getExceptionType())
            .append("productionOrderId", getProductionOrderId())
            .append("operationTaskId", getOperationTaskId())
            .append("equipmentId", getEquipmentId())
            .append("serialNo", getSerialNo())
            .append("severity", getSeverity())
            .append("description", getDescription())
            .append("reporterNo", getReporterNo())
            .append("reportedAt", getReportedAt())
            .append("handlerNo", getHandlerNo())
            .append("handleResult", getHandleResult())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
