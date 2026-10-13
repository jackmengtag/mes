package com.ruoyi.mes.eq.mapper;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqStatus;

/**
 * 设备状态Mapper接口
 *
 * @author ruoyi
 */
public interface EqStatusMapper
{
    /**
     * 查询设备状态
     */
    public EqStatus selectEqStatusById(Long id);

    /**
     * 查询设备状态列表
     */
    public List<EqStatus> selectEqStatusList(EqStatus eqStatus);

    /**
     * 新增设备状态
     */
    public int insertEqStatus(EqStatus eqStatus);

    /**
     * 修改设备状态
     */
    public int updateEqStatus(EqStatus eqStatus);

    /**
     * 删除设备状态
     */
    public int deleteEqStatusById(Long id);

    /**
     * 批量删除设备状态
     */
    public int deleteEqStatusByIds(Long[] ids);
}
