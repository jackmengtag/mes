package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesEquipment;

/**
 * 设备台账Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesEquipmentMapper
{
    public MesEquipment selectMesEquipmentById(Long equipmentId);

    public List<MesEquipment> selectMesEquipmentList(MesEquipment mesEquipment);

    public int insertMesEquipment(MesEquipment mesEquipment);

    public int updateMesEquipment(MesEquipment mesEquipment);

    public int deleteMesEquipmentById(Long equipmentId);

    public int deleteMesEquipmentByIds(Long[] equipmentIds);
}
