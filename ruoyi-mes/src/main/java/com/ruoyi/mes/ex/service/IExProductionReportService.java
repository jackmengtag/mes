package com.ruoyi.mes.ex.service;

import java.util.List;
import com.ruoyi.mes.ex.domain.ExProductionReport;

/**
 * 报工记录Service接口
 *
 * @author ruoyi
 */
public interface IExProductionReportService
{
    /**
     * 查询报工记录
     */
    public ExProductionReport selectExProductionReportById(Long id);

    /**
     * 查询报工记录列表
     */
    public List<ExProductionReport> selectExProductionReportList(ExProductionReport exProductionReport);

    /**
     * 新增报工记录
     */
    public int insertExProductionReport(ExProductionReport exProductionReport);

    /**
     * 修改报工记录
     */
    public int updateExProductionReport(ExProductionReport exProductionReport);

    /**
     * 批量删除报工记录
     */
    public int deleteExProductionReportByIds(Long[] ids);

    /**
     * 删除报工记录信息
     */
    public int deleteExProductionReportById(Long id);
}
