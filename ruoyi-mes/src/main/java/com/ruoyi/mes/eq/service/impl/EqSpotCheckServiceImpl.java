package com.ruoyi.mes.eq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.eq.mapper.EqSpotCheckMapper;
import com.ruoyi.mes.eq.domain.EqSpotCheck;
import com.ruoyi.mes.eq.service.IEqSpotCheckService;

/**
 * 设备点检Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class EqSpotCheckServiceImpl implements IEqSpotCheckService
{
    @Autowired
    private EqSpotCheckMapper eqSpotCheckMapper;

    /**
     * 查询设备点检
     */
    @Override
    public EqSpotCheck selectEqSpotCheckById(Long id)
    {
        return eqSpotCheckMapper.selectEqSpotCheckById(id);
    }

    /**
     * 查询设备点检列表
     */
    @Override
    public List<EqSpotCheck> selectEqSpotCheckList(EqSpotCheck eqSpotCheck)
    {
        return eqSpotCheckMapper.selectEqSpotCheckList(eqSpotCheck);
    }

    /**
     * 新增设备点检
     */
    @Override
    public int insertEqSpotCheck(EqSpotCheck eqSpotCheck)
    {
        return eqSpotCheckMapper.insertEqSpotCheck(eqSpotCheck);
    }

    /**
     * 修改设备点检
     */
    @Override
    public int updateEqSpotCheck(EqSpotCheck eqSpotCheck)
    {
        return eqSpotCheckMapper.updateEqSpotCheck(eqSpotCheck);
    }

    /**
     * 批量删除设备点检
     */
    @Override
    public int deleteEqSpotCheckByIds(Long[] ids)
    {
        return eqSpotCheckMapper.deleteEqSpotCheckByIds(ids);
    }

    /**
     * 删除设备点检信息
     */
    @Override
    public int deleteEqSpotCheckById(Long id)
    {
        return eqSpotCheckMapper.deleteEqSpotCheckById(id);
    }
}
