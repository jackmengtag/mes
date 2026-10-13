package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdEquipmentPoint;

/**
 * 设备采集点位Service接口
 *
 * @author ruoyi
 */
public interface IMdEquipmentPointService
{
    /**
     * 查询设备采集点位
     */
    public MdEquipmentPoint selectMdEquipmentPointById(Long id);

    /**
     * 查询设备采集点位列表
     */
    public List<MdEquipmentPoint> selectMdEquipmentPointList(MdEquipmentPoint mdEquipmentPoint);

    /**
     * 新增设备采集点位
     */
    public int insertMdEquipmentPoint(MdEquipmentPoint mdEquipmentPoint);

    /**
     * 修改设备采集点位
     */
    public int updateMdEquipmentPoint(MdEquipmentPoint mdEquipmentPoint);

    /**
     * 批量删除设备采集点位
     */
    public int deleteMdEquipmentPointByIds(Long[] ids);

    /**
     * 删除设备采集点位信息
     */
    public int deleteMdEquipmentPointById(Long id);
}
