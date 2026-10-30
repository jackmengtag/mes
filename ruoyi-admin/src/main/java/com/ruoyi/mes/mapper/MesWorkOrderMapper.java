package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesWorkOrder;

/**
 * 生产工单Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesWorkOrderMapper
{
    public MesWorkOrder selectMesWorkOrderById(Long workOrderId);

    public List<MesWorkOrder> selectMesWorkOrderList(MesWorkOrder mesWorkOrder);

    public int insertMesWorkOrder(MesWorkOrder mesWorkOrder);

    public int updateMesWorkOrder(MesWorkOrder mesWorkOrder);

    public int deleteMesWorkOrderById(Long workOrderId);

    public int deleteMesWorkOrderByIds(Long[] workOrderIds);
}
