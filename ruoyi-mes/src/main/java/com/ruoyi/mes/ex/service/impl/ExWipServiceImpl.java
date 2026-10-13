package com.ruoyi.mes.ex.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.ex.mapper.ExWipMapper;
import com.ruoyi.mes.ex.domain.ExWip;
import com.ruoyi.mes.ex.service.IExWipService;

/**
 * 在制品Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class ExWipServiceImpl implements IExWipService
{
    @Autowired
    private ExWipMapper exWipMapper;

    /**
     * 查询在制品
     */
    @Override
    public ExWip selectExWipById(Long id)
    {
        return exWipMapper.selectExWipById(id);
    }

    /**
     * 查询在制品列表
     */
    @Override
    public List<ExWip> selectExWipList(ExWip exWip)
    {
        return exWipMapper.selectExWipList(exWip);
    }

    /**
     * 新增在制品
     */
    @Override
    public int insertExWip(ExWip exWip)
    {
        return exWipMapper.insertExWip(exWip);
    }

    /**
     * 修改在制品
     */
    @Override
    public int updateExWip(ExWip exWip)
    {
        return exWipMapper.updateExWip(exWip);
    }

    /**
     * 批量删除在制品
     */
    @Override
    public int deleteExWipByIds(Long[] ids)
    {
        return exWipMapper.deleteExWipByIds(ids);
    }

    /**
     * 删除在制品信息
     */
    @Override
    public int deleteExWipById(Long id)
    {
        return exWipMapper.deleteExWipById(id);
    }
}
