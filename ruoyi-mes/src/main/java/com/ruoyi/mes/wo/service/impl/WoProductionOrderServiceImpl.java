package com.ruoyi.mes.wo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.wo.mapper.WoProductionOrderMapper;
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.service.IWoProductionOrderService;

/**
 * 生产工单Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class WoProductionOrderServiceImpl implements IWoProductionOrderService
{
    @Autowired
    private WoProductionOrderMapper woProductionOrderMapper;

    /**
     * 查询生产工单
     */
    @Override
    public WoProductionOrder selectWoProductionOrderById(Long id)
    {
        return woProductionOrderMapper.selectWoProductionOrderById(id);
    }

    /**
     * 查询生产工单列表
     */
    @Override
    public List<WoProductionOrder> selectWoProductionOrderList(WoProductionOrder woProductionOrder)
    {
        return woProductionOrderMapper.selectWoProductionOrderList(woProductionOrder);
    }

    /**
     * 新增生产工单
     */
    @Override
    public int insertWoProductionOrder(WoProductionOrder woProductionOrder)
    {
        return woProductionOrderMapper.insertWoProductionOrder(woProductionOrder);
    }

    /**
     * 修改生产工单
     */
    @Override
    public int updateWoProductionOrder(WoProductionOrder woProductionOrder)
    {
        return woProductionOrderMapper.updateWoProductionOrder(woProductionOrder);
    }

    /**
     * 批量删除生产工单
     */
    @Override
    public int deleteWoProductionOrderByIds(Long[] ids)
    {
        return woProductionOrderMapper.deleteWoProductionOrderByIds(ids);
    }

    /**
     * 删除生产工单信息
     */
    @Override
    public int deleteWoProductionOrderById(Long id)
    {
        return woProductionOrderMapper.deleteWoProductionOrderById(id);
    }
}
