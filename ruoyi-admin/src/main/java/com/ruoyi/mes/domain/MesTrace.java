package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 质量追溯对象 mes_trace
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesTrace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 追溯ID */
    @Excel(name = "追溯ID")
    private Long traceId;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNo;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 工单号 */
    @Excel(name = "工单号")
    private String orderCode;

    /** 工位 */
    @Excel(name = "工位")
    private String station;

    /** 关键件 */
    @Excel(name = "关键件")
    private String keyMaterial;

    /** 供应商批次 */
    @Excel(name = "供应商批次")
    private String supplierBatch;

    /** 检验结果 */
    @Excel(name = "检验结果")
    private String inspectionResult;

    /** 生产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "生产时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date producedTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setTraceId(Long traceId)
    {
        this.traceId = traceId;
    }

    public Long getTraceId()
    {
        return traceId;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo()
    {
        return batchNo;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
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

    public void setKeyMaterial(String keyMaterial)
    {
        this.keyMaterial = keyMaterial;
    }

    public String getKeyMaterial()
    {
        return keyMaterial;
    }

    public void setSupplierBatch(String supplierBatch)
    {
        this.supplierBatch = supplierBatch;
    }

    public String getSupplierBatch()
    {
        return supplierBatch;
    }

    public void setInspectionResult(String inspectionResult)
    {
        this.inspectionResult = inspectionResult;
    }

    public String getInspectionResult()
    {
        return inspectionResult;
    }

    public void setProducedTime(Date producedTime)
    {
        this.producedTime = producedTime;
    }

    public Date getProducedTime()
    {
        return producedTime;
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
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("traceId", getTraceId())
            .append("serialNo", getSerialNo())
            .append("batchNo", getBatchNo())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("orderCode", getOrderCode())
            .append("station", getStation())
            .append("keyMaterial", getKeyMaterial())
            .append("supplierBatch", getSupplierBatch())
            .append("inspectionResult", getInspectionResult())
            .append("producedTime", getProducedTime())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
