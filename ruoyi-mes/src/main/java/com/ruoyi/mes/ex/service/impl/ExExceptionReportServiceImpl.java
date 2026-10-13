package com.ruoyi.mes.ex.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.ex.mapper.ExExceptionReportMapper;
import com.ruoyi.mes.ex.domain.ExExceptionReport;
import com.ruoyi.mes.ex.service.IExExceptionReportService;

/**
 * 现场异常上报Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class ExExceptionReportServiceImpl implements IExExceptionReportService
{
    @Autowired
    private ExExceptionReportMapper exExceptionReportMapper;

    /**
     * 查询现场异常上报
     */
    @Override
    public ExExceptionReport selectExExceptionReportById(Long id)
    {
        return exExceptionReportMapper.selectExExceptionReportById(id);
    }

    /**
     * 查询现场异常上报列表
     */
    @Override
    public List<ExExceptionReport> selectExExceptionReportList(ExExceptionReport exExceptionReport)
    {
        return exExceptionReportMapper.selectExExceptionReportList(exExceptionReport);
    }

    /**
     * 新增现场异常上报
     */
    @Override
    public int insertExExceptionReport(ExExceptionReport exExceptionReport)
    {
        return exExceptionReportMapper.insertExExceptionReport(exExceptionReport);
    }

    /**
     * 修改现场异常上报
     */
    @Override
    public int updateExExceptionReport(ExExceptionReport exExceptionReport)
    {
        return exExceptionReportMapper.updateExExceptionReport(exExceptionReport);
    }

    /**
     * 批量删除现场异常上报
     */
    @Override
    public int deleteExExceptionReportByIds(Long[] ids)
    {
        return exExceptionReportMapper.deleteExExceptionReportByIds(ids);
    }

    /**
     * 删除现场异常上报信息
     */
    @Override
    public int deleteExExceptionReportById(Long id)
    {
        return exExceptionReportMapper.deleteExExceptionReportById(id);
    }
}
