package com.ruoyi.mes.qc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
String
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 质量放行对象 qc_release
 *
 * @author ruoyi
 */
public class QcRelease extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 放行单号 */
    @Excel(name = "放行单号")
    private String releaseNo;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderNo;

    /** 产品序列号 */
    @Excel(name = "产品序列号")
    private String serialNo;

    /** 放行结果 */
    @Excel(name = "放行结果")
    private String releaseResult;

    /** 放行人 */
    @Excel(name = "放行人")
    private String releaseBy;

    /** 放行时间 */
    @Excel(name = "放行时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    /** 是否特殊放行 */
    @Excel(name = "是否特殊放行")
    private String specialFlag;

    /** 阻塞项 */
    @Excel(name = "阻塞项")
    private String blockReason;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setReleaseNo(String releaseNo)
    {
        this.releaseNo = releaseNo;
    }

    public String getReleaseNo()
    {
        return releaseNo;
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

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setReleaseResult(String releaseResult)
    {
        this.releaseResult = releaseResult;
    }

    public String getReleaseResult()
    {
        return releaseResult;
    }

    public void setReleaseBy(String releaseBy)
    {
        this.releaseBy = releaseBy;
    }

    public String getReleaseBy()
    {
        return releaseBy;
    }

    public void setReleaseTime(Date releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime()
    {
        return releaseTime;
    }

    public void setSpecialFlag(String specialFlag)
    {
        this.specialFlag = specialFlag;
    }

    public String getSpecialFlag()
    {
        return specialFlag;
    }

    public void setBlockReason(String blockReason)
    {
        this.blockReason = blockReason;
    }

    public String getBlockReason()
    {
        return blockReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("releaseNo", getReleaseNo())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("serialNo", getSerialNo())
            .append("releaseResult", getReleaseResult())
            .append("releaseBy", getReleaseBy())
            .append("releaseTime", getReleaseTime())
            .append("specialFlag", getSpecialFlag())
            .append("blockReason", getBlockReason())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
