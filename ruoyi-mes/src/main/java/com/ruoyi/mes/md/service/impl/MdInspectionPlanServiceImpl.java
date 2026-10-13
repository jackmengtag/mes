package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdInspectionPlanMapper;
import com.ruoyi.mes.md.domain.MdInspectionPlan;
import com.ruoyi.mes.md.service.IMdInspectionPlanService;

/**
 * 检验方案Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdInspectionPlanServiceImpl implements IMdInspectionPlanService
{
    @Autowired
    private MdInspectionPlanMapper mdInspectionPlanMapper;

    /**
     * 查询检验方案
     */
    @Override
    public MdInspectionPlan selectMdInspectionPlanById(Long id)
    {
        return mdInspectionPlanMapper.selectMdInspectionPlanById(id);
    }

    /**
     * 查询检验方案列表
     */
    @Override
    public List<MdInspectionPlan> selectMdInspectionPlanList(MdInspectionPlan mdInspectionPlan)
    {
        return mdInspectionPlanMapper.selectMdInspectionPlanList(mdInspectionPlan);
    }

    /**
     * 新增检验方案
     */
    @Override
    public int insertMdInspectionPlan(MdInspectionPlan mdInspectionPlan)
    {
        return mdInspectionPlanMapper.insertMdInspectionPlan(mdInspectionPlan);
    }

    /**
     * 修改检验方案
     */
    @Override
    public int updateMdInspectionPlan(MdInspectionPlan mdInspectionPlan)
    {
        return mdInspectionPlanMapper.updateMdInspectionPlan(mdInspectionPlan);
    }

    /**
     * 批量删除检验方案
     */
    @Override
    public int deleteMdInspectionPlanByIds(Long[] ids)
    {
        return mdInspectionPlanMapper.deleteMdInspectionPlanByIds(ids);
    }

    /**
     * 删除检验方案信息
     */
    @Override
    public int deleteMdInspectionPlanById(Long id)
    {
        return mdInspectionPlanMapper.deleteMdInspectionPlanById(id);
    }
}
