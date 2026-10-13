package com.ruoyi.mes.ex.mapper;

import java.util.List;
import com.ruoyi.mes.ex.domain.ExWorkHour;

/**
 * 工时记录Mapper接口
 *
 * @author ruoyi
 */
public interface ExWorkHourMapper
{
    /**
     * 查询工时记录
     */
    public ExWorkHour selectExWorkHourById(Long id);

    /**
     * 查询工时记录列表
     */
    public List<ExWorkHour> selectExWorkHourList(ExWorkHour exWorkHour);

    /**
     * 新增工时记录
     */
    public int insertExWorkHour(ExWorkHour exWorkHour);

    /**
     * 修改工时记录
     */
    public int updateExWorkHour(ExWorkHour exWorkHour);

    /**
     * 删除工时记录
     */
    public int deleteExWorkHourById(Long id);

    /**
     * 批量删除工时记录
     */
    public int deleteExWorkHourByIds(Long[] ids);
}
