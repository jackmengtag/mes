package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdShiftMapper;
import com.ruoyi.mes.md.domain.MdShift;
import com.ruoyi.mes.md.service.IMdShiftService;

/**
 * 班次Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdShiftServiceImpl implements IMdShiftService
{
    @Autowired
    private MdShiftMapper mdShiftMapper;

    /**
     * 查询班次
     */
    @Override
    public MdShift selectMdShiftById(Long id)
    {
        return mdShiftMapper.selectMdShiftById(id);
    }

    /**
     * 查询班次列表
     */
    @Override
    public List<MdShift> selectMdShiftList(MdShift mdShift)
    {
        return mdShiftMapper.selectMdShiftList(mdShift);
    }

    /**
     * 新增班次
     */
    @Override
    public int insertMdShift(MdShift mdShift)
    {
        return mdShiftMapper.insertMdShift(mdShift);
    }

    /**
     * 修改班次
     */
    @Override
    public int updateMdShift(MdShift mdShift)
    {
        return mdShiftMapper.updateMdShift(mdShift);
    }

    /**
     * 批量删除班次
     */
    @Override
    public int deleteMdShiftByIds(Long[] ids)
    {
        return mdShiftMapper.deleteMdShiftByIds(ids);
    }

    /**
     * 删除班次信息
     */
    @Override
    public int deleteMdShiftById(Long id)
    {
        return mdShiftMapper.deleteMdShiftById(id);
    }
}
