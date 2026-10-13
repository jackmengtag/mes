package com.ruoyi.mes.ex.service;

import java.util.List;
import com.ruoyi.mes.ex.domain.ExProcessParam;

/**
 * 工艺参数采集Service接口
 *
 * @author ruoyi
 */
public interface IExProcessParamService
{
    /**
     * 查询工艺参数采集
     */
    public ExProcessParam selectExProcessParamById(Long id);

    /**
     * 查询工艺参数采集列表
     */
    public List<ExProcessParam> selectExProcessParamList(ExProcessParam exProcessParam);

    /**
     * 新增工艺参数采集
     */
    public int insertExProcessParam(ExProcessParam exProcessParam);

    /**
     * 修改工艺参数采集
     */
    public int updateExProcessParam(ExProcessParam exProcessParam);

    /**
     * 批量删除工艺参数采集
     */
    public int deleteExProcessParamByIds(Long[] ids);

    /**
     * 删除工艺参数采集信息
     */
    public int deleteExProcessParamById(Long id);
}
