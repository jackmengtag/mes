package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesReport;

/**
 * 生产报工Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesReportService
{
    public MesReport selectMesReportById(Long reportId);

    public List<MesReport> selectMesReportList(MesReport mesReport);

    public int insertMesReport(MesReport mesReport);

    public int updateMesReport(MesReport mesReport);

    public int deleteMesReportById(Long reportId);

    public int deleteMesReportByIds(Long[] reportIds);
}
