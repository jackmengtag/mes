package com.ruoyi.mes.qc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 质量检验对象 qc_inspection
 *
 * @author ruoyi
 */
public class QcInspection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 检验单号 */
    @Excel(name = "检验单号")
    private String inspectionNo;

    /** 检验类型 */
    @Excel(name = "检验类型")
    private String inspectionType;

    /** 工单ID */
    @Excel(name = "工单ID", cellType = ColumnType.NUMERIC)
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

    /** 物料ID */
    @Excel(name = "物料ID", cellType = ColumnType.NUMERIC)
    private Long materialId;

    /** 物料批次号 */
    @Excel(name = "物料批次号")
    private String materialBatchNo;

    /** 检验方案 */
    @Excel(name = "检验方案", cellType = ColumnType.NUMERIC)
    private Long inspectionPlanId;

    /** 检验结果 */
    @Excel(name = "检验结果")
    private String result;

    /** 检验员 */
    @Excel(name = "检验员")
    private String inspectorNo;

    /** 检验时间 */
    @Excel(name = "检验时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date inspectedAt;

    /** 是否已放行 */
    @Excel(name = "是否已放行")
    private String releaseFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setInspectionNo(String inspectionNo)
    {
        this.inspectionNo = inspectionNo;
    }

    public String getInspectionNo()
    {
        return inspectionNo;
    }

    public void setInspectionType(String inspectionType)
    {
        this.inspectionType = inspectionType;
    }

    public String getInspectionType()
    {
        return inspectionType;
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

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    public void setMaterialBatchNo(String materialBatchNo)
    {
        this.materialBatchNo = materialBatchNo;
    }

    public String getMaterialBatchNo()
    {
        return materialBatchNo;
    }

    public void setInspectionPlanId(Long inspectionPlanId)
    {
        this.inspectionPlanId = inspectionPlanId;
    }

    public Long getInspectionPlanId()
    {
        return inspectionPlanId;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    public void setInspectorNo(String inspectorNo)
    {
        this.inspectorNo = inspectorNo;
    }

    public String getInspectorNo()
    {
        return inspectorNo;
    }

    public void setInspectedAt(Date inspectedAt)
    {
        this.inspectedAt = inspectedAt;
    }

    public Date getInspectedAt()
    {
        return inspectedAt;
    }

    public void setReleaseFlag(String releaseFlag)
    {
        this.releaseFlag = releaseFlag;
    }

    public String getReleaseFlag()
    {
        return releaseFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("inspectionNo", getInspectionNo())
            .append("inspectionType", getInspectionType())
            .append("productionOrderId", getProductionOrderId())
            .append("orderNo", getOrderNo())
            .append("operationTaskId", getOperationTaskId())
            .append("operationCode", getOperationCode())
            .append("serialNo", getSerialNo())
            .append("materialId", getMaterialId())
            .append("materialBatchNo", getMaterialBatchNo())
            .append("inspectionPlanId", getInspectionPlanId())
            .append("result", getResult())
            .append("inspectorNo", getInspectorNo())
            .append("inspectedAt", getInspectedAt())
            .append("releaseFlag", getReleaseFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
