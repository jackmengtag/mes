package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdBomLineMapper;
import com.ruoyi.mes.md.domain.MdBomLine;
import com.ruoyi.mes.md.service.IMdBomLineService;

/**
 * BOM明细Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdBomLineServiceImpl implements IMdBomLineService
{
    @Autowired
    private MdBomLineMapper mdBomLineMapper;

    /**
     * 查询BOM明细
     */
    @Override
    public MdBomLine selectMdBomLineById(Long id)
    {
        return mdBomLineMapper.selectMdBomLineById(id);
    }

    /**
     * 查询BOM明细列表
     */
    @Override
    public List<MdBomLine> selectMdBomLineList(MdBomLine mdBomLine)
    {
        return mdBomLineMapper.selectMdBomLineList(mdBomLine);
    }

    /**
     * 新增BOM明细
     */
    @Override
    public int insertMdBomLine(MdBomLine mdBomLine)
    {
        return mdBomLineMapper.insertMdBomLine(mdBomLine);
    }

    /**
     * 修改BOM明细
     */
    @Override
    public int updateMdBomLine(MdBomLine mdBomLine)
    {
        return mdBomLineMapper.updateMdBomLine(mdBomLine);
    }

    /**
     * 批量删除BOM明细
     */
    @Override
    public int deleteMdBomLineByIds(Long[] ids)
    {
        return mdBomLineMapper.deleteMdBomLineByIds(ids);
    }

    /**
     * 删除BOM明细信息
     */
    @Override
    public int deleteMdBomLineById(Long id)
    {
        return mdBomLineMapper.deleteMdBomLineById(id);
    }
}
