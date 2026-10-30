package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesPlanMapper;
import com.ruoyi.mes.domain.MesPlan;
import com.ruoyi.mes.service.IMesPlanService;

/**
 * 生产计划Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesPlanServiceImpl implements IMesPlanService
{
    @Autowired
    private MesPlanMapper mesPlanMapper;

    @Override
    public MesPlan selectMesPlanById(Long planId)
    {
        return mesPlanMapper.selectMesPlanById(planId);
    }

    @Override
    public List<MesPlan> selectMesPlanList(MesPlan mesPlan)
    {
        return mesPlanMapper.selectMesPlanList(mesPlan);
    }

    @Override
    public int insertMesPlan(MesPlan mesPlan)
    {
        return mesPlanMapper.insertMesPlan(mesPlan);
    }

    @Override
    public int updateMesPlan(MesPlan mesPlan)
    {
        return mesPlanMapper.updateMesPlan(mesPlan);
    }

    @Override
    public int deleteMesPlanById(Long planId)
    {
        return mesPlanMapper.deleteMesPlanById(planId);
    }

    @Override
    public int deleteMesPlanByIds(Long[] planIds)
    {
        return mesPlanMapper.deleteMesPlanByIds(planIds);
    }
}
