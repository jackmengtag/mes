package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备采集点位对象 md_equipment_point
 *
 * @author ruoyi
 */
public class MdEquipmentPoint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 设备ID */
    @Excel(name = "设备ID", cellType = ColumnType.NUMERIC)
    private Long equipmentId;

    /** 点位编码 */
    @Excel(name = "点位编码")
    private String pointCode;

    /** 点位名称 */
    @Excel(name = "点位名称")
    private String pointName;

    /** 通信协议 */
    @Excel(name = "通信协议")
    private String protocol;

    /** 点位地址 */
    @Excel(name = "点位地址")
    private String address;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String dataType;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 变更原因 */
    @Excel(name = "变更原因")
    private String changeReason;

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

    public void setPointCode(String pointCode)
    {
        this.pointCode = pointCode;
    }

    public String getPointCode()
    {
        return pointCode;
    }

    public void setPointName(String pointName)
    {
        this.pointName = pointName;
    }

    public String getPointName()
    {
        return pointName;
    }

    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setChangeReason(String changeReason)
    {
        this.changeReason = changeReason;
    }

    public String getChangeReason()
    {
        return changeReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentId", getEquipmentId())
            .append("pointCode", getPointCode())
            .append("pointName", getPointName())
            .append("protocol", getProtocol())
            .append("address", getAddress())
            .append("dataType", getDataType())
            .append("unit", getUnit())
            .append("changeReason", getChangeReason())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
