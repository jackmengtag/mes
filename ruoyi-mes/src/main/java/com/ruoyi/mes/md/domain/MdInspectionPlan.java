package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检验方案对象 md_inspection_plan
 *
 * @author ruoyi
 */
public class MdInspectionPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 方案编码 */
    @Excel(name = "方案编码")
    private String planCode;

    /** 方案名称 */
    @Excel(name = "方案名称")
    private String planName;

    /** 检验类型 */
    @Excel(name = "检验类型")
    private String inspectionType;

    /** 产品ID */
    @Excel(name = "产品ID", cellType = ColumnType.NUMERIC)
    private Long productId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String operationCode;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionNo;

    /** 版本状态 */
    @Excel(name = "版本状态")
    private String docStatus;

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

    public void setPlanCode(String planCode)
    {
        this.planCode = planCode;
    }

    public String getPlanCode()
    {
        return planCode;
    }

    public void setPlanName(String planName)
    {
        this.planName = planName;
    }

    public String getPlanName()
    {
        return planName;
    }

    public void setInspectionType(String inspectionType)
    {
        this.inspectionType = inspectionType;
    }

    public String getInspectionType()
    {
        return inspectionType;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setOperationCode(String operationCode)
    {
        this.operationCode = operationCode;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public void setVersionNo(String versionNo)
    {
        this.versionNo = versionNo;
    }

    public String getVersionNo()
    {
        return versionNo;
    }

    public void setDocStatus(String docStatus)
    {
        this.docStatus = docStatus;
    }

    public String getDocStatus()
    {
        return docStatus;
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
            .append("planCode", getPlanCode())
            .append("planName", getPlanName())
            .append("inspectionType", getInspectionType())
            .append("productId", getProductId())
            .append("operationCode", getOperationCode())
            .append("versionNo", getVersionNo())
            .append("docStatus", getDocStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
