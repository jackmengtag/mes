package com.ruoyi.mes.eq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.eq.mapper.EqBindMapper;
import com.ruoyi.mes.eq.domain.EqBind;
import com.ruoyi.mes.eq.service.IEqBindService;

/**
 * 设备与工单绑定Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class EqBindServiceImpl implements IEqBindService
{
    @Autowired
    private EqBindMapper eqBindMapper;

    /**
     * 查询设备与工单绑定
     */
    @Override
    public EqBind selectEqBindById(Long id)
    {
        return eqBindMapper.selectEqBindById(id);
    }

    /**
     * 查询设备与工单绑定列表
     */
    @Override
    public List<EqBind> selectEqBindList(EqBind eqBind)
    {
        return eqBindMapper.selectEqBindList(eqBind);
    }

    /**
     * 新增设备与工单绑定
     */
    @Override
    public int insertEqBind(EqBind eqBind)
    {
        return eqBindMapper.insertEqBind(eqBind);
    }

    /**
     * 修改设备与工单绑定
     */
    @Override
    public int updateEqBind(EqBind eqBind)
    {
        return eqBindMapper.updateEqBind(eqBind);
    }

    /**
     * 批量删除设备与工单绑定
     */
    @Override
    public int deleteEqBindByIds(Long[] ids)
    {
        return eqBindMapper.deleteEqBindByIds(ids);
    }

    /**
     * 删除设备与工单绑定信息
     */
    @Override
    public int deleteEqBindById(Long id)
    {
        return eqBindMapper.deleteEqBindById(id);
    }
}
