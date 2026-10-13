package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备档案对象 md_equipment
 *
 * @author ruoyi
 */
public class MdEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String equipmentCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equipmentName;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String equipmentType;

    /** 归属工作中心 */
    @Excel(name = "归属工作中心", cellType = ColumnType.NUMERIC)
    private Long workCenterId;

    /** 采集方式 */
    @Excel(name = "采集方式")
    private String collectMode;

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

    public void setEquipmentCode(String equipmentCode)
    {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentCode()
    {
        return equipmentCode;
    }

    public void setEquipmentName(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName()
    {
        return equipmentName;
    }

    public void setEquipmentType(String equipmentType)
    {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentType()
    {
        return equipmentType;
    }

    public void setWorkCenterId(Long workCenterId)
    {
        this.workCenterId = workCenterId;
    }

    public Long getWorkCenterId()
    {
        return workCenterId;
    }

    public void setCollectMode(String collectMode)
    {
        this.collectMode = collectMode;
    }

    public String getCollectMode()
    {
        return collectMode;
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
            .append("equipmentCode", getEquipmentCode())
            .append("equipmentName", getEquipmentName())
            .append("equipmentType", getEquipmentType())
            .append("workCenterId", getWorkCenterId())
            .append("collectMode", getCollectMode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
