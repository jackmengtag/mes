package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesInterface;

/**
 * 系统集成接口Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesInterfaceService
{
    public MesInterface selectMesInterfaceById(Long interfaceId);

    public List<MesInterface> selectMesInterfaceList(MesInterface mesInterface);

    public int insertMesInterface(MesInterface mesInterface);

    public int updateMesInterface(MesInterface mesInterface);

    public int deleteMesInterfaceById(Long interfaceId);

    public int deleteMesInterfaceByIds(Long[] interfaceIds);
}
