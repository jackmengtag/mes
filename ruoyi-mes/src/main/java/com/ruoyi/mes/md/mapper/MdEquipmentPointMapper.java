package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdEquipmentPoint;

/**
 * 设备采集点位Mapper接口
 *
 * @author ruoyi
 */
public interface MdEquipmentPointMapper
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
     * 删除设备采集点位
     */
    public int deleteMdEquipmentPointById(Long id);

    /**
     * 批量删除设备采集点位
     */
    public int deleteMdEquipmentPointByIds(Long[] ids);
}
