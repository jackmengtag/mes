package com.ruoyi.mes.ex.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品序列号对象 ex_product_serial
 *
 * @author ruoyi
 */
public class ExProductSerial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNo;

    /** 产品ID */
    @Excel(name = "产品ID", cellType = ColumnType.NUMERIC)
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 所属工单 */
    @Excel(name = "所属工单", cellType = ColumnType.NUMERIC)
    private Long productionOrderId;

    /** 当前工序 */
    @Excel(name = "当前工序")
    private String currentOperation;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String currentStatus;

    /** 质量状态 */
    @Excel(name = "质量状态")
    private String qualityStatus;

    /** 库存状态 */
    @Excel(name = "库存状态")
    private String warehouseStatus;

    /** 报废标识 */
    @Excel(name = "报废标识")
    private String scrapFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductionOrderId(Long productionOrderId)
    {
        this.productionOrderId = productionOrderId;
    }

    public Long getProductionOrderId()
    {
        return productionOrderId;
    }

    public void setCurrentOperation(String currentOperation)
    {
        this.currentOperation = currentOperation;
    }

    public String getCurrentOperation()
    {
        return currentOperation;
    }

    public void setCurrentStatus(String currentStatus)
    {
        this.currentStatus = currentStatus;
    }

    public String getCurrentStatus()
    {
        return currentStatus;
    }

    public void setQualityStatus(String qualityStatus)
    {
        this.qualityStatus = qualityStatus;
    }

    public String getQualityStatus()
    {
        return qualityStatus;
    }

    public void setWarehouseStatus(String warehouseStatus)
    {
        this.warehouseStatus = warehouseStatus;
    }

    public String getWarehouseStatus()
    {
        return warehouseStatus;
    }

    public void setScrapFlag(String scrapFlag)
    {
        this.scrapFlag = scrapFlag;
    }

    public String getScrapFlag()
    {
        return scrapFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("serialNo", getSerialNo())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productionOrderId", getProductionOrderId())
            .append("currentOperation", getCurrentOperation())
            .append("currentStatus", getCurrentStatus())
            .append("qualityStatus", getQualityStatus())
            .append("warehouseStatus", getWarehouseStatus())
            .append("scrapFlag", getScrapFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
