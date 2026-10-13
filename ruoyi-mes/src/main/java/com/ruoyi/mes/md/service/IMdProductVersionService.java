package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdProductVersion;

/**
 * 产品版本Service接口
 *
 * @author ruoyi
 */
public interface IMdProductVersionService
{
    /**
     * 查询产品版本
     */
    public MdProductVersion selectMdProductVersionById(Long id);

    /**
     * 查询产品版本列表
     */
    public List<MdProductVersion> selectMdProductVersionList(MdProductVersion mdProductVersion);

    /**
     * 新增产品版本
     */
    public int insertMdProductVersion(MdProductVersion mdProductVersion);

    /**
     * 修改产品版本
     */
    public int updateMdProductVersion(MdProductVersion mdProductVersion);

    /**
     * 批量删除产品版本
     */
    public int deleteMdProductVersionByIds(Long[] ids);

    /**
     * 删除产品版本信息
     */
    public int deleteMdProductVersionById(Long id);
}
