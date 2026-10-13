package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料主数据对象 md_material
 *
 * @author ruoyi
 */
public class MdMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String unit;

    /** 物料类型 */
    @Excel(name = "物料类型")
    private String materialType;

    /** 批次管理标识 */
    @Excel(name = "批次管理标识")
    private String batchFlag;

    /** 序列号管理标识 */
    @Excel(name = "序列号管理标识")
    private String serialFlag;

    /** 保质期(天) */
    @Excel(name = "保质期(天)", cellType = ColumnType.NUMERIC)
    private Integer shelfLife;

    /** 是否关键物料 */
    @Excel(name = "是否关键物料")
    private String keyFlag;

    /** 是否需要检验 */
    @Excel(name = "是否需要检验")
    private String inspectFlag;

    /** 是否允许替代 */
    @Excel(name = "是否允许替代")
    private String substituteFlag;

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

    public void setMaterialCode(String materialCode)
    {
        this.materialCode = materialCode;
    }

    public String getMaterialCode()
    {
        return materialCode;
    }

    public void setMaterialName(String materialName)
    {
        this.materialName = materialName;
    }

    public String getMaterialName()
    {
        return materialName;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setMaterialType(String materialType)
    {
        this.materialType = materialType;
    }

    public String getMaterialType()
    {
        return materialType;
    }

    public void setBatchFlag(String batchFlag)
    {
        this.batchFlag = batchFlag;
    }

    public String getBatchFlag()
    {
        return batchFlag;
    }

    public void setSerialFlag(String serialFlag)
    {
        this.serialFlag = serialFlag;
    }

    public String getSerialFlag()
    {
        return serialFlag;
    }

    public void setShelfLife(Integer shelfLife)
    {
        this.shelfLife = shelfLife;
    }

    public Integer getShelfLife()
    {
        return shelfLife;
    }

    public void setKeyFlag(String keyFlag)
    {
        this.keyFlag = keyFlag;
    }

    public String getKeyFlag()
    {
        return keyFlag;
    }

    public void setInspectFlag(String inspectFlag)
    {
        this.inspectFlag = inspectFlag;
    }

    public String getInspectFlag()
    {
        return inspectFlag;
    }

    public void setSubstituteFlag(String substituteFlag)
    {
        this.substituteFlag = substituteFlag;
    }

    public String getSubstituteFlag()
    {
        return substituteFlag;
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
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("unit", getUnit())
            .append("materialType", getMaterialType())
            .append("batchFlag", getBatchFlag())
            .append("serialFlag", getSerialFlag())
            .append("shelfLife", getShelfLife())
            .append("keyFlag", getKeyFlag())
            .append("inspectFlag", getInspectFlag())
            .append("substituteFlag", getSubstituteFlag())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
