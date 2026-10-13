package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdMaterial;

/**
 * 物料主数据Service接口
 *
 * @author ruoyi
 */
public interface IMdMaterialService
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
     * 批量删除物料主数据
     */
    public int deleteMdMaterialByIds(Long[] ids);

    /**
     * 删除物料主数据信息
     */
    public int deleteMdMaterialById(Long id);
}
