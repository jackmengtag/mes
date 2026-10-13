package com.ruoyi.mes.eq.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.eq.mapper.EqOeeMapper;
import com.ruoyi.mes.eq.domain.EqOee;
import com.ruoyi.mes.eq.service.IEqOeeService;

/**
 * OEEService业务层处理
 *
 * @author ruoyi
 */
@Service
public class EqOeeServiceImpl implements IEqOeeService
{
    @Autowired
    private EqOeeMapper eqOeeMapper;

    /**
     * 查询OEE
     */
    @Override
    public EqOee selectEqOeeById(Long id)
    {
        return eqOeeMapper.selectEqOeeById(id);
    }

    /**
     * 查询OEE列表
     */
    @Override
    public List<EqOee> selectEqOeeList(EqOee eqOee)
    {
        return eqOeeMapper.selectEqOeeList(eqOee);
    }

    /**
     * 新增OEE
     */
    @Override
    public int insertEqOee(EqOee eqOee)
    {
        return eqOeeMapper.insertEqOee(eqOee);
    }

    /**
     * 修改OEE
     */
    @Override
    public int updateEqOee(EqOee eqOee)
    {
        return eqOeeMapper.updateEqOee(eqOee);
    }

    /**
     * 批量删除OEE
     */
    @Override
    public int deleteEqOeeByIds(Long[] ids)
    {
        return eqOeeMapper.deleteEqOeeByIds(ids);
    }

    /**
     * 删除OEE信息
     */
    @Override
    public int deleteEqOeeById(Long id)
    {
        return eqOeeMapper.deleteEqOeeById(id);
    }
}
