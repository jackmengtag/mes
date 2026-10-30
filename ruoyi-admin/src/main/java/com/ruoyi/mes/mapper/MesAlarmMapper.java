package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesAlarm;

/**
 * 设备报警Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesAlarmMapper
{
    public MesAlarm selectMesAlarmById(Long alarmId);

    public List<MesAlarm> selectMesAlarmList(MesAlarm mesAlarm);

    public int insertMesAlarm(MesAlarm mesAlarm);

    public int updateMesAlarm(MesAlarm mesAlarm);

    public int deleteMesAlarmById(Long alarmId);

    public int deleteMesAlarmByIds(Long[] alarmIds);
}
