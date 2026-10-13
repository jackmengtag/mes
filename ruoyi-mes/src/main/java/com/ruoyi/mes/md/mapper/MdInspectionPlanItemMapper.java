package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdInspectionPlanItem;

/**
 * 检验方案明细Mapper接口
 *
 * @author ruoyi
 */
public interface MdInspectionPlanItemMapper
{
    /**
     * 查询检验方案明细
     */
    public MdInspectionPlanItem selectMdInspectionPlanItemById(Long id);

    /**
     * 查询检验方案明细列表
     */
    public List<MdInspectionPlanItem> selectMdInspectionPlanItemList(MdInspectionPlanItem mdInspectionPlanItem);

    /**
     * 新增检验方案明细
     */
    public int insertMdInspectionPlanItem(MdInspectionPlanItem mdInspectionPlanItem);

    /**
     * 修改检验方案明细
     */
    public int updateMdInspectionPlanItem(MdInspectionPlanItem mdInspectionPlanItem);

    /**
     * 删除检验方案明细
     */
    public int deleteMdInspectionPlanItemById(Long id);

    /**
     * 批量删除检验方案明细
     */
    public int deleteMdInspectionPlanItemByIds(Long[] ids);
}
