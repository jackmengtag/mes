package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesCollab;

/**
 * 供应链协同Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesCollabMapper
{
    public MesCollab selectMesCollabById(Long collabId);

    public List<MesCollab> selectMesCollabList(MesCollab mesCollab);

    public int insertMesCollab(MesCollab mesCollab);

    public int updateMesCollab(MesCollab mesCollab);

    public int deleteMesCollabById(Long collabId);

    public int deleteMesCollabByIds(Long[] collabIds);
}
