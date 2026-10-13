package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工位对象 md_station
 *
 * @author ruoyi
 */
public class MdStation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 工位编码 */
    @Excel(name = "工位编码")
    private String stationCode;

    /** 工位名称 */
    @Excel(name = "工位名称")
    private String stationName;

    /** 所属产线 */
    @Excel(name = "所属产线", cellType = ColumnType.NUMERIC)
    private Long lineId;

    /** 所属工作中心 */
    @Excel(name = "所属工作中心", cellType = ColumnType.NUMERIC)
    private Long workCenterId;

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

    public void setStationCode(String stationCode)
    {
        this.stationCode = stationCode;
    }

    public String getStationCode()
    {
        return stationCode;
    }

    public void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public String getStationName()
    {
        return stationName;
    }

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setWorkCenterId(Long workCenterId)
    {
        this.workCenterId = workCenterId;
    }

    public Long getWorkCenterId()
    {
        return workCenterId;
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
            .append("stationCode", getStationCode())
            .append("stationName", getStationName())
            .append("lineId", getLineId())
            .append("workCenterId", getWorkCenterId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
