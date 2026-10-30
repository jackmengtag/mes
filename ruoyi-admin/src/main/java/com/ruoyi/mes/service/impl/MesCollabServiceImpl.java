package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesCollabMapper;
import com.ruoyi.mes.domain.MesCollab;
import com.ruoyi.mes.service.IMesCollabService;

/**
 * 供应链协同Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesCollabServiceImpl implements IMesCollabService
{
    @Autowired
    private MesCollabMapper mesCollabMapper;

    @Override
    public MesCollab selectMesCollabById(Long collabId)
    {
        return mesCollabMapper.selectMesCollabById(collabId);
    }

    @Override
    public List<MesCollab> selectMesCollabList(MesCollab mesCollab)
    {
        return mesCollabMapper.selectMesCollabList(mesCollab);
    }

    @Override
    public int insertMesCollab(MesCollab mesCollab)
    {
        return mesCollabMapper.insertMesCollab(mesCollab);
    }

    @Override
    public int updateMesCollab(MesCollab mesCollab)
    {
        return mesCollabMapper.updateMesCollab(mesCollab);
    }

    @Override
    public int deleteMesCollabById(Long collabId)
    {
        return mesCollabMapper.deleteMesCollabById(collabId);
    }

    @Override
    public int deleteMesCollabByIds(Long[] collabIds)
    {
        return mesCollabMapper.deleteMesCollabByIds(collabIds);
    }
}
