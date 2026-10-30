package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesInspection;

/**
 * 质量检验Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesInspectionService
{
    public MesInspection selectMesInspectionById(Long inspectionId);

    public List<MesInspection> selectMesInspectionList(MesInspection mesInspection);

    public int insertMesInspection(MesInspection mesInspection);

    public int updateMesInspection(MesInspection mesInspection);

    public int deleteMesInspectionById(Long inspectionId);

    public int deleteMesInspectionByIds(Long[] inspectionIds);
}
