package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdProductVersion;

/**
 * 产品版本Mapper接口
 *
 * @author ruoyi
 */
public interface MdProductVersionMapper
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
     * 删除产品版本
     */
    public int deleteMdProductVersionById(Long id);

    /**
     * 批量删除产品版本
     */
    public int deleteMdProductVersionByIds(Long[] ids);
}
