package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdLineMapper;
import com.ruoyi.mes.md.domain.MdLine;
import com.ruoyi.mes.md.service.IMdLineService;

/**
 * 产线Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdLineServiceImpl implements IMdLineService
{
    @Autowired
    private MdLineMapper mdLineMapper;

    /**
     * 查询产线
     */
    @Override
    public MdLine selectMdLineById(Long id)
    {
        return mdLineMapper.selectMdLineById(id);
    }

    /**
     * 查询产线列表
     */
    @Override
    public List<MdLine> selectMdLineList(MdLine mdLine)
    {
        return mdLineMapper.selectMdLineList(mdLine);
    }

    /**
     * 新增产线
     */
    @Override
    public int insertMdLine(MdLine mdLine)
    {
        return mdLineMapper.insertMdLine(mdLine);
    }

    /**
     * 修改产线
     */
    @Override
    public int updateMdLine(MdLine mdLine)
    {
        return mdLineMapper.updateMdLine(mdLine);
    }

    /**
     * 批量删除产线
     */
    @Override
    public int deleteMdLineByIds(Long[] ids)
    {
        return mdLineMapper.deleteMdLineByIds(ids);
    }

    /**
     * 删除产线信息
     */
    @Override
    public int deleteMdLineById(Long id)
    {
        return mdLineMapper.deleteMdLineById(id);
    }
}
