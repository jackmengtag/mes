package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdInspectionPlan;

/**
 * 检验方案Mapper接口
 *
 * @author ruoyi
 */
public interface MdInspectionPlanMapper
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
     * 删除检验方案
     */
    public int deleteMdInspectionPlanById(Long id);

    /**
     * 批量删除检验方案
     */
    public int deleteMdInspectionPlanByIds(Long[] ids);
}
