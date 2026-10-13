package com.ruoyi.mes.wo.mapper;

import java.util.List;
import com.ruoyi.mes.wo.domain.WoProductionOrder;

/**
 * 生产工单Mapper接口
 *
 * @author ruoyi
 */
public interface WoProductionOrderMapper
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
     * 删除生产工单
     */
    public int deleteWoProductionOrderById(Long id);

    /**
     * 批量删除生产工单
     */
    public int deleteWoProductionOrderByIds(Long[] ids);
}
