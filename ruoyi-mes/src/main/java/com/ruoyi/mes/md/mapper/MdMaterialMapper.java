package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdMaterial;

/**
 * 物料主数据Mapper接口
 *
 * @author ruoyi
 */
public interface MdMaterialMapper
{
    /**
     * 查询物料主数据
     */
    public MdMaterial selectMdMaterialById(Long id);

    /**
     * 查询物料主数据列表
     */
    public List<MdMaterial> selectMdMaterialList(MdMaterial mdMaterial);

    /**
     * 新增物料主数据
     */
    public int insertMdMaterial(MdMaterial mdMaterial);

    /**
     * 修改物料主数据
     */
    public int updateMdMaterial(MdMaterial mdMaterial);

    /**
     * 删除物料主数据
     */
    public int deleteMdMaterialById(Long id);

    /**
     * 批量删除物料主数据
     */
    public int deleteMdMaterialByIds(Long[] ids);
}
