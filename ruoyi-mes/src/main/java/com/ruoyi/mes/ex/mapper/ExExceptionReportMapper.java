package com.ruoyi.mes.ex.mapper;

import java.util.List;
import com.ruoyi.mes.ex.domain.ExExceptionReport;

/**
 * 现场异常上报Mapper接口
 *
 * @author ruoyi
 */
public interface ExExceptionReportMapper
{
    /**
     * 查询现场异常上报
     */
    public ExExceptionReport selectExExceptionReportById(Long id);

    /**
     * 查询现场异常上报列表
     */
    public List<ExExceptionReport> selectExExceptionReportList(ExExceptionReport exExceptionReport);

    /**
     * 新增现场异常上报
     */
    public int insertExExceptionReport(ExExceptionReport exExceptionReport);

    /**
     * 修改现场异常上报
     */
    public int updateExExceptionReport(ExExceptionReport exExceptionReport);

    /**
     * 删除现场异常上报
     */
    public int deleteExExceptionReportById(Long id);

    /**
     * 批量删除现场异常上报
     */
    public int deleteExExceptionReportByIds(Long[] ids);
}
