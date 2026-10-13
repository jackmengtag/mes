package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工对象 md_employee
 *
 * @author ruoyi
 */
public class MdEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 员工工号 */
    @Excel(name = "员工工号")
    private String empNo;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String empName;

    /** 岗位 */
    @Excel(name = "岗位")
    private String postName;

    /** 技能 */
    @Excel(name = "技能")
    private String skills;

    /** 所属班组 */
    @Excel(name = "所属班组", cellType = ColumnType.NUMERIC)
    private Long teamId;

    /** 所属班次 */
    @Excel(name = "所属班次", cellType = ColumnType.NUMERIC)
    private Long shiftId;

    /** 关联系统用户 */
    @Excel(name = "关联系统用户", cellType = ColumnType.NUMERIC)
    private Long userId;

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

    public void setEmpNo(String empNo)
    {
        this.empNo = empNo;
    }

    public String getEmpNo()
    {
        return empNo;
    }

    public void setEmpName(String empName)
    {
        this.empName = empName;
    }

    public String getEmpName()
    {
        return empName;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    public String getPostName()
    {
        return postName;
    }

    public void setSkills(String skills)
    {
        this.skills = skills;
    }

    public String getSkills()
    {
        return skills;
    }

    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }

    public void setShiftId(Long shiftId)
    {
        this.shiftId = shiftId;
    }

    public Long getShiftId()
    {
        return shiftId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
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
            .append("empNo", getEmpNo())
            .append("empName", getEmpName())
            .append("postName", getPostName())
            .append("skills", getSkills())
            .append("teamId", getTeamId())
            .append("shiftId", getShiftId())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
