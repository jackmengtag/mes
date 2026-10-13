package com.ruoyi.mes.eq.service;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqBind;

/**
 * 设备与工单绑定Service接口
 *
 * @author ruoyi
 */
public interface IEqBindService
{
    /**
     * 查询设备与工单绑定
     */
    public EqBind selectEqBindById(Long id);

    /**
     * 查询设备与工单绑定列表
     */
    public List<EqBind> selectEqBindList(EqBind eqBind);

    /**
     * 新增设备与工单绑定
     */
    public int insertEqBind(EqBind eqBind);

    /**
     * 修改设备与工单绑定
     */
    public int updateEqBind(EqBind eqBind);

    /**
     * 批量删除设备与工单绑定
     */
    public int deleteEqBindByIds(Long[] ids);

    /**
     * 删除设备与工单绑定信息
     */
    public int deleteEqBindById(Long id);
}
