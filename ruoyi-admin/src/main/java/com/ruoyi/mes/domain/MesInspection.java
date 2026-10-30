package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 质量检验对象 mes_inspection
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesInspection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检验ID */
    @Excel(name = "检验ID")
    private Long inspectionId;

    /** 检验单号 */
    @Excel(name = "检验单号")
    private String inspectionCode;

    /** 检验类型 */
    @Excel(name = "检验类型")
    private String inspectionType;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderCode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 抽样数 */
    @Excel(name = "抽样数")
    private BigDecimal sampleQty;

    /** 不良数 */
    @Excel(name = "不良数")
    private BigDecimal ngQty;

    /** 检验结果 */
    @Excel(name = "检验结果")
    private String result;

    /** 检验员 */
    @Excel(name = "检验员")
    private String inspector;

    /** 检验时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "检验时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date inspectTime;

    public void setInspectionId(Long inspectionId)
    {
        this.inspectionId = inspectionId;
    }

    public Long getInspectionId()
    {
        return inspectionId;
    }

    public void setInspectionCode(String inspectionCode)
    {
        this.inspectionCode = inspectionCode;
    }

    public String getInspectionCode()
    {
        return inspectionCode;
    }

    public void setInspectionType(String inspectionType)
    {
        this.inspectionType = inspectionType;
    }

    public String getInspectionType()
    {
        return inspectionType;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setSampleQty(BigDecimal sampleQty)
    {
        this.sampleQty = sampleQty;
    }

    public BigDecimal getSampleQty()
    {
        return sampleQty;
    }

    public void setNgQty(BigDecimal ngQty)
    {
        this.ngQty = ngQty;
    }

    public BigDecimal getNgQty()
    {
        return ngQty;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    public void setInspector(String inspector)
    {
        this.inspector = inspector;
    }

    public String getInspector()
    {
        return inspector;
    }

    public void setInspectTime(Date inspectTime)
    {
        this.inspectTime = inspectTime;
    }

    public Date getInspectTime()
    {
        return inspectTime;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("inspectionId", getInspectionId())
            .append("inspectionCode", getInspectionCode())
            .append("inspectionType", getInspectionType())
            .append("orderCode", getOrderCode())
            .append("productCode", getProductCode())
            .append("batchNo", getBatchNo())
            .append("sampleQty", getSampleQty())
            .append("ngQty", getNgQty())
            .append("result", getResult())
            .append("inspector", getInspector())
            .append("inspectTime", getInspectTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
