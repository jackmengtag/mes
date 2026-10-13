package com.ruoyi.mes.mt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mt.mapper.MtSupplementMaterialMapper;
import com.ruoyi.mes.mt.domain.MtSupplementMaterial;
import com.ruoyi.mes.mt.service.IMtSupplementMaterialService;

/**
 * 补料Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MtSupplementMaterialServiceImpl implements IMtSupplementMaterialService
{
    @Autowired
    private MtSupplementMaterialMapper mtSupplementMaterialMapper;

    /**
     * 查询补料
     */
    @Override
    public MtSupplementMaterial selectMtSupplementMaterialById(Long id)
    {
        return mtSupplementMaterialMapper.selectMtSupplementMaterialById(id);
    }

    /**
     * 查询补料列表
     */
    @Override
    public List<MtSupplementMaterial> selectMtSupplementMaterialList(MtSupplementMaterial mtSupplementMaterial)
    {
        return mtSupplementMaterialMapper.selectMtSupplementMaterialList(mtSupplementMaterial);
    }

    /**
     * 新增补料
     */
    @Override
    public int insertMtSupplementMaterial(MtSupplementMaterial mtSupplementMaterial)
    {
        return mtSupplementMaterialMapper.insertMtSupplementMaterial(mtSupplementMaterial);
    }

    /**
     * 修改补料
     */
    @Override
    public int updateMtSupplementMaterial(MtSupplementMaterial mtSupplementMaterial)
    {
        return mtSupplementMaterialMapper.updateMtSupplementMaterial(mtSupplementMaterial);
    }

    /**
     * 批量删除补料
     */
    @Override
    public int deleteMtSupplementMaterialByIds(Long[] ids)
    {
        return mtSupplementMaterialMapper.deleteMtSupplementMaterialByIds(ids);
    }

    /**
     * 删除补料信息
     */
    @Override
    public int deleteMtSupplementMaterialById(Long id)
    {
        return mtSupplementMaterialMapper.deleteMtSupplementMaterialById(id);
    }
}
