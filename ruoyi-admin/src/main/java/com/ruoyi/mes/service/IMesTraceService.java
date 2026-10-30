package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesTrace;

/**
 * 质量追溯Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesTraceService
{
    public MesTrace selectMesTraceById(Long traceId);

    public List<MesTrace> selectMesTraceList(MesTrace mesTrace);

    public int insertMesTrace(MesTrace mesTrace);

    public int updateMesTrace(MesTrace mesTrace);

    public int deleteMesTraceById(Long traceId);

    public int deleteMesTraceByIds(Long[] traceIds);
}
