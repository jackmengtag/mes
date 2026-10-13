package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdEquipment;

/**
 * 设备档案Service接口
 *
 * @author ruoyi
 */
public interface IMdEquipmentService
{
    /**
     * 查询设备档案
     */
    public MdEquipment selectMdEquipmentById(Long id);

    /**
     * 查询设备档案列表
     */
    public List<MdEquipment> selectMdEquipmentList(MdEquipment mdEquipment);

    /**
     * 新增设备档案
     */
    public int insertMdEquipment(MdEquipment mdEquipment);

    /**
     * 修改设备档案
     */
    public int updateMdEquipment(MdEquipment mdEquipment);

    /**
     * 批量删除设备档案
     */
    public int deleteMdEquipmentByIds(Long[] ids);

    /**
     * 删除设备档案信息
     */
    public int deleteMdEquipmentById(Long id);
}
