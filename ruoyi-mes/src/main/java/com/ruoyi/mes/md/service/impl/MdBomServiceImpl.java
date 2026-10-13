package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdBomMapper;
import com.ruoyi.mes.md.domain.MdBom;
import com.ruoyi.mes.md.service.IMdBomService;

/**
 * BOM主数据Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdBomServiceImpl implements IMdBomService
{
    @Autowired
    private MdBomMapper mdBomMapper;

    /**
     * 查询BOM主数据
     */
    @Override
    public MdBom selectMdBomById(Long id)
    {
        return mdBomMapper.selectMdBomById(id);
    }

    /**
     * 查询BOM主数据列表
     */
    @Override
    public List<MdBom> selectMdBomList(MdBom mdBom)
    {
        return mdBomMapper.selectMdBomList(mdBom);
    }

    /**
     * 新增BOM主数据
     */
    @Override
    public int insertMdBom(MdBom mdBom)
    {
        return mdBomMapper.insertMdBom(mdBom);
    }

    /**
     * 修改BOM主数据
     */
    @Override
    public int updateMdBom(MdBom mdBom)
    {
        return mdBomMapper.updateMdBom(mdBom);
    }

    /**
     * 批量删除BOM主数据
     */
    @Override
    public int deleteMdBomByIds(Long[] ids)
    {
        return mdBomMapper.deleteMdBomByIds(ids);
    }

    /**
     * 删除BOM主数据信息
     */
    @Override
    public int deleteMdBomById(Long id)
    {
        return mdBomMapper.deleteMdBomById(id);
    }
}
