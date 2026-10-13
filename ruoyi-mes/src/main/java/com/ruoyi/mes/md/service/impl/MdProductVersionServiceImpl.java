package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdProductVersionMapper;
import com.ruoyi.mes.md.domain.MdProductVersion;
import com.ruoyi.mes.md.service.IMdProductVersionService;

/**
 * 产品版本Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdProductVersionServiceImpl implements IMdProductVersionService
{
    @Autowired
    private MdProductVersionMapper mdProductVersionMapper;

    /**
     * 查询产品版本
     */
    @Override
    public MdProductVersion selectMdProductVersionById(Long id)
    {
        return mdProductVersionMapper.selectMdProductVersionById(id);
    }

    /**
     * 查询产品版本列表
     */
    @Override
    public List<MdProductVersion> selectMdProductVersionList(MdProductVersion mdProductVersion)
    {
        return mdProductVersionMapper.selectMdProductVersionList(mdProductVersion);
    }

    /**
     * 新增产品版本
     */
    @Override
    public int insertMdProductVersion(MdProductVersion mdProductVersion)
    {
        return mdProductVersionMapper.insertMdProductVersion(mdProductVersion);
    }

    /**
     * 修改产品版本
     */
    @Override
    public int updateMdProductVersion(MdProductVersion mdProductVersion)
    {
        return mdProductVersionMapper.updateMdProductVersion(mdProductVersion);
    }

    /**
     * 批量删除产品版本
     */
    @Override
    public int deleteMdProductVersionByIds(Long[] ids)
    {
        return mdProductVersionMapper.deleteMdProductVersionByIds(ids);
    }

    /**
     * 删除产品版本信息
     */
    @Override
    public int deleteMdProductVersionById(Long id)
    {
        return mdProductVersionMapper.deleteMdProductVersionById(id);
    }
}
