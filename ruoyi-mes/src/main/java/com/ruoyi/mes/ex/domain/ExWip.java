package com.ruoyi.mes.ex.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 在制品对象 ex_wip
 *
 * @author ruoyi
 */
public class ExWip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 在制数量 */
    @Excel(name = "在制数量", cellType = ColumnType.NUMERIC)
    private BigDecimal wipQty;

    /** 停留时长(分) */
    @Excel(name = "停留时长(分)", cellType = ColumnType.NUMERIC)
    private BigDecimal stayMinutes;

    /** 统计时间 */
    @Excel(name = "统计时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date statTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
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

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setWipQty(BigDecimal wipQty)
    {
        this.wipQty = wipQty;
    }

    public BigDecimal getWipQty()
    {
        return wipQty;
    }

    public void setStayMinutes(BigDecimal stayMinutes)
    {
        this.stayMinutes = stayMinutes;
    }

    public BigDecimal getStayMinutes()
    {
        return stayMinutes;
    }

    public void setStatTime(Date statTime)
    {
        this.statTime = statTime;
    }

    public Date getStatTime()
    {
        return statTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("operationCode", getOperationCode())
            .append("wipQty", getWipQty())
            .append("stayMinutes", getStayMinutes())
            .append("statTime", getStatTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
