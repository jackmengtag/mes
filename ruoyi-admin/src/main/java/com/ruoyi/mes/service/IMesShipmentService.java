package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesShipment;

/**
 * 销售出库Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesShipmentService
{
    public MesShipment selectMesShipmentById(Long shipmentId);

    public List<MesShipment> selectMesShipmentList(MesShipment mesShipment);

    public int insertMesShipment(MesShipment mesShipment);

    public int updateMesShipment(MesShipment mesShipment);

    public int deleteMesShipmentById(Long shipmentId);

    public int deleteMesShipmentByIds(Long[] shipmentIds);
}
