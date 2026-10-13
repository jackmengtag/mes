package com.ruoyi.mes.wo.service;

import java.util.List;
import com.ruoyi.mes.wo.domain.WoProductionOrder;

/**
 * 生产工单Service接口
 *
 * @author ruoyi
 */
public interface IWoProductionOrderService
{
    /**
     * 查询生产工单
     */
    public WoProductionOrder selectWoProductionOrderById(Long id);

    /**
     * 查询生产工单列表
     */
    public List<WoProductionOrder> selectWoProductionOrderList(WoProductionOrder woProductionOrder);

    /**
     * 新增生产工单
     */
    public int insertWoProductionOrder(WoProductionOrder woProductionOrder);

    /**
     * 修改生产工单
     */
    public int updateWoProductionOrder(WoProductionOrder woProductionOrder);

    /**
     * 批量删除生产工单
     */
    public int deleteWoProductionOrderByIds(Long[] ids);

    /**
     * 删除生产工单信息
     */
    public int deleteWoProductionOrderById(Long id);
}
