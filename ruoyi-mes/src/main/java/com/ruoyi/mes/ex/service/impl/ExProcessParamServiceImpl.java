package com.ruoyi.mes.ex.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.ex.mapper.ExProcessParamMapper;
import com.ruoyi.mes.ex.domain.ExProcessParam;
import com.ruoyi.mes.ex.service.IExProcessParamService;

/**
 * 工艺参数采集Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class ExProcessParamServiceImpl implements IExProcessParamService
{
    @Autowired
    private ExProcessParamMapper exProcessParamMapper;

    /**
     * 查询工艺参数采集
     */
    @Override
    public ExProcessParam selectExProcessParamById(Long id)
    {
        return exProcessParamMapper.selectExProcessParamById(id);
    }

    /**
     * 查询工艺参数采集列表
     */
    @Override
    public List<ExProcessParam> selectExProcessParamList(ExProcessParam exProcessParam)
    {
        return exProcessParamMapper.selectExProcessParamList(exProcessParam);
    }

    /**
     * 新增工艺参数采集
     */
    @Override
    public int insertExProcessParam(ExProcessParam exProcessParam)
    {
        return exProcessParamMapper.insertExProcessParam(exProcessParam);
    }

    /**
     * 修改工艺参数采集
     */
    @Override
    public int updateExProcessParam(ExProcessParam exProcessParam)
    {
        return exProcessParamMapper.updateExProcessParam(exProcessParam);
    }

    /**
     * 批量删除工艺参数采集
     */
    @Override
    public int deleteExProcessParamByIds(Long[] ids)
    {
        return exProcessParamMapper.deleteExProcessParamByIds(ids);
    }

    /**
     * 删除工艺参数采集信息
     */
    @Override
    public int deleteExProcessParamById(Long id)
    {
        return exProcessParamMapper.deleteExProcessParamById(id);
    }
}
