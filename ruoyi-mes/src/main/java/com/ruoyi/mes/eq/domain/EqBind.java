package com.ruoyi.mes.eq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 设备与工单绑定对象 eq_bind
 *
 * @author ruoyi
 */
public class EqBind extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 设备ID */
    @Excel(name = "设备ID", cellType = ColumnType.NUMERIC)
    private Long equipmentId;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String equipmentCode;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNo;

    /** 班次 */
    @Excel(name = "班次")
    private String shiftCode;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operatorNo;

    /** 绑定时间 */
    @Excel(name = "绑定时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date bindTime;

    /** 解绑时间 */
    @Excel(name = "解绑时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date unbindTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
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

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setShiftCode(String shiftCode)
    {
        this.shiftCode = shiftCode;
    }

    public String getShiftCode()
    {
        return shiftCode;
    }

    public void setOperatorNo(String operatorNo)
    {
        this.operatorNo = operatorNo;
    }

    public String getOperatorNo()
    {
        return operatorNo;
    }

    public void setBindTime(Date bindTime)
    {
        this.bindTime = bindTime;
    }

    public Date getBindTime()
    {
        return bindTime;
    }

    public void setUnbindTime(Date unbindTime)
    {
        this.unbindTime = unbindTime;
    }

    public Date getUnbindTime()
    {
        return unbindTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentId", getEquipmentId())
            .append("equipmentCode", getEquipmentCode())
            .append("productionOrderId", getProductionOrderId())
            .append("operationCode", getOperationCode())
            .append("serialNo", getSerialNo())
            .append("shiftCode", getShiftCode())
            .append("operatorNo", getOperatorNo())
            .append("bindTime", getBindTime())
            .append("unbindTime", getUnbindTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
