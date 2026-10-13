package com.ruoyi.mes.eq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 设备点检对象 eq_spot_check
 *
 * @author ruoyi
 */
public class EqSpotCheck extends BaseEntity
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

    /** 计划时间 */
    @Excel(name = "计划时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date planTime;

    /** 点检结果 */
    @Excel(name = "点检结果")
    private String checkResult;

    /** 点检人 */
    @Excel(name = "点检人")
    private String checkerNo;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String problemDesc;

    /** 是否需维修 */
    @Excel(name = "是否需维修")
    private String repairRequired;

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

    public void setPlanTime(Date planTime)
    {
        this.planTime = planTime;
    }

    public Date getPlanTime()
    {
        return planTime;
    }

    public void setCheckResult(String checkResult)
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult()
    {
        return checkResult;
    }

    public void setCheckerNo(String checkerNo)
    {
        this.checkerNo = checkerNo;
    }

    public String getCheckerNo()
    {
        return checkerNo;
    }

    public void setProblemDesc(String problemDesc)
    {
        this.problemDesc = problemDesc;
    }

    public String getProblemDesc()
    {
        return problemDesc;
    }

    public void setRepairRequired(String repairRequired)
    {
        this.repairRequired = repairRequired;
    }

    public String getRepairRequired()
    {
        return repairRequired;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentId", getEquipmentId())
            .append("equipmentCode", getEquipmentCode())
            .append("planTime", getPlanTime())
            .append("checkResult", getCheckResult())
            .append("checkerNo", getCheckerNo())
            .append("problemDesc", getProblemDesc())
            .append("repairRequired", getRepairRequired())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
