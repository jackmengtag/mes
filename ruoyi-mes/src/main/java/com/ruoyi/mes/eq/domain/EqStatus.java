package com.ruoyi.mes.eq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 设备状态对象 eq_status
 *
 * @author ruoyi
 */
public class EqStatus extends BaseEntity
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

    /** 设备状态 */
    @Excel(name = "设备状态")
    private String status;

    /** 变更时间 */
    @Excel(name = "变更时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date changedAt;

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

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setChangedAt(Date changedAt)
    {
        this.changedAt = changedAt;
    }

    public Date getChangedAt()
    {
        return changedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentId", getEquipmentId())
            .append("equipmentCode", getEquipmentCode())
            .append("status", getStatus())
            .append("changedAt", getChangedAt())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
