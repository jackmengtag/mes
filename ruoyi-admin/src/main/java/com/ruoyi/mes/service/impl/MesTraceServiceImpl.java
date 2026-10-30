package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesTraceMapper;
import com.ruoyi.mes.domain.MesTrace;
import com.ruoyi.mes.service.IMesTraceService;

/**
 * 质量追溯Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesTraceServiceImpl implements IMesTraceService
{
    @Autowired
    private MesTraceMapper mesTraceMapper;

    @Override
    public MesTrace selectMesTraceById(Long traceId)
    {
        return mesTraceMapper.selectMesTraceById(traceId);
    }

    @Override
    public List<MesTrace> selectMesTraceList(MesTrace mesTrace)
    {
        return mesTraceMapper.selectMesTraceList(mesTrace);
    }

    @Override
    public int insertMesTrace(MesTrace mesTrace)
    {
        return mesTraceMapper.insertMesTrace(mesTrace);
    }

    @Override
    public int updateMesTrace(MesTrace mesTrace)
    {
        return mesTraceMapper.updateMesTrace(mesTrace);
    }

    @Override
    public int deleteMesTraceById(Long traceId)
    {
        return mesTraceMapper.deleteMesTraceById(traceId);
    }

    @Override
    public int deleteMesTraceByIds(Long[] traceIds)
    {
        return mesTraceMapper.deleteMesTraceByIds(traceIds);
    }
}
