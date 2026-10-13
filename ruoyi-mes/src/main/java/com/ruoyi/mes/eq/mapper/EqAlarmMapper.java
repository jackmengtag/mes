package com.ruoyi.mes.eq.mapper;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqAlarm;

/**
 * 设备报警Mapper接口
 *
 * @author ruoyi
 */
public interface EqAlarmMapper
{
    /**
     * 查询设备报警
     */
    public EqAlarm selectEqAlarmById(Long id);

    /**
     * 查询设备报警列表
     */
    public List<EqAlarm> selectEqAlarmList(EqAlarm eqAlarm);

    /**
     * 新增设备报警
     */
    public int insertEqAlarm(EqAlarm eqAlarm);

    /**
     * 修改设备报警
     */
    public int updateEqAlarm(EqAlarm eqAlarm);

    /**
     * 删除设备报警
     */
    public int deleteEqAlarmById(Long id);

    /**
     * 批量删除设备报警
     */
    public int deleteEqAlarmByIds(Long[] ids);
}
