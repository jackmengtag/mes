package com.ruoyi.mes.eq.service;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqMaintenance;

/**
 * 设备维护与停机Service接口
 *
 * @author ruoyi
 */
public interface IEqMaintenanceService
{
    /**
     * 查询设备维护与停机
     */
    public EqMaintenance selectEqMaintenanceById(Long id);

    /**
     * 查询设备维护与停机列表
     */
    public List<EqMaintenance> selectEqMaintenanceList(EqMaintenance eqMaintenance);

    /**
     * 新增设备维护与停机
     */
    public int insertEqMaintenance(EqMaintenance eqMaintenance);

    /**
     * 修改设备维护与停机
     */
    public int updateEqMaintenance(EqMaintenance eqMaintenance);

    /**
     * 批量删除设备维护与停机
     */
    public int deleteEqMaintenanceByIds(Long[] ids);

    /**
     * 删除设备维护与停机信息
     */
    public int deleteEqMaintenanceById(Long id);
}
