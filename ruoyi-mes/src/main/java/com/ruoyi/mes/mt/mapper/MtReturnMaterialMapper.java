package com.ruoyi.mes.mt.mapper;

import java.util.List;
import com.ruoyi.mes.mt.domain.MtReturnMaterial;

/**
 * 退料Mapper接口
 *
 * @author ruoyi
 */
public interface MtReturnMaterialMapper
{
    /**
     * 查询退料
     */
    public MtReturnMaterial selectMtReturnMaterialById(Long id);

    /**
     * 查询退料列表
     */
    public List<MtReturnMaterial> selectMtReturnMaterialList(MtReturnMaterial mtReturnMaterial);

    /**
     * 新增退料
     */
    public int insertMtReturnMaterial(MtReturnMaterial mtReturnMaterial);

    /**
     * 修改退料
     */
    public int updateMtReturnMaterial(MtReturnMaterial mtReturnMaterial);

    /**
     * 删除退料
     */
    public int deleteMtReturnMaterialById(Long id);

    /**
     * 批量删除退料
     */
    public int deleteMtReturnMaterialByIds(Long[] ids);
}
