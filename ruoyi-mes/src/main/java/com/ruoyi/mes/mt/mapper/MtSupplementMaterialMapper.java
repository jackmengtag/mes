package com.ruoyi.mes.mt.mapper;

import java.util.List;
import com.ruoyi.mes.mt.domain.MtSupplementMaterial;

/**
 * 补料Mapper接口
 *
 * @author ruoyi
 */
public interface MtSupplementMaterialMapper
{
    /**
     * 查询补料
     */
    public MtSupplementMaterial selectMtSupplementMaterialById(Long id);

    /**
     * 查询补料列表
     */
    public List<MtSupplementMaterial> selectMtSupplementMaterialList(MtSupplementMaterial mtSupplementMaterial);

    /**
     * 新增补料
     */
    public int insertMtSupplementMaterial(MtSupplementMaterial mtSupplementMaterial);

    /**
     * 修改补料
     */
    public int updateMtSupplementMaterial(MtSupplementMaterial mtSupplementMaterial);

    /**
     * 删除补料
     */
    public int deleteMtSupplementMaterialById(Long id);

    /**
     * 批量删除补料
     */
    public int deleteMtSupplementMaterialByIds(Long[] ids);
}
