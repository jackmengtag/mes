package com.ruoyi.mes.ex.service;

import java.util.List;
import com.ruoyi.mes.ex.domain.ExWip;

/**
 * 在制品Service接口
 *
 * @author ruoyi
 */
public interface IExWipService
{
    /**
     * 查询在制品
     */
    public ExWip selectExWipById(Long id);

    /**
     * 查询在制品列表
     */
    public List<ExWip> selectExWipList(ExWip exWip);

    /**
     * 新增在制品
     */
    public int insertExWip(ExWip exWip);

    /**
     * 修改在制品
     */
    public int updateExWip(ExWip exWip);

    /**
     * 批量删除在制品
     */
    public int deleteExWipByIds(Long[] ids);

    /**
     * 删除在制品信息
     */
    public int deleteExWipById(Long id);
}
