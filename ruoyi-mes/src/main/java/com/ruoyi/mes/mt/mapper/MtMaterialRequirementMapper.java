package com.ruoyi.mes.mt.mapper;

import java.util.List;
import com.ruoyi.mes.mt.domain.MtMaterialRequirement;

/**
 * 物料需求Mapper接口
 *
 * @author ruoyi
 */
public interface MtMaterialRequirementMapper
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
     * 删除物料需求
     */
    public int deleteMtMaterialRequirementById(Long id);

    /**
     * 批量删除物料需求
     */
    public int deleteMtMaterialRequirementByIds(Long[] ids);
}
