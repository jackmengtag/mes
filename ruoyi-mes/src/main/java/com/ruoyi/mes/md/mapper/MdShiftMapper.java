package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdShift;

/**
 * 班次Mapper接口
 *
 * @author ruoyi
 */
public interface MdShiftMapper
{
    /**
     * 查询班次
     */
    public MdShift selectMdShiftById(Long id);

    /**
     * 查询班次列表
     */
    public List<MdShift> selectMdShiftList(MdShift mdShift);

    /**
     * 新增班次
     */
    public int insertMdShift(MdShift mdShift);

    /**
     * 修改班次
     */
    public int updateMdShift(MdShift mdShift);

    /**
     * 删除班次
     */
    public int deleteMdShiftById(Long id);

    /**
     * 批量删除班次
     */
    public int deleteMdShiftByIds(Long[] ids);
}
