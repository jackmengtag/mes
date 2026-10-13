package com.ruoyi.mes.mt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mt.mapper.MtReturnMaterialMapper;
import com.ruoyi.mes.mt.domain.MtReturnMaterial;
import com.ruoyi.mes.mt.service.IMtReturnMaterialService;

/**
 * 退料Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MtReturnMaterialServiceImpl implements IMtReturnMaterialService
{
    @Autowired
    private MtReturnMaterialMapper mtReturnMaterialMapper;

    /**
     * 查询退料
     */
    @Override
    public MtReturnMaterial selectMtReturnMaterialById(Long id)
    {
        return mtReturnMaterialMapper.selectMtReturnMaterialById(id);
    }

    /**
     * 查询退料列表
     */
    @Override
    public List<MtReturnMaterial> selectMtReturnMaterialList(MtReturnMaterial mtReturnMaterial)
    {
        return mtReturnMaterialMapper.selectMtReturnMaterialList(mtReturnMaterial);
    }

    /**
     * 新增退料
     */
    @Override
    public int insertMtReturnMaterial(MtReturnMaterial mtReturnMaterial)
    {
        return mtReturnMaterialMapper.insertMtReturnMaterial(mtReturnMaterial);
    }

    /**
     * 修改退料
     */
    @Override
    public int updateMtReturnMaterial(MtReturnMaterial mtReturnMaterial)
    {
        return mtReturnMaterialMapper.updateMtReturnMaterial(mtReturnMaterial);
    }

    /**
     * 批量删除退料
     */
    @Override
    public int deleteMtReturnMaterialByIds(Long[] ids)
    {
        return mtReturnMaterialMapper.deleteMtReturnMaterialByIds(ids);
    }

    /**
     * 删除退料信息
     */
    @Override
    public int deleteMtReturnMaterialById(Long id)
    {
        return mtReturnMaterialMapper.deleteMtReturnMaterialById(id);
    }
}
