package com.ruoyi.mes.eq.mapper;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqBind;

/**
 * 设备与工单绑定Mapper接口
 *
 * @author ruoyi
 */
public interface EqBindMapper
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
     * 删除设备与工单绑定
     */
    public int deleteEqBindById(Long id);

    /**
     * 批量删除设备与工单绑定
     */
    public int deleteEqBindByIds(Long[] ids);
}
