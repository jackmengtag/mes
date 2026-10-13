package com.ruoyi.mes.eq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OEE对象 eq_oee
 *
 * @author ruoyi
 */
public class EqOee extends BaseEntity
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

    /** 统计日期 */
    @Excel(name = "统计日期", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date statDate;

    /** 开动率 */
    @Excel(name = "开动率", cellType = ColumnType.NUMERIC)
    private BigDecimal availability;

    /** 性能效率 */
    @Excel(name = "性能效率", cellType = ColumnType.NUMERIC)
    private BigDecimal performance;

    /** 良品率 */
    @Excel(name = "良品率", cellType = ColumnType.NUMERIC)
    private BigDecimal quality;

    /** OEE */
    @Excel(name = "OEE", cellType = ColumnType.NUMERIC)
    private BigDecimal oee;

    /** 运行时长(分) */
    @Excel(name = "运行时长(分)", cellType = ColumnType.NUMERIC)
    private BigDecimal runMinutes;

    /** 停机时长(分) */
    @Excel(name = "停机时长(分)", cellType = ColumnType.NUMERIC)
    private BigDecimal stopMinutes;

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

    public void setStatDate(Date statDate)
    {
        this.statDate = statDate;
    }

    public Date getStatDate()
    {
        return statDate;
    }

    public void setAvailability(BigDecimal availability)
    {
        this.availability = availability;
    }

    public BigDecimal getAvailability()
    {
        return availability;
    }

    public void setPerformance(BigDecimal performance)
    {
        this.performance = performance;
    }

    public BigDecimal getPerformance()
    {
        return performance;
    }

    public void setQuality(BigDecimal quality)
    {
        this.quality = quality;
    }

    public BigDecimal getQuality()
    {
        return quality;
    }

    public void setOee(BigDecimal oee)
    {
        this.oee = oee;
    }

    public BigDecimal getOee()
    {
        return oee;
    }

    public void setRunMinutes(BigDecimal runMinutes)
    {
        this.runMinutes = runMinutes;
    }

    public BigDecimal getRunMinutes()
    {
        return runMinutes;
    }

    public void setStopMinutes(BigDecimal stopMinutes)
    {
        this.stopMinutes = stopMinutes;
    }

    public BigDecimal getStopMinutes()
    {
        return stopMinutes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentId", getEquipmentId())
            .append("equipmentCode", getEquipmentCode())
            .append("statDate", getStatDate())
            .append("availability", getAvailability())
            .append("performance", getPerformance())
            .append("quality", getQuality())
            .append("oee", getOee())
            .append("runMinutes", getRunMinutes())
            .append("stopMinutes", getStopMinutes())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
