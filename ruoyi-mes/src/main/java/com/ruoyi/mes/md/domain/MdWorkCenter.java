package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 工作中心对象 md_work_center
 *
 * @author ruoyi
 */
public class MdWorkCenter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 工作中心编码 */
    @Excel(name = "工作中心编码")
    private String workCenterCode;

    /** 工作中心名称 */
    @Excel(name = "工作中心名称")
    private String workCenterName;

    /** 所属产线 */
    @Excel(name = "所属产线", cellType = ColumnType.NUMERIC)
    private Long lineId;

    /** 产能 */
    @Excel(name = "产能", cellType = ColumnType.NUMERIC)
    private BigDecimal capacity;

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

    public void setWorkCenterCode(String workCenterCode)
    {
        this.workCenterCode = workCenterCode;
    }

    public String getWorkCenterCode()
    {
        return workCenterCode;
    }

    public void setWorkCenterName(String workCenterName)
    {
        this.workCenterName = workCenterName;
    }

    public String getWorkCenterName()
    {
        return workCenterName;
    }

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setCapacity(BigDecimal capacity)
    {
        this.capacity = capacity;
    }

    public BigDecimal getCapacity()
    {
        return capacity;
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
            .append("workCenterCode", getWorkCenterCode())
            .append("workCenterName", getWorkCenterName())
            .append("lineId", getLineId())
            .append("capacity", getCapacity())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
