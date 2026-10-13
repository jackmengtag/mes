package com.ruoyi.mes.eq.service;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqCommand;

/**
 * 设备命令Service接口
 *
 * @author ruoyi
 */
public interface IEqCommandService
{
    /**
     * 查询设备命令
     */
    public EqCommand selectEqCommandById(Long id);

    /**
     * 查询设备命令列表
     */
    public List<EqCommand> selectEqCommandList(EqCommand eqCommand);

    /**
     * 新增设备命令
     */
    public int insertEqCommand(EqCommand eqCommand);

    /**
     * 修改设备命令
     */
    public int updateEqCommand(EqCommand eqCommand);

    /**
     * 批量删除设备命令
     */
    public int deleteEqCommandByIds(Long[] ids);

    /**
     * 删除设备命令信息
     */
    public int deleteEqCommandById(Long id);
}
