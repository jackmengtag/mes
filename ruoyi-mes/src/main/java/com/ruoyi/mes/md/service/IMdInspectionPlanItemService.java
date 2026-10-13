package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdInspectionPlanItem;

/**
 * 检验方案明细Service接口
 *
 * @author ruoyi
 */
public interface IMdInspectionPlanItemService
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
     * 批量删除检验方案明细
     */
    public int deleteMdInspectionPlanItemByIds(Long[] ids);

    /**
     * 删除检验方案明细信息
     */
    public int deleteMdInspectionPlanItemById(Long id);
}
