package com.ruoyi.mes.eq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 设备命令对象 eq_command
 *
 * @author ruoyi
 */
public class EqCommand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 命令编号 */
    @Excel(name = "命令编号")
    private String commandNo;

    /** 设备ID */
    @Excel(name = "设备ID", cellType = ColumnType.NUMERIC)
    private Long equipmentId;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String equipmentCode;

    /** 命令类型 */
    @Excel(name = "命令类型")
    private String commandType;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNo;

    /** 程序/配方 */
    @Excel(name = "程序/配方")
    private String programCode;

    /** 参数 */
    @Excel(name = "参数")
    private String params;

    /** 下发人 */
    @Excel(name = "下发人")
    private String sendBy;

    /** 下发时间 */
    @Excel(name = "下发时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /** 响应结果 */
    @Excel(name = "响应结果")
    private String responseResult;

    /** 重试次数 */
    @Excel(name = "重试次数", cellType = ColumnType.NUMERIC)
    private Integer retryCount;

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

    public void setCommandNo(String commandNo)
    {
        this.commandNo = commandNo;
    }

    public String getCommandNo()
    {
        return commandNo;
    }

    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }

    public void setEquipmentCode(String equipmentCode)
    {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentCode()
    {
        return equipmentCode;
    }

    public void setCommandType(String commandType)
    {
        this.commandType = commandType;
    }

    public String getCommandType()
    {
        return commandType;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setProgramCode(String programCode)
    {
        this.programCode = programCode;
    }

    public String getProgramCode()
    {
        return programCode;
    }

    public void setParams(String params)
    {
        this.params = params;
    }

    public String getParams()
    {
        return params;
    }

    public void setSendBy(String sendBy)
    {
        this.sendBy = sendBy;
    }

    public String getSendBy()
    {
        return sendBy;
    }

    public void setSendTime(Date sendTime)
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime()
    {
        return sendTime;
    }

    public void setResponseResult(String responseResult)
    {
        this.responseResult = responseResult;
    }

    public String getResponseResult()
    {
        return responseResult;
    }

    public void setRetryCount(Integer retryCount)
    {
        this.retryCount = retryCount;
    }

    public Integer getRetryCount()
    {
        return retryCount;
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
            .append("commandNo", getCommandNo())
            .append("equipmentId", getEquipmentId())
            .append("equipmentCode", getEquipmentCode())
            .append("commandType", getCommandType())
            .append("productionOrderId", getProductionOrderId())
            .append("serialNo", getSerialNo())
            .append("programCode", getProgramCode())
            .append("params", getParams())
            .append("sendBy", getSendBy())
            .append("sendTime", getSendTime())
            .append("responseResult", getResponseResult())
            .append("retryCount", getRetryCount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
