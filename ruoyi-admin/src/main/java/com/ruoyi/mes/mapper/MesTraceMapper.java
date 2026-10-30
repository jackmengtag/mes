package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesTrace;

/**
 * 质量追溯Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesTraceMapper
{
    public MesTrace selectMesTraceById(Long traceId);

    public List<MesTrace> selectMesTraceList(MesTrace mesTrace);

    public int insertMesTrace(MesTrace mesTrace);

    public int updateMesTrace(MesTrace mesTrace);

    public int deleteMesTraceById(Long traceId);

    public int deleteMesTraceByIds(Long[] traceIds);
}
