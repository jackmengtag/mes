package com.ruoyi.mes.eq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.eq.mapper.EqStatusMapper;
import com.ruoyi.mes.eq.domain.EqStatus;
import com.ruoyi.mes.eq.service.IEqStatusService;

/**
 * 设备状态Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class EqStatusServiceImpl implements IEqStatusService
{
    @Autowired
    private EqStatusMapper eqStatusMapper;

    /**
     * 查询设备状态
     */
    @Override
    public EqStatus selectEqStatusById(Long id)
    {
        return eqStatusMapper.selectEqStatusById(id);
    }

    /**
     * 查询设备状态列表
     */
    @Override
    public List<EqStatus> selectEqStatusList(EqStatus eqStatus)
    {
        return eqStatusMapper.selectEqStatusList(eqStatus);
    }

    /**
     * 新增设备状态
     */
    @Override
    public int insertEqStatus(EqStatus eqStatus)
    {
        return eqStatusMapper.insertEqStatus(eqStatus);
    }

    /**
     * 修改设备状态
     */
    @Override
    public int updateEqStatus(EqStatus eqStatus)
    {
        return eqStatusMapper.updateEqStatus(eqStatus);
    }

    /**
     * 批量删除设备状态
     */
    @Override
    public int deleteEqStatusByIds(Long[] ids)
    {
        return eqStatusMapper.deleteEqStatusByIds(ids);
    }

    /**
     * 删除设备状态信息
     */
    @Override
    public int deleteEqStatusById(Long id)
    {
        return eqStatusMapper.deleteEqStatusById(id);
    }
}
