package com.ruoyi.mes.ex.mapper;

import java.util.List;
import com.ruoyi.mes.ex.domain.ExWip;

/**
 * 在制品Mapper接口
 *
 * @author ruoyi
 */
public interface ExWipMapper
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
     * 删除在制品
     */
    public int deleteExWipById(Long id);

    /**
     * 批量删除在制品
     */
    public int deleteExWipByIds(Long[] ids);
}
