package com.ruoyi.mes.eq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.eq.mapper.EqCommandMapper;
import com.ruoyi.mes.eq.domain.EqCommand;
import com.ruoyi.mes.eq.service.IEqCommandService;

/**
 * 设备命令Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class EqCommandServiceImpl implements IEqCommandService
{
    @Autowired
    private EqCommandMapper eqCommandMapper;

    /**
     * 查询设备命令
     */
    @Override
    public EqCommand selectEqCommandById(Long id)
    {
        return eqCommandMapper.selectEqCommandById(id);
    }

    /**
     * 查询设备命令列表
     */
    @Override
    public List<EqCommand> selectEqCommandList(EqCommand eqCommand)
    {
        return eqCommandMapper.selectEqCommandList(eqCommand);
    }

    /**
     * 新增设备命令
     */
    @Override
    public int insertEqCommand(EqCommand eqCommand)
    {
        return eqCommandMapper.insertEqCommand(eqCommand);
    }

    /**
     * 修改设备命令
     */
    @Override
    public int updateEqCommand(EqCommand eqCommand)
    {
        return eqCommandMapper.updateEqCommand(eqCommand);
    }

    /**
     * 批量删除设备命令
     */
    @Override
    public int deleteEqCommandByIds(Long[] ids)
    {
        return eqCommandMapper.deleteEqCommandByIds(ids);
    }

    /**
     * 删除设备命令信息
     */
    @Override
    public int deleteEqCommandById(Long id)
    {
        return eqCommandMapper.deleteEqCommandById(id);
    }
}
