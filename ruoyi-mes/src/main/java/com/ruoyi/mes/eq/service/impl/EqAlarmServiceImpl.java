package com.ruoyi.mes.eq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.eq.mapper.EqAlarmMapper;
import com.ruoyi.mes.eq.domain.EqAlarm;
import com.ruoyi.mes.eq.service.IEqAlarmService;

/**
 * 设备报警Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class EqAlarmServiceImpl implements IEqAlarmService
{
    @Autowired
    private EqAlarmMapper eqAlarmMapper;

    /**
     * 查询设备报警
     */
    @Override
    public EqAlarm selectEqAlarmById(Long id)
    {
        return eqAlarmMapper.selectEqAlarmById(id);
    }

    /**
     * 查询设备报警列表
     */
    @Override
    public List<EqAlarm> selectEqAlarmList(EqAlarm eqAlarm)
    {
        return eqAlarmMapper.selectEqAlarmList(eqAlarm);
    }

    /**
     * 新增设备报警
     */
    @Override
    public int insertEqAlarm(EqAlarm eqAlarm)
    {
        return eqAlarmMapper.insertEqAlarm(eqAlarm);
    }

    /**
     * 修改设备报警
     */
    @Override
    public int updateEqAlarm(EqAlarm eqAlarm)
    {
        return eqAlarmMapper.updateEqAlarm(eqAlarm);
    }

    /**
     * 批量删除设备报警
     */
    @Override
    public int deleteEqAlarmByIds(Long[] ids)
    {
        return eqAlarmMapper.deleteEqAlarmByIds(ids);
    }

    /**
     * 删除设备报警信息
     */
    @Override
    public int deleteEqAlarmById(Long id)
    {
        return eqAlarmMapper.deleteEqAlarmById(id);
    }
}
