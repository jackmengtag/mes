package com.ruoyi.mes.wo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 生产工单对象 wo_production_order
 *
 * @author ruoyi
 */
public class WoProductionOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 来源订单号 */
    @Excel(name = "来源订单号")
    private String sourceOrderNo;

    /** ERP工单号 */
    @Excel(name = "ERP工单号")
    private String erpWorkOrderNo;

    /** 需求ID */
    @Excel(name = "需求ID", cellType = ColumnType.NUMERIC)
    private Long demandId;

    /** 父工单ID */
    @Excel(name = "父工单ID", cellType = ColumnType.NUMERIC)
    private Long parentOrderId;

    /** 拆分原因 */
    @Excel(name = "拆分原因")
    private String splitReason;

    /** 产品ID */
    @Excel(name = "产品ID", cellType = ColumnType.NUMERIC)
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 固化产品版本 */
    @Excel(name = "固化产品版本", cellType = ColumnType.NUMERIC)
    private Long productVersionId;

    /** 固化工艺版本 */
    @Excel(name = "固化工艺版本", cellType = ColumnType.NUMERIC)
    private Long routeVersionId;

    /** 固化BOM版本 */
    @Excel(name = "固化BOM版本", cellType = ColumnType.NUMERIC)
    private Long bomVersionId;

    /** 固化检验方案版本 */
    @Excel(name = "固化检验方案版本", cellType = ColumnType.NUMERIC)
    private Long inspectionPlanVersionId;

    /** 产线 */
    @Excel(name = "产线", cellType = ColumnType.NUMERIC)
    private Long lineId;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    /** 计划数量 */
    @Excel(name = "计划数量", cellType = ColumnType.NUMERIC)
    private BigDecimal plannedQty;

    /** 完成数量 */
    @Excel(name = "完成数量", cellType = ColumnType.NUMERIC)
    private BigDecimal completedQty;

    /** 合格数量 */
    @Excel(name = "合格数量", cellType = ColumnType.NUMERIC)
    private BigDecimal qualifiedQty;

    /** 报废数量 */
    @Excel(name = "报废数量", cellType = ColumnType.NUMERIC)
    private BigDecimal scrapQty;

    /** 齐套状态 */
    @Excel(name = "齐套状态")
    private String kitStatus;

    /** 计划开始 */
    @Excel(name = "计划开始", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date plannedStartTime;

    /** 计划结束 */
    @Excel(name = "计划结束", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date plannedEndTime;

    /** 实际开始 */
    @Excel(name = "实际开始", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date actualStartTime;

    /** 实际结束 */
    @Excel(name = "实际结束", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date actualEndTime;

    /** 暂停原因 */
    @Excel(name = "暂停原因")
    private String pauseReason;

    /** 工单状态 */
    @Excel(name = "工单状态")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setSourceOrderNo(String sourceOrderNo)
    {
        this.sourceOrderNo = sourceOrderNo;
    }

    public String getSourceOrderNo()
    {
        return sourceOrderNo;
    }

    public void setErpWorkOrderNo(String erpWorkOrderNo)
    {
        this.erpWorkOrderNo = erpWorkOrderNo;
    }

    public String getErpWorkOrderNo()
    {
        return erpWorkOrderNo;
    }

    public void setDemandId(Long demandId)
    {
        this.demandId = demandId;
    }

    public Long getDemandId()
    {
        return demandId;
    }

    public void setParentOrderId(Long parentOrderId)
    {
        this.parentOrderId = parentOrderId;
    }

    public Long getParentOrderId()
    {
        return parentOrderId;
    }

    public void setSplitReason(String splitReason)
    {
        this.splitReason = splitReason;
    }

    public String getSplitReason()
    {
        return splitReason;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductVersionId(Long productVersionId)
    {
        this.productVersionId = productVersionId;
    }

    public Long getProductVersionId()
    {
        return productVersionId;
    }

    public void setRouteVersionId(Long routeVersionId)
    {
        this.routeVersionId = routeVersionId;
    }

    public Long getRouteVersionId()
    {
        return routeVersionId;
    }

    public void setBomVersionId(Long bomVersionId)
    {
        this.bomVersionId = bomVersionId;
    }

    public Long getBomVersionId()
    {
        return bomVersionId;
    }

    public void setInspectionPlanVersionId(Long inspectionPlanVersionId)
    {
        this.inspectionPlanVersionId = inspectionPlanVersionId;
    }

    public Long getInspectionPlanVersionId()
    {
        return inspectionPlanVersionId;
    }

    public void setLineId(Long lineId)
    {
        this.lineId = lineId;
    }

    public Long getLineId()
    {
        return lineId;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPlannedQty(BigDecimal plannedQty)
    {
        this.plannedQty = plannedQty;
    }

    public BigDecimal getPlannedQty()
    {
        return plannedQty;
    }

    public void setCompletedQty(BigDecimal completedQty)
    {
        this.completedQty = completedQty;
    }

    public BigDecimal getCompletedQty()
    {
        return completedQty;
    }

    public void setQualifiedQty(BigDecimal qualifiedQty)
    {
        this.qualifiedQty = qualifiedQty;
    }

    public BigDecimal getQualifiedQty()
    {
        return qualifiedQty;
    }

    public void setScrapQty(BigDecimal scrapQty)
    {
        this.scrapQty = scrapQty;
    }

    public BigDecimal getScrapQty()
    {
        return scrapQty;
    }

    public void setKitStatus(String kitStatus)
    {
        this.kitStatus = kitStatus;
    }

    public String getKitStatus()
    {
        return kitStatus;
    }

    public void setPlannedStartTime(Date plannedStartTime)
    {
        this.plannedStartTime = plannedStartTime;
    }

    public Date getPlannedStartTime()
    {
        return plannedStartTime;
    }

    public void setPlannedEndTime(Date plannedEndTime)
    {
        this.plannedEndTime = plannedEndTime;
    }

    public Date getPlannedEndTime()
    {
        return plannedEndTime;
    }

    public void setActualStartTime(Date actualStartTime)
    {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualStartTime()
    {
        return actualStartTime;
    }

    public void setActualEndTime(Date actualEndTime)
    {
        this.actualEndTime = actualEndTime;
    }

    public Date getActualEndTime()
    {
        return actualEndTime;
    }

    public void setPauseReason(String pauseReason)
    {
        this.pauseReason = pauseReason;
    }

    public String getPauseReason()
    {
        return pauseReason;
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
            .append("orderNo", getOrderNo())
            .append("sourceOrderNo", getSourceOrderNo())
            .append("erpWorkOrderNo", getErpWorkOrderNo())
            .append("demandId", getDemandId())
            .append("parentOrderId", getParentOrderId())
            .append("splitReason", getSplitReason())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productVersionId", getProductVersionId())
            .append("routeVersionId", getRouteVersionId())
            .append("bomVersionId", getBomVersionId())
            .append("inspectionPlanVersionId", getInspectionPlanVersionId())
            .append("lineId", getLineId())
            .append("priority", getPriority())
            .append("plannedQty", getPlannedQty())
            .append("completedQty", getCompletedQty())
            .append("qualifiedQty", getQualifiedQty())
            .append("scrapQty", getScrapQty())
            .append("kitStatus", getKitStatus())
            .append("plannedStartTime", getPlannedStartTime())
            .append("plannedEndTime", getPlannedEndTime())
            .append("actualStartTime", getActualStartTime())
            .append("actualEndTime", getActualEndTime())
            .append("pauseReason", getPauseReason())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
