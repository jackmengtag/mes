package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesInspectionMapper;
import com.ruoyi.mes.domain.MesInspection;
import com.ruoyi.mes.service.IMesInspectionService;

/**
 * 质量检验Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesInspectionServiceImpl implements IMesInspectionService
{
    @Autowired
    private MesInspectionMapper mesInspectionMapper;

    @Override
    public MesInspection selectMesInspectionById(Long inspectionId)
    {
        return mesInspectionMapper.selectMesInspectionById(inspectionId);
    }

    @Override
    public List<MesInspection> selectMesInspectionList(MesInspection mesInspection)
    {
        return mesInspectionMapper.selectMesInspectionList(mesInspection);
    }

    @Override
    public int insertMesInspection(MesInspection mesInspection)
    {
        return mesInspectionMapper.insertMesInspection(mesInspection);
    }

    @Override
    public int updateMesInspection(MesInspection mesInspection)
    {
        return mesInspectionMapper.updateMesInspection(mesInspection);
    }

    @Override
    public int deleteMesInspectionById(Long inspectionId)
    {
        return mesInspectionMapper.deleteMesInspectionById(inspectionId);
    }

    @Override
    public int deleteMesInspectionByIds(Long[] inspectionIds)
    {
        return mesInspectionMapper.deleteMesInspectionByIds(inspectionIds);
    }
}
