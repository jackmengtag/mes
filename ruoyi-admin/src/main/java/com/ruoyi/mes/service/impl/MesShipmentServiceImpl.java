package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesShipmentMapper;
import com.ruoyi.mes.domain.MesShipment;
import com.ruoyi.mes.service.IMesShipmentService;

/**
 * 销售出库Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesShipmentServiceImpl implements IMesShipmentService
{
    @Autowired
    private MesShipmentMapper mesShipmentMapper;

    @Override
    public MesShipment selectMesShipmentById(Long shipmentId)
    {
        return mesShipmentMapper.selectMesShipmentById(shipmentId);
    }

    @Override
    public List<MesShipment> selectMesShipmentList(MesShipment mesShipment)
    {
        return mesShipmentMapper.selectMesShipmentList(mesShipment);
    }

    @Override
    public int insertMesShipment(MesShipment mesShipment)
    {
        return mesShipmentMapper.insertMesShipment(mesShipment);
    }

    @Override
    public int updateMesShipment(MesShipment mesShipment)
    {
        return mesShipmentMapper.updateMesShipment(mesShipment);
    }

    @Override
    public int deleteMesShipmentById(Long shipmentId)
    {
        return mesShipmentMapper.deleteMesShipmentById(shipmentId);
    }

    @Override
    public int deleteMesShipmentByIds(Long[] shipmentIds)
    {
        return mesShipmentMapper.deleteMesShipmentByIds(shipmentIds);
    }
}
