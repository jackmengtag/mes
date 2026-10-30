package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesWorkOrderMapper;
import com.ruoyi.mes.domain.MesWorkOrder;
import com.ruoyi.mes.service.IMesWorkOrderService;

/**
 * 生产工单Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesWorkOrderServiceImpl implements IMesWorkOrderService
{
    @Autowired
    private MesWorkOrderMapper mesWorkOrderMapper;

    @Override
    public MesWorkOrder selectMesWorkOrderById(Long workOrderId)
    {
        return mesWorkOrderMapper.selectMesWorkOrderById(workOrderId);
    }

    @Override
    public List<MesWorkOrder> selectMesWorkOrderList(MesWorkOrder mesWorkOrder)
    {
        return mesWorkOrderMapper.selectMesWorkOrderList(mesWorkOrder);
    }

    @Override
    public int insertMesWorkOrder(MesWorkOrder mesWorkOrder)
    {
        return mesWorkOrderMapper.insertMesWorkOrder(mesWorkOrder);
    }

    @Override
    public int updateMesWorkOrder(MesWorkOrder mesWorkOrder)
    {
        return mesWorkOrderMapper.updateMesWorkOrder(mesWorkOrder);
    }

    @Override
    public int deleteMesWorkOrderById(Long workOrderId)
    {
        return mesWorkOrderMapper.deleteMesWorkOrderById(workOrderId);
    }

    @Override
    public int deleteMesWorkOrderByIds(Long[] workOrderIds)
    {
        return mesWorkOrderMapper.deleteMesWorkOrderByIds(workOrderIds);
    }
}
