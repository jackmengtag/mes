package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdMaterialMapper;
import com.ruoyi.mes.md.domain.MdMaterial;
import com.ruoyi.mes.md.service.IMdMaterialService;

/**
 * 物料主数据Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdMaterialServiceImpl implements IMdMaterialService
{
    @Autowired
    private MdMaterialMapper mdMaterialMapper;

    /**
     * 查询物料主数据
     */
    @Override
    public MdMaterial selectMdMaterialById(Long id)
    {
        return mdMaterialMapper.selectMdMaterialById(id);
    }

    /**
     * 查询物料主数据列表
     */
    @Override
    public List<MdMaterial> selectMdMaterialList(MdMaterial mdMaterial)
    {
        return mdMaterialMapper.selectMdMaterialList(mdMaterial);
    }

    /**
     * 新增物料主数据
     */
    @Override
    public int insertMdMaterial(MdMaterial mdMaterial)
    {
        return mdMaterialMapper.insertMdMaterial(mdMaterial);
    }

    /**
     * 修改物料主数据
     */
    @Override
    public int updateMdMaterial(MdMaterial mdMaterial)
    {
        return mdMaterialMapper.updateMdMaterial(mdMaterial);
    }

    /**
     * 批量删除物料主数据
     */
    @Override
    public int deleteMdMaterialByIds(Long[] ids)
    {
        return mdMaterialMapper.deleteMdMaterialByIds(ids);
    }

    /**
     * 删除物料主数据信息
     */
    @Override
    public int deleteMdMaterialById(Long id)
    {
        return mdMaterialMapper.deleteMdMaterialById(id);
    }
}
