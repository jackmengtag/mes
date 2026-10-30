package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesPlan;

/**
 * 生产计划Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesPlanMapper
{
    public MesPlan selectMesPlanById(Long planId);

    public List<MesPlan> selectMesPlanList(MesPlan mesPlan);

    public int insertMesPlan(MesPlan mesPlan);

    public int updateMesPlan(MesPlan mesPlan);

    public int deleteMesPlanById(Long planId);

    public int deleteMesPlanByIds(Long[] planIds);
}
