package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产报工对象 mes_report
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报工ID */
    @Excel(name = "报工ID")
    private Long reportId;

    /** 报工单号 */
    @Excel(name = "报工单号")
    private String reportCode;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderCode;

    /** 工位 */
    @Excel(name = "工位")
    private String station;

    /** 报工人 */
    @Excel(name = "报工人")
    private String operator;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private BigDecimal qtyOk;

    /** 不良数量 */
    @Excel(name = "不良数量")
    private BigDecimal qtyNg;

    /** 班次 */
    @Excel(name = "班次")
    private String shift;

    /** 报工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "报工时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reportTime;

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getReportId()
    {
        return reportId;
    }

    public void setReportCode(String reportCode)
    {
        this.reportCode = reportCode;
    }

    public String getReportCode()
    {
        return reportCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setStation(String station)
    {
        this.station = station;
    }

    public String getStation()
    {
        return station;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setQtyOk(BigDecimal qtyOk)
    {
        this.qtyOk = qtyOk;
    }

    public BigDecimal getQtyOk()
    {
        return qtyOk;
    }

    public void setQtyNg(BigDecimal qtyNg)
    {
        this.qtyNg = qtyNg;
    }

    public BigDecimal getQtyNg()
    {
        return qtyNg;
    }

    public void setShift(String shift)
    {
        this.shift = shift;
    }

    public String getShift()
    {
        return shift;
    }

    public void setReportTime(Date reportTime)
    {
        this.reportTime = reportTime;
    }

    public Date getReportTime()
    {
        return reportTime;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId", getReportId())
            .append("reportCode", getReportCode())
            .append("orderCode", getOrderCode())
            .append("station", getStation())
            .append("operator", getOperator())
            .append("qtyOk", getQtyOk())
            .append("qtyNg", getQtyNg())
            .append("shift", getShift())
            .append("reportTime", getReportTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
