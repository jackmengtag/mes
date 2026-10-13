package com.ruoyi.mes.qc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 返工返修对象 qc_rework
 *
 * @author ruoyi
 */
public class QcRework extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 返工单号 */
    @Excel(name = "返工单号")
    private String reworkNo;

    /** 不合格记录ID */
    @Excel(name = "不合格记录ID", cellType = ColumnType.NUMERIC)
    private Long nonconformityId;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 产品序列号 */
    @Excel(name = "产品序列号")
    private String serialNo;

    /** 返工工序 */
    @Excel(name = "返工工序")
    private String operationCode;

    /** 返工数量 */
    @Excel(name = "返工数量", cellType = ColumnType.NUMERIC)
    private BigDecimal reworkQty;

    /** 复检结果 */
    @Excel(name = "复检结果")
    private String reworkResult;

    /** 执行人 */
    @Excel(name = "执行人")
    private String executorNo;

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

    public void setReworkNo(String reworkNo)
    {
        this.reworkNo = reworkNo;
    }

    public String getReworkNo()
    {
        return reworkNo;
    }

    public void setNonconformityId(Long nonconformityId)
    {
        this.nonconformityId = nonconformityId;
    }

    public Long getNonconformityId()
    {
        return nonconformityId;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setReworkQty(BigDecimal reworkQty)
    {
        this.reworkQty = reworkQty;
    }

    public BigDecimal getReworkQty()
    {
        return reworkQty;
    }

    public void setReworkResult(String reworkResult)
    {
        this.reworkResult = reworkResult;
    }

    public String getReworkResult()
    {
        return reworkResult;
    }

    public void setExecutorNo(String executorNo)
    {
        this.executorNo = executorNo;
    }

    public String getExecutorNo()
    {
        return executorNo;
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
            .append("reworkNo", getReworkNo())
            .append("nonconformityId", getNonconformityId())
            .append("productionOrderId", getProductionOrderId())
            .append("serialNo", getSerialNo())
            .append("operationCode", getOperationCode())
            .append("reworkQty", getReworkQty())
            .append("reworkResult", getReworkResult())
            .append("executorNo", getExecutorNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
