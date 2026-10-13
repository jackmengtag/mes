package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdEquipmentMapper;
import com.ruoyi.mes.md.domain.MdEquipment;
import com.ruoyi.mes.md.service.IMdEquipmentService;

/**
 * 设备档案Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdEquipmentServiceImpl implements IMdEquipmentService
{
    @Autowired
    private MdEquipmentMapper mdEquipmentMapper;

    /**
     * 查询设备档案
     */
    @Override
    public MdEquipment selectMdEquipmentById(Long id)
    {
        return mdEquipmentMapper.selectMdEquipmentById(id);
    }

    /**
     * 查询设备档案列表
     */
    @Override
    public List<MdEquipment> selectMdEquipmentList(MdEquipment mdEquipment)
    {
        return mdEquipmentMapper.selectMdEquipmentList(mdEquipment);
    }

    /**
     * 新增设备档案
     */
    @Override
    public int insertMdEquipment(MdEquipment mdEquipment)
    {
        return mdEquipmentMapper.insertMdEquipment(mdEquipment);
    }

    /**
     * 修改设备档案
     */
    @Override
    public int updateMdEquipment(MdEquipment mdEquipment)
    {
        return mdEquipmentMapper.updateMdEquipment(mdEquipment);
    }

    /**
     * 批量删除设备档案
     */
    @Override
    public int deleteMdEquipmentByIds(Long[] ids)
    {
        return mdEquipmentMapper.deleteMdEquipmentByIds(ids);
    }

    /**
     * 删除设备档案信息
     */
    @Override
    public int deleteMdEquipmentById(Long id)
    {
        return mdEquipmentMapper.deleteMdEquipmentById(id);
    }
}
