package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesMaterialMapper;
import com.ruoyi.mes.domain.MesMaterial;
import com.ruoyi.mes.service.IMesMaterialService;

/**
 * 物料主数据Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesMaterialServiceImpl implements IMesMaterialService
{
    @Autowired
    private MesMaterialMapper mesMaterialMapper;

    @Override
    public MesMaterial selectMesMaterialById(Long materialId)
    {
        return mesMaterialMapper.selectMesMaterialById(materialId);
    }

    @Override
    public List<MesMaterial> selectMesMaterialList(MesMaterial mesMaterial)
    {
        return mesMaterialMapper.selectMesMaterialList(mesMaterial);
    }

    @Override
    public int insertMesMaterial(MesMaterial mesMaterial)
    {
        return mesMaterialMapper.insertMesMaterial(mesMaterial);
    }

    @Override
    public int updateMesMaterial(MesMaterial mesMaterial)
    {
        return mesMaterialMapper.updateMesMaterial(mesMaterial);
    }

    @Override
    public int deleteMesMaterialById(Long materialId)
    {
        return mesMaterialMapper.deleteMesMaterialById(materialId);
    }

    @Override
    public int deleteMesMaterialByIds(Long[] materialIds)
    {
        return mesMaterialMapper.deleteMesMaterialByIds(materialIds);
    }
}
