package com.ruoyi.mes.ps.service;

import java.util.List;
import com.ruoyi.mes.ps.domain.PsPlan;

/**
 * 生产计划Service接口
 *
 * @author ruoyi
 */
public interface IPsPlanService
{
    /**
     * 查询生产计划
     */
    public PsPlan selectPsPlanById(Long id);

    /**
     * 查询生产计划列表
     */
    public List<PsPlan> selectPsPlanList(PsPlan psPlan);

    /**
     * 新增生产计划
     */
    public int insertPsPlan(PsPlan psPlan);

    /**
     * 修改生产计划
     */
    public int updatePsPlan(PsPlan psPlan);

    /**
     * 批量删除生产计划
     */
    public int deletePsPlanByIds(Long[] ids);

    /**
     * 删除生产计划信息
     */
    public int deletePsPlanById(Long id);
}
