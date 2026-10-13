package com.ruoyi.mes.mt.service;

import java.util.List;
import com.ruoyi.mes.mt.domain.MtMaterialRequirement;

/**
 * 物料需求Service接口
 *
 * @author ruoyi
 */
public interface IMtMaterialRequirementService
{
    /**
     * 查询物料需求
     */
    public MtMaterialRequirement selectMtMaterialRequirementById(Long id);

    /**
     * 查询物料需求列表
     */
    public List<MtMaterialRequirement> selectMtMaterialRequirementList(MtMaterialRequirement mtMaterialRequirement);

    /**
     * 新增物料需求
     */
    public int insertMtMaterialRequirement(MtMaterialRequirement mtMaterialRequirement);

    /**
     * 修改物料需求
     */
    public int updateMtMaterialRequirement(MtMaterialRequirement mtMaterialRequirement);

    /**
     * 批量删除物料需求
     */
    public int deleteMtMaterialRequirementByIds(Long[] ids);

    /**
     * 删除物料需求信息
     */
    public int deleteMtMaterialRequirementById(Long id);
}
