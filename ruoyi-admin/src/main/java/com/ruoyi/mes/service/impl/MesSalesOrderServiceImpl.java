package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesSalesOrderMapper;
import com.ruoyi.mes.domain.MesSalesOrder;
import com.ruoyi.mes.service.IMesSalesOrderService;

/**
 * 销售订单Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesSalesOrderServiceImpl implements IMesSalesOrderService
{
    @Autowired
    private MesSalesOrderMapper mesSalesOrderMapper;

    @Override
    public MesSalesOrder selectMesSalesOrderById(Long salesId)
    {
        return mesSalesOrderMapper.selectMesSalesOrderById(salesId);
    }

    @Override
    public List<MesSalesOrder> selectMesSalesOrderList(MesSalesOrder mesSalesOrder)
    {
        return mesSalesOrderMapper.selectMesSalesOrderList(mesSalesOrder);
    }

    @Override
    public int insertMesSalesOrder(MesSalesOrder mesSalesOrder)
    {
        return mesSalesOrderMapper.insertMesSalesOrder(mesSalesOrder);
    }

    @Override
    public int updateMesSalesOrder(MesSalesOrder mesSalesOrder)
    {
        return mesSalesOrderMapper.updateMesSalesOrder(mesSalesOrder);
    }

    @Override
    public int deleteMesSalesOrderById(Long salesId)
    {
        return mesSalesOrderMapper.deleteMesSalesOrderById(salesId);
    }

    @Override
    public int deleteMesSalesOrderByIds(Long[] salesIds)
    {
        return mesSalesOrderMapper.deleteMesSalesOrderByIds(salesIds);
    }
}
