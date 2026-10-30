package com.ruoyi.mes.domain;

import com.ruoyi.common.annotation.Excel;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系统集成接口对象 mes_interface
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public class MesInterface extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 接口ID */
    @Excel(name = "接口ID")
    private Long interfaceId;

    /** 接口编码 */
    @Excel(name = "接口编码")
    private String interfaceCode;

    /** 接口名称 */
    @Excel(name = "接口名称")
    private String interfaceName;

    /** 目标系统 */
    @Excel(name = "目标系统")
    private String targetSystem;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 最近同步 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最近同步", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastSync;

    /** 成功率 */
    @Excel(name = "成功率")
    private BigDecimal successRate;

    public void setInterfaceId(Long interfaceId)
    {
        this.interfaceId = interfaceId;
    }

    public Long getInterfaceId()
    {
        return interfaceId;
    }

    public void setInterfaceCode(String interfaceCode)
    {
        this.interfaceCode = interfaceCode;
    }

    public String getInterfaceCode()
    {
        return interfaceCode;
    }

    public void setInterfaceName(String interfaceName)
    {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceName()
    {
        return interfaceName;
    }

    public void setTargetSystem(String targetSystem)
    {
        this.targetSystem = targetSystem;
    }

    public String getTargetSystem()
    {
        return targetSystem;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setLastSync(Date lastSync)
    {
        this.lastSync = lastSync;
    }

    public Date getLastSync()
    {
        return lastSync;
    }

    public void setSuccessRate(BigDecimal successRate)
    {
        this.successRate = successRate;
    }

    public BigDecimal getSuccessRate()
    {
        return successRate;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("interfaceId", getInterfaceId())
            .append("interfaceCode", getInterfaceCode())
            .append("interfaceName", getInterfaceName())
            .append("targetSystem", getTargetSystem())
            .append("status", getStatus())
            .append("lastSync", getLastSync())
            .append("successRate", getSuccessRate())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
