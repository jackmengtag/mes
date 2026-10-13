package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 缺陷代码对象 md_defect_code
 *
 * @author ruoyi
 */
public class MdDefectCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 缺陷代码 */
    @Excel(name = "缺陷代码")
    private String defectCode;

    /** 缺陷名称 */
    @Excel(name = "缺陷名称")
    private String defectName;

    /** 缺陷类别 */
    @Excel(name = "缺陷类别")
    private String defectCategory;

    /** 责任部门 */
    @Excel(name = "责任部门")
    private String respDept;

    /** 默认处理方式 */
    @Excel(name = "默认处理方式")
    private String defaultDisposition;

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

    public void setDefectCode(String defectCode)
    {
        this.defectCode = defectCode;
    }

    public String getDefectCode()
    {
        return defectCode;
    }

    public void setDefectName(String defectName)
    {
        this.defectName = defectName;
    }

    public String getDefectName()
    {
        return defectName;
    }

    public void setDefectCategory(String defectCategory)
    {
        this.defectCategory = defectCategory;
    }

    public String getDefectCategory()
    {
        return defectCategory;
    }

    public void setRespDept(String respDept)
    {
        this.respDept = respDept;
    }

    public String getRespDept()
    {
        return respDept;
    }

    public void setDefaultDisposition(String defaultDisposition)
    {
        this.defaultDisposition = defaultDisposition;
    }

    public String getDefaultDisposition()
    {
        return defaultDisposition;
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
            .append("defectCode", getDefectCode())
            .append("defectName", getDefectName())
            .append("defectCategory", getDefectCategory())
            .append("respDept", getRespDept())
            .append("defaultDisposition", getDefaultDisposition())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
