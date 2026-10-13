package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdInspectionPlan;

/**
 * 检验方案Service接口
 *
 * @author ruoyi
 */
public interface IMdInspectionPlanService
{
    /**
     * 查询检验方案
     */
    public MdInspectionPlan selectMdInspectionPlanById(Long id);

    /**
     * 查询检验方案列表
     */
    public List<MdInspectionPlan> selectMdInspectionPlanList(MdInspectionPlan mdInspectionPlan);

    /**
     * 新增检验方案
     */
    public int insertMdInspectionPlan(MdInspectionPlan mdInspectionPlan);

    /**
     * 修改检验方案
     */
    public int updateMdInspectionPlan(MdInspectionPlan mdInspectionPlan);

    /**
     * 批量删除检验方案
     */
    public int deleteMdInspectionPlanByIds(Long[] ids);

    /**
     * 删除检验方案信息
     */
    public int deleteMdInspectionPlanById(Long id);
}
