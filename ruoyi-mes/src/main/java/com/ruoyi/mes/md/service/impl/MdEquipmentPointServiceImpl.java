package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdEquipmentPointMapper;
import com.ruoyi.mes.md.domain.MdEquipmentPoint;
import com.ruoyi.mes.md.service.IMdEquipmentPointService;

/**
 * 设备采集点位Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdEquipmentPointServiceImpl implements IMdEquipmentPointService
{
    @Autowired
    private MdEquipmentPointMapper mdEquipmentPointMapper;

    /**
     * 查询设备采集点位
     */
    @Override
    public MdEquipmentPoint selectMdEquipmentPointById(Long id)
    {
        return mdEquipmentPointMapper.selectMdEquipmentPointById(id);
    }

    /**
     * 查询设备采集点位列表
     */
    @Override
    public List<MdEquipmentPoint> selectMdEquipmentPointList(MdEquipmentPoint mdEquipmentPoint)
    {
        return mdEquipmentPointMapper.selectMdEquipmentPointList(mdEquipmentPoint);
    }

    /**
     * 新增设备采集点位
     */
    @Override
    public int insertMdEquipmentPoint(MdEquipmentPoint mdEquipmentPoint)
    {
        return mdEquipmentPointMapper.insertMdEquipmentPoint(mdEquipmentPoint);
    }

    /**
     * 修改设备采集点位
     */
    @Override
    public int updateMdEquipmentPoint(MdEquipmentPoint mdEquipmentPoint)
    {
        return mdEquipmentPointMapper.updateMdEquipmentPoint(mdEquipmentPoint);
    }

    /**
     * 批量删除设备采集点位
     */
    @Override
    public int deleteMdEquipmentPointByIds(Long[] ids)
    {
        return mdEquipmentPointMapper.deleteMdEquipmentPointByIds(ids);
    }

    /**
     * 删除设备采集点位信息
     */
    @Override
    public int deleteMdEquipmentPointById(Long id)
    {
        return mdEquipmentPointMapper.deleteMdEquipmentPointById(id);
    }
}
