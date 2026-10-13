package com.ruoyi.mes.ex.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 报工记录对象 ex_production_report
 *
 * @author ruoyi
 */
public class ExProductionReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 报工单号 */
    @Excel(name = "报工单号")
    private String reportNo;

    /** 所属工单 */
    @Excel(name = "所属工单", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 工序任务ID */
    @Excel(name = "工序任务ID", cellType = ColumnType.NUMERIC)
    private Long operationTaskId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 产品序列号 */
    @Excel(name = "产品序列号")
    private String serialNo;

    /** 合格数量 */
    @Excel(name = "合格数量", cellType = ColumnType.NUMERIC)
    private BigDecimal qualifiedQty;

    /** 不合格数量 */
    @Excel(name = "不合格数量", cellType = ColumnType.NUMERIC)
    private BigDecimal unqualifiedQty;

    /** 报废数量 */
    @Excel(name = "报废数量", cellType = ColumnType.NUMERIC)
    private BigDecimal scrapQty;

    /** 人工工时(分) */
    @Excel(name = "人工工时(分)", cellType = ColumnType.NUMERIC)
    private BigDecimal laborMinutes;

    /** 机器工时(分) */
    @Excel(name = "机器工时(分)", cellType = ColumnType.NUMERIC)
    private BigDecimal machineMinutes;

    /** 设备ID */
    @Excel(name = "设备ID", cellType = ColumnType.NUMERIC)
    private Long equipmentId;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operatorNo;

    /** 班次 */
    @Excel(name = "班次")
    private String shiftCode;

    /** 报工时间 */
    @Excel(name = "报工时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reportedAt;

    /** 幂等键 */
    @Excel(name = "幂等键")
    private String idempotencyKey;

    /** ERP同步状态 */
    @Excel(name = "ERP同步状态")
    private String syncStatus;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setReportNo(String reportNo)
    {
        this.reportNo = reportNo;
    }

    public String getReportNo()
    {
        return reportNo;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOperationTaskId(Long operationTaskId)
    {
        this.operationTaskId = operationTaskId;
    }

    public Long getOperationTaskId()
    {
        return operationTaskId;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setQualifiedQty(BigDecimal qualifiedQty)
    {
        this.qualifiedQty = qualifiedQty;
    }

    public BigDecimal getQualifiedQty()
    {
        return qualifiedQty;
    }

    public void setUnqualifiedQty(BigDecimal unqualifiedQty)
    {
        this.unqualifiedQty = unqualifiedQty;
    }

    public BigDecimal getUnqualifiedQty()
    {
        return unqualifiedQty;
    }

    public void setScrapQty(BigDecimal scrapQty)
    {
        this.scrapQty = scrapQty;
    }

    public BigDecimal getScrapQty()
    {
        return scrapQty;
    }

    public void setLaborMinutes(BigDecimal laborMinutes)
    {
        this.laborMinutes = laborMinutes;
    }

    public BigDecimal getLaborMinutes()
    {
        return laborMinutes;
    }

    public void setMachineMinutes(BigDecimal machineMinutes)
    {
        this.machineMinutes = machineMinutes;
    }

    public BigDecimal getMachineMinutes()
    {
        return machineMinutes;
    }

    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }

    public void setOperatorNo(String operatorNo)
    {
        this.operatorNo = operatorNo;
    }

    public String getOperatorNo()
    {
        return operatorNo;
    }

    public void setShiftCode(String shiftCode)
    {
        this.shiftCode = shiftCode;
    }

    public String getShiftCode()
    {
        return shiftCode;
    }

    public void setReportedAt(Date reportedAt)
    {
        this.reportedAt = reportedAt;
    }

    public Date getReportedAt()
    {
        return reportedAt;
    }

    public void setIdempotencyKey(String idempotencyKey)
    {
        this.idempotencyKey = idempotencyKey;
    }

    public String getIdempotencyKey()
    {
        return idempotencyKey;
    }

    public void setSyncStatus(String syncStatus)
    {
        this.syncStatus = syncStatus;
    }

    public String getSyncStatus()
    {
        return syncStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("reportNo", getReportNo())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("operationTaskId", getOperationTaskId())
            .append("operationCode", getOperationCode())
            .append("serialNo", getSerialNo())
            .append("qualifiedQty", getQualifiedQty())
            .append("unqualifiedQty", getUnqualifiedQty())
            .append("scrapQty", getScrapQty())
            .append("laborMinutes", getLaborMinutes())
            .append("machineMinutes", getMachineMinutes())
            .append("equipmentId", getEquipmentId())
            .append("operatorNo", getOperatorNo())
            .append("shiftCode", getShiftCode())
            .append("reportedAt", getReportedAt())
            .append("idempotencyKey", getIdempotencyKey())
            .append("syncStatus", getSyncStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
