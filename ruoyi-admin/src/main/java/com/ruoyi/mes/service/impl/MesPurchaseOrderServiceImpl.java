package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesPurchaseOrderMapper;
import com.ruoyi.mes.domain.MesPurchaseOrder;
import com.ruoyi.mes.service.IMesPurchaseOrderService;

/**
 * 采购订单Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesPurchaseOrderServiceImpl implements IMesPurchaseOrderService
{
    @Autowired
    private MesPurchaseOrderMapper mesPurchaseOrderMapper;

    @Override
    public MesPurchaseOrder selectMesPurchaseOrderById(Long purchaseId)
    {
        return mesPurchaseOrderMapper.selectMesPurchaseOrderById(purchaseId);
    }

    @Override
    public List<MesPurchaseOrder> selectMesPurchaseOrderList(MesPurchaseOrder mesPurchaseOrder)
    {
        return mesPurchaseOrderMapper.selectMesPurchaseOrderList(mesPurchaseOrder);
    }

    @Override
    public int insertMesPurchaseOrder(MesPurchaseOrder mesPurchaseOrder)
    {
        return mesPurchaseOrderMapper.insertMesPurchaseOrder(mesPurchaseOrder);
    }

    @Override
    public int updateMesPurchaseOrder(MesPurchaseOrder mesPurchaseOrder)
    {
        return mesPurchaseOrderMapper.updateMesPurchaseOrder(mesPurchaseOrder);
    }

    @Override
    public int deleteMesPurchaseOrderById(Long purchaseId)
    {
        return mesPurchaseOrderMapper.deleteMesPurchaseOrderById(purchaseId);
    }

    @Override
    public int deleteMesPurchaseOrderByIds(Long[] purchaseIds)
    {
        return mesPurchaseOrderMapper.deleteMesPurchaseOrderByIds(purchaseIds);
    }
}
