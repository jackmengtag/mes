package com.ruoyi.mes.ex.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.ex.mapper.ExWorkHourMapper;
import com.ruoyi.mes.ex.domain.ExWorkHour;
import com.ruoyi.mes.ex.service.IExWorkHourService;

/**
 * 工时记录Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class ExWorkHourServiceImpl implements IExWorkHourService
{
    @Autowired
    private ExWorkHourMapper exWorkHourMapper;

    /**
     * 查询工时记录
     */
    @Override
    public ExWorkHour selectExWorkHourById(Long id)
    {
        return exWorkHourMapper.selectExWorkHourById(id);
    }

    /**
     * 查询工时记录列表
     */
    @Override
    public List<ExWorkHour> selectExWorkHourList(ExWorkHour exWorkHour)
    {
        return exWorkHourMapper.selectExWorkHourList(exWorkHour);
    }

    /**
     * 新增工时记录
     */
    @Override
    public int insertExWorkHour(ExWorkHour exWorkHour)
    {
        return exWorkHourMapper.insertExWorkHour(exWorkHour);
    }

    /**
     * 修改工时记录
     */
    @Override
    public int updateExWorkHour(ExWorkHour exWorkHour)
    {
        return exWorkHourMapper.updateExWorkHour(exWorkHour);
    }

    /**
     * 批量删除工时记录
     */
    @Override
    public int deleteExWorkHourByIds(Long[] ids)
    {
        return exWorkHourMapper.deleteExWorkHourByIds(ids);
    }

    /**
     * 删除工时记录信息
     */
    @Override
    public int deleteExWorkHourById(Long id)
    {
        return exWorkHourMapper.deleteExWorkHourById(id);
    }
}
