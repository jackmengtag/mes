package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesArrivalMapper;
import com.ruoyi.mes.domain.MesArrival;
import com.ruoyi.mes.service.IMesArrivalService;

/**
 * 采购到货Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesArrivalServiceImpl implements IMesArrivalService
{
    @Autowired
    private MesArrivalMapper mesArrivalMapper;

    @Override
    public MesArrival selectMesArrivalById(Long arrivalId)
    {
        return mesArrivalMapper.selectMesArrivalById(arrivalId);
    }

    @Override
    public List<MesArrival> selectMesArrivalList(MesArrival mesArrival)
    {
        return mesArrivalMapper.selectMesArrivalList(mesArrival);
    }

    @Override
    public int insertMesArrival(MesArrival mesArrival)
    {
        return mesArrivalMapper.insertMesArrival(mesArrival);
    }

    @Override
    public int updateMesArrival(MesArrival mesArrival)
    {
        return mesArrivalMapper.updateMesArrival(mesArrival);
    }

    @Override
    public int deleteMesArrivalById(Long arrivalId)
    {
        return mesArrivalMapper.deleteMesArrivalById(arrivalId);
    }

    @Override
    public int deleteMesArrivalByIds(Long[] arrivalIds)
    {
        return mesArrivalMapper.deleteMesArrivalByIds(arrivalIds);
    }
}
