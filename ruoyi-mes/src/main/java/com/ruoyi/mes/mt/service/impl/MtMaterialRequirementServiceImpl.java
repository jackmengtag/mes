package com.ruoyi.mes.mt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mt.mapper.MtMaterialRequirementMapper;
import com.ruoyi.mes.mt.domain.MtMaterialRequirement;
import com.ruoyi.mes.mt.service.IMtMaterialRequirementService;

/**
 * 物料需求Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MtMaterialRequirementServiceImpl implements IMtMaterialRequirementService
{
    @Autowired
    private MtMaterialRequirementMapper mtMaterialRequirementMapper;

    /**
     * 查询物料需求
     */
    @Override
    public MtMaterialRequirement selectMtMaterialRequirementById(Long id)
    {
        return mtMaterialRequirementMapper.selectMtMaterialRequirementById(id);
    }

    /**
     * 查询物料需求列表
     */
    @Override
    public List<MtMaterialRequirement> selectMtMaterialRequirementList(MtMaterialRequirement mtMaterialRequirement)
    {
        return mtMaterialRequirementMapper.selectMtMaterialRequirementList(mtMaterialRequirement);
    }

    /**
     * 新增物料需求
     */
    @Override
    public int insertMtMaterialRequirement(MtMaterialRequirement mtMaterialRequirement)
    {
        return mtMaterialRequirementMapper.insertMtMaterialRequirement(mtMaterialRequirement);
    }

    /**
     * 修改物料需求
     */
    @Override
    public int updateMtMaterialRequirement(MtMaterialRequirement mtMaterialRequirement)
    {
        return mtMaterialRequirementMapper.updateMtMaterialRequirement(mtMaterialRequirement);
    }

    /**
     * 批量删除物料需求
     */
    @Override
    public int deleteMtMaterialRequirementByIds(Long[] ids)
    {
        return mtMaterialRequirementMapper.deleteMtMaterialRequirementByIds(ids);
    }

    /**
     * 删除物料需求信息
     */
    @Override
    public int deleteMtMaterialRequirementById(Long id)
    {
        return mtMaterialRequirementMapper.deleteMtMaterialRequirementById(id);
    }
}
