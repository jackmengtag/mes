package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesArrival;

/**
 * 采购到货Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesArrivalMapper
{
    public MesArrival selectMesArrivalById(Long arrivalId);

    public List<MesArrival> selectMesArrivalList(MesArrival mesArrival);

    public int insertMesArrival(MesArrival mesArrival);

    public int updateMesArrival(MesArrival mesArrival);

    public int deleteMesArrivalById(Long arrivalId);

    public int deleteMesArrivalByIds(Long[] arrivalIds);
}
