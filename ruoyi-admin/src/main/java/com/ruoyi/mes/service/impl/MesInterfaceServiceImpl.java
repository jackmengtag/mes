package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesInterfaceMapper;
import com.ruoyi.mes.domain.MesInterface;
import com.ruoyi.mes.service.IMesInterfaceService;

/**
 * 系统集成接口Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesInterfaceServiceImpl implements IMesInterfaceService
{
    @Autowired
    private MesInterfaceMapper mesInterfaceMapper;

    @Override
    public MesInterface selectMesInterfaceById(Long interfaceId)
    {
        return mesInterfaceMapper.selectMesInterfaceById(interfaceId);
    }

    @Override
    public List<MesInterface> selectMesInterfaceList(MesInterface mesInterface)
    {
        return mesInterfaceMapper.selectMesInterfaceList(mesInterface);
    }

    @Override
    public int insertMesInterface(MesInterface mesInterface)
    {
        return mesInterfaceMapper.insertMesInterface(mesInterface);
    }

    @Override
    public int updateMesInterface(MesInterface mesInterface)
    {
        return mesInterfaceMapper.updateMesInterface(mesInterface);
    }

    @Override
    public int deleteMesInterfaceById(Long interfaceId)
    {
        return mesInterfaceMapper.deleteMesInterfaceById(interfaceId);
    }

    @Override
    public int deleteMesInterfaceByIds(Long[] interfaceIds)
    {
        return mesInterfaceMapper.deleteMesInterfaceByIds(interfaceIds);
    }
}
