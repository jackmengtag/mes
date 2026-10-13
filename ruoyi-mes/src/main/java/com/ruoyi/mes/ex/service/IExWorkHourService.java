package com.ruoyi.mes.ex.service;

import java.util.List;
import com.ruoyi.mes.ex.domain.ExWorkHour;

/**
 * 工时记录Service接口
 *
 * @author ruoyi
 */
public interface IExWorkHourService
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
     * 批量删除工时记录
     */
    public int deleteExWorkHourByIds(Long[] ids);

    /**
     * 删除工时记录信息
     */
    public int deleteExWorkHourById(Long id);
}
