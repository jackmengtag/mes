package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班组对象 md_team
 *
 * @author ruoyi
 */
public class MdTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 班组编码 */
    @Excel(name = "班组编码")
    private String teamCode;

    /** 班组名称 */
    @Excel(name = "班组名称")
    private String teamName;

    /** 班组长工号 */
    @Excel(name = "班组长工号")
    private String leaderNo;

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

    public void setTeamCode(String teamCode)
    {
        this.teamCode = teamCode;
    }

    public String getTeamCode()
    {
        return teamCode;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setLeaderNo(String leaderNo)
    {
        this.leaderNo = leaderNo;
    }

    public String getLeaderNo()
    {
        return leaderNo;
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
            .append("teamCode", getTeamCode())
            .append("teamName", getTeamName())
            .append("leaderNo", getLeaderNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
