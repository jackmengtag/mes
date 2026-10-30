package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesAlarmMapper;
import com.ruoyi.mes.domain.MesAlarm;
import com.ruoyi.mes.service.IMesAlarmService;

/**
 * 设备报警Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesAlarmServiceImpl implements IMesAlarmService
{
    @Autowired
    private MesAlarmMapper mesAlarmMapper;

    @Override
    public MesAlarm selectMesAlarmById(Long alarmId)
    {
        return mesAlarmMapper.selectMesAlarmById(alarmId);
    }

    @Override
    public List<MesAlarm> selectMesAlarmList(MesAlarm mesAlarm)
    {
        return mesAlarmMapper.selectMesAlarmList(mesAlarm);
    }

    @Override
    public int insertMesAlarm(MesAlarm mesAlarm)
    {
        return mesAlarmMapper.insertMesAlarm(mesAlarm);
    }

    @Override
    public int updateMesAlarm(MesAlarm mesAlarm)
    {
        return mesAlarmMapper.updateMesAlarm(mesAlarm);
    }

    @Override
    public int deleteMesAlarmById(Long alarmId)
    {
        return mesAlarmMapper.deleteMesAlarmById(alarmId);
    }

    @Override
    public int deleteMesAlarmByIds(Long[] alarmIds)
    {
        return mesAlarmMapper.deleteMesAlarmByIds(alarmIds);
    }
}
