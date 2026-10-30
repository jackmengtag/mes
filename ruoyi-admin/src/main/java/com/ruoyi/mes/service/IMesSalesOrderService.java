package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesSalesOrder;

/**
 * 销售订单Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesSalesOrderService
{
    public MesSalesOrder selectMesSalesOrderById(Long salesId);

    public List<MesSalesOrder> selectMesSalesOrderList(MesSalesOrder mesSalesOrder);

    public int insertMesSalesOrder(MesSalesOrder mesSalesOrder);

    public int updateMesSalesOrder(MesSalesOrder mesSalesOrder);

    public int deleteMesSalesOrderById(Long salesId);

    public int deleteMesSalesOrderByIds(Long[] salesIds);
}
