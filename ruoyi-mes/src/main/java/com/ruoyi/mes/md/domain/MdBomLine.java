package com.ruoyi.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * BOM明细对象 md_bom_line
 *
 * @author ruoyi
 */
public class MdBomLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** BOM ID */
    @Excel(name = "BOM ID", cellType = ColumnType.NUMERIC)
    private Long bomId;

    /** 物料ID */
    @Excel(name = "物料ID", cellType = ColumnType.NUMERIC)
    private Long materialId;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String materialCode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 用量 */
    @Excel(name = "用量", cellType = ColumnType.NUMERIC)
    private BigDecimal quantity;

    /** 损耗率 */
    @Excel(name = "损耗率", cellType = ColumnType.NUMERIC)
    private BigDecimal lossRate;

    /** 投料工序 */
    @Excel(name = "投料工序")
    private String feedingOperationCode;

    /** 是否关键物料 */
    @Excel(name = "是否关键物料")
    private String keyFlag;

    /** 批次绑定要求 */
    @Excel(name = "批次绑定要求")
    private String batchBindFlag;

    /** 是否允许替代 */
    @Excel(name = "是否允许替代")
    private String substituteFlag;

    /** 替代料编码 */
    @Excel(name = "替代料编码")
    private String substituteMaterialCode;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setBomId(Long bomId)
    {
        this.bomId = bomId;
    }

    public Long getBomId()
    {
        return bomId;
    }

    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
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

    public void setQuantity(BigDecimal quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity()
    {
        return quantity;
    }

    public void setLossRate(BigDecimal lossRate)
    {
        this.lossRate = lossRate;
    }

    public BigDecimal getLossRate()
    {
        return lossRate;
    }

    public void setFeedingOperationCode(String feedingOperationCode)
    {
        this.feedingOperationCode = feedingOperationCode;
    }

    public String getFeedingOperationCode()
    {
        return feedingOperationCode;
    }

    public void setKeyFlag(String keyFlag)
    {
        this.keyFlag = keyFlag;
    }

    public String getKeyFlag()
    {
        return keyFlag;
    }

    public void setBatchBindFlag(String batchBindFlag)
    {
        this.batchBindFlag = batchBindFlag;
    }

    public String getBatchBindFlag()
    {
        return batchBindFlag;
    }

    public void setSubstituteFlag(String substituteFlag)
    {
        this.substituteFlag = substituteFlag;
    }

    public String getSubstituteFlag()
    {
        return substituteFlag;
    }

    public void setSubstituteMaterialCode(String substituteMaterialCode)
    {
        this.substituteMaterialCode = substituteMaterialCode;
    }

    public String getSubstituteMaterialCode()
    {
        return substituteMaterialCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bomId", getBomId())
            .append("materialId", getMaterialId())
            .append("materialCode", getMaterialCode())
            .append("materialName", getMaterialName())
            .append("quantity", getQuantity())
            .append("lossRate", getLossRate())
            .append("feedingOperationCode", getFeedingOperationCode())
            .append("keyFlag", getKeyFlag())
            .append("batchBindFlag", getBatchBindFlag())
            .append("substituteFlag", getSubstituteFlag())
            .append("substituteMaterialCode", getSubstituteMaterialCode())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
