package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesPurchaseOrder;

/**
 * 采购订单Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesPurchaseOrderMapper
{
    public MesPurchaseOrder selectMesPurchaseOrderById(Long purchaseId);

    public List<MesPurchaseOrder> selectMesPurchaseOrderList(MesPurchaseOrder mesPurchaseOrder);

    public int insertMesPurchaseOrder(MesPurchaseOrder mesPurchaseOrder);

    public int updateMesPurchaseOrder(MesPurchaseOrder mesPurchaseOrder);

    public int deleteMesPurchaseOrderById(Long purchaseId);

    public int deleteMesPurchaseOrderByIds(Long[] purchaseIds);
}
