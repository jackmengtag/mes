package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesPlan;

/**
 * 生产计划Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesPlanService
{
    public MesPlan selectMesPlanById(Long planId);

    public List<MesPlan> selectMesPlanList(MesPlan mesPlan);

    public int insertMesPlan(MesPlan mesPlan);

    public int updateMesPlan(MesPlan mesPlan);

    public int deleteMesPlanById(Long planId);

    public int deleteMesPlanByIds(Long[] planIds);
}
