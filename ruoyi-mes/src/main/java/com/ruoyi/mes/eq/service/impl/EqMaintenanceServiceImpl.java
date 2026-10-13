package com.ruoyi.mes.eq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.eq.mapper.EqMaintenanceMapper;
import com.ruoyi.mes.eq.domain.EqMaintenance;
import com.ruoyi.mes.eq.service.IEqMaintenanceService;

/**
 * 设备维护与停机Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class EqMaintenanceServiceImpl implements IEqMaintenanceService
{
    @Autowired
    private EqMaintenanceMapper eqMaintenanceMapper;

    /**
     * 查询设备维护与停机
     */
    @Override
    public EqMaintenance selectEqMaintenanceById(Long id)
    {
        return eqMaintenanceMapper.selectEqMaintenanceById(id);
    }

    /**
     * 查询设备维护与停机列表
     */
    @Override
    public List<EqMaintenance> selectEqMaintenanceList(EqMaintenance eqMaintenance)
    {
        return eqMaintenanceMapper.selectEqMaintenanceList(eqMaintenance);
    }

    /**
     * 新增设备维护与停机
     */
    @Override
    public int insertEqMaintenance(EqMaintenance eqMaintenance)
    {
        return eqMaintenanceMapper.insertEqMaintenance(eqMaintenance);
    }

    /**
     * 修改设备维护与停机
     */
    @Override
    public int updateEqMaintenance(EqMaintenance eqMaintenance)
    {
        return eqMaintenanceMapper.updateEqMaintenance(eqMaintenance);
    }

    /**
     * 批量删除设备维护与停机
     */
    @Override
    public int deleteEqMaintenanceByIds(Long[] ids)
    {
        return eqMaintenanceMapper.deleteEqMaintenanceByIds(ids);
    }

    /**
     * 删除设备维护与停机信息
     */
    @Override
    public int deleteEqMaintenanceById(Long id)
    {
        return eqMaintenanceMapper.deleteEqMaintenanceById(id);
    }
}
