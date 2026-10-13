package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 工艺工序对象 md_route_operation
 *
 * @author ruoyi
 */
public class MdRouteOperation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 工艺路线ID */
    @Excel(name = "工艺路线ID", cellType = ColumnType.NUMERIC)
    private Long routeId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String operationName;

    /** 工序顺序号 */
    @Excel(name = "工序顺序号", cellType = ColumnType.NUMERIC)
    private Integer sequenceNo;

    /** 工作中心 */
    @Excel(name = "工作中心", cellType = ColumnType.NUMERIC)
    private Long workCenterId;

    /** 设备类型要求 */
    @Excel(name = "设备类型要求")
    private String equipmentType;

    /** 技能要求 */
    @Excel(name = "技能要求")
    private String skillRequirement;

    /** 是否强制检验 */
    @Excel(name = "是否强制检验")
    private String forceInspect;

    /** 检验方案 */
    @Excel(name = "检验方案", cellType = ColumnType.NUMERIC)
    private Long inspectionPlanId;

    /** 标准工时 */
    @Excel(name = "标准工时", cellType = ColumnType.NUMERIC)
    private BigDecimal standardHours;

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

    public void setRouteId(Long routeId)
    {
        this.routeId = routeId;
    }

    public Long getRouteId()
    {
        return routeId;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setOperationName(String operationName)
    {
        this.operationName = operationName;
    }

    public String getOperationName()
    {
        return operationName;
    }

    public void setSequenceNo(Integer sequenceNo)
    {
        this.sequenceNo = sequenceNo;
    }

    public Integer getSequenceNo()
    {
        return sequenceNo;
    }

    public void setWorkCenterId(Long workCenterId)
    {
        this.workCenterId = workCenterId;
    }

    public Long getWorkCenterId()
    {
        return workCenterId;
    }

    public void setEquipmentType(String equipmentType)
    {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentType()
    {
        return equipmentType;
    }

    public void setSkillRequirement(String skillRequirement)
    {
        this.skillRequirement = skillRequirement;
    }

    public String getSkillRequirement()
    {
        return skillRequirement;
    }

    public void setForceInspect(String forceInspect)
    {
        this.forceInspect = forceInspect;
    }

    public String getForceInspect()
    {
        return forceInspect;
    }

    public void setInspectionPlanId(Long inspectionPlanId)
    {
        this.inspectionPlanId = inspectionPlanId;
    }

    public Long getInspectionPlanId()
    {
        return inspectionPlanId;
    }

    public void setStandardHours(BigDecimal standardHours)
    {
        this.standardHours = standardHours;
    }

    public BigDecimal getStandardHours()
    {
        return standardHours;
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
            .append("routeId", getRouteId())
            .append("operationCode", getOperationCode())
            .append("operationName", getOperationName())
            .append("sequenceNo", getSequenceNo())
            .append("workCenterId", getWorkCenterId())
            .append("equipmentType", getEquipmentType())
            .append("skillRequirement", getSkillRequirement())
            .append("forceInspect", getForceInspect())
            .append("inspectionPlanId", getInspectionPlanId())
            .append("standardHours", getStandardHours())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
