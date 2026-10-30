package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesReportMapper;
import com.ruoyi.mes.domain.MesReport;
import com.ruoyi.mes.service.IMesReportService;

/**
 * 生产报工Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesReportServiceImpl implements IMesReportService
{
    @Autowired
    private MesReportMapper mesReportMapper;

    @Override
    public MesReport selectMesReportById(Long reportId)
    {
        return mesReportMapper.selectMesReportById(reportId);
    }

    @Override
    public List<MesReport> selectMesReportList(MesReport mesReport)
    {
        return mesReportMapper.selectMesReportList(mesReport);
    }

    @Override
    public int insertMesReport(MesReport mesReport)
    {
        return mesReportMapper.insertMesReport(mesReport);
    }

    @Override
    public int updateMesReport(MesReport mesReport)
    {
        return mesReportMapper.updateMesReport(mesReport);
    }

    @Override
    public int deleteMesReportById(Long reportId)
    {
        return mesReportMapper.deleteMesReportById(reportId);
    }

    @Override
    public int deleteMesReportByIds(Long[] reportIds)
    {
        return mesReportMapper.deleteMesReportByIds(reportIds);
    }
}
