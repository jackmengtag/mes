package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesEquipmentMapper;
import com.ruoyi.mes.domain.MesEquipment;
import com.ruoyi.mes.service.IMesEquipmentService;

/**
 * 设备台账Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesEquipmentServiceImpl implements IMesEquipmentService
{
    @Autowired
    private MesEquipmentMapper mesEquipmentMapper;

    @Override
    public MesEquipment selectMesEquipmentById(Long equipmentId)
    {
        return mesEquipmentMapper.selectMesEquipmentById(equipmentId);
    }

    @Override
    public List<MesEquipment> selectMesEquipmentList(MesEquipment mesEquipment)
    {
        return mesEquipmentMapper.selectMesEquipmentList(mesEquipment);
    }

    @Override
    public int insertMesEquipment(MesEquipment mesEquipment)
    {
        return mesEquipmentMapper.insertMesEquipment(mesEquipment);
    }

    @Override
    public int updateMesEquipment(MesEquipment mesEquipment)
    {
        return mesEquipmentMapper.updateMesEquipment(mesEquipment);
    }

    @Override
    public int deleteMesEquipmentById(Long equipmentId)
    {
        return mesEquipmentMapper.deleteMesEquipmentById(equipmentId);
    }

    @Override
    public int deleteMesEquipmentByIds(Long[] equipmentIds)
    {
        return mesEquipmentMapper.deleteMesEquipmentByIds(equipmentIds);
    }
}
