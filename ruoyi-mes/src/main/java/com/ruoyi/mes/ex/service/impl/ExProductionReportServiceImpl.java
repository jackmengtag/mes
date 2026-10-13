package com.ruoyi.mes.ex.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.ex.mapper.ExProductionReportMapper;
import com.ruoyi.mes.ex.domain.ExProductionReport;
import com.ruoyi.mes.ex.service.IExProductionReportService;

/**
 * 报工记录Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class ExProductionReportServiceImpl implements IExProductionReportService
{
    @Autowired
    private ExProductionReportMapper exProductionReportMapper;

    /**
     * 查询报工记录
     */
    @Override
    public ExProductionReport selectExProductionReportById(Long id)
    {
        return exProductionReportMapper.selectExProductionReportById(id);
    }

    /**
     * 查询报工记录列表
     */
    @Override
    public List<ExProductionReport> selectExProductionReportList(ExProductionReport exProductionReport)
    {
        return exProductionReportMapper.selectExProductionReportList(exProductionReport);
    }

    /**
     * 新增报工记录
     */
    @Override
    public int insertExProductionReport(ExProductionReport exProductionReport)
    {
        return exProductionReportMapper.insertExProductionReport(exProductionReport);
    }

    /**
     * 修改报工记录
     */
    @Override
    public int updateExProductionReport(ExProductionReport exProductionReport)
    {
        return exProductionReportMapper.updateExProductionReport(exProductionReport);
    }

    /**
     * 批量删除报工记录
     */
    @Override
    public int deleteExProductionReportByIds(Long[] ids)
    {
        return exProductionReportMapper.deleteExProductionReportByIds(ids);
    }

    /**
     * 删除报工记录信息
     */
    @Override
    public int deleteExProductionReportById(Long id)
    {
        return exProductionReportMapper.deleteExProductionReportById(id);
    }
}
