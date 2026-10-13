package com.ruoyi.mes.ps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.ps.mapper.PsPlanMapper;
import com.ruoyi.mes.ps.domain.PsPlan;
import com.ruoyi.mes.ps.service.IPsPlanService;

/**
 * 生产计划Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class PsPlanServiceImpl implements IPsPlanService
{
    @Autowired
    private PsPlanMapper psPlanMapper;

    /**
     * 查询生产计划
     */
    @Override
    public PsPlan selectPsPlanById(Long id)
    {
        return psPlanMapper.selectPsPlanById(id);
    }

    /**
     * 查询生产计划列表
     */
    @Override
    public List<PsPlan> selectPsPlanList(PsPlan psPlan)
    {
        return psPlanMapper.selectPsPlanList(psPlan);
    }

    /**
     * 新增生产计划
     */
    @Override
    public int insertPsPlan(PsPlan psPlan)
    {
        return psPlanMapper.insertPsPlan(psPlan);
    }

    /**
     * 修改生产计划
     */
    @Override
    public int updatePsPlan(PsPlan psPlan)
    {
        return psPlanMapper.updatePsPlan(psPlan);
    }

    /**
     * 批量删除生产计划
     */
    @Override
    public int deletePsPlanByIds(Long[] ids)
    {
        return psPlanMapper.deletePsPlanByIds(ids);
    }

    /**
     * 删除生产计划信息
     */
    @Override
    public int deletePsPlanById(Long id)
    {
        return psPlanMapper.deletePsPlanById(id);
    }
}
