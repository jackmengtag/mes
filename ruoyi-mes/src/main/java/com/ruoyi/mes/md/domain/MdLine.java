package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产线对象 md_line
 *
 * @author ruoyi
 */
public class MdLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 产线编码 */
    @Excel(name = "产线编码")
    private String lineCode;

    /** 产线名称 */
    @Excel(name = "产线名称")
    private String lineName;

    /** 工厂编码 */
    @Excel(name = "工厂编码")
    private String plantCode;

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

    public void setLineCode(String lineCode)
    {
        this.lineCode = lineCode;
    }

    public String getLineCode()
    {
        return lineCode;
    }

    public void setLineName(String lineName)
    {
        this.lineName = lineName;
    }

    public String getLineName()
    {
        return lineName;
    }

    public void setPlantCode(String plantCode)
    {
        this.plantCode = plantCode;
    }

    public String getPlantCode()
    {
        return plantCode;
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
            .append("lineCode", getLineCode())
            .append("lineName", getLineName())
            .append("plantCode", getPlantCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
