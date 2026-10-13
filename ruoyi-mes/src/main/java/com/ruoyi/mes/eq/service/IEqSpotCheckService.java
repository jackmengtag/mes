package com.ruoyi.mes.eq.service;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqSpotCheck;

/**
 * 设备点检Service接口
 *
 * @author ruoyi
 */
public interface IEqSpotCheckService
{
    /**
     * 查询设备点检
     */
    public EqSpotCheck selectEqSpotCheckById(Long id);

    /**
     * 查询设备点检列表
     */
    public List<EqSpotCheck> selectEqSpotCheckList(EqSpotCheck eqSpotCheck);

    /**
     * 新增设备点检
     */
    public int insertEqSpotCheck(EqSpotCheck eqSpotCheck);

    /**
     * 修改设备点检
     */
    public int updateEqSpotCheck(EqSpotCheck eqSpotCheck);

    /**
     * 批量删除设备点检
     */
    public int deleteEqSpotCheckByIds(Long[] ids);

    /**
     * 删除设备点检信息
     */
    public int deleteEqSpotCheckById(Long id);
}
