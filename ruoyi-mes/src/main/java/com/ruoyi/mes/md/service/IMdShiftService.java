package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdShift;

/**
 * 班次Service接口
 *
 * @author ruoyi
 */
public interface IMdShiftService
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
     * 批量删除班次
     */
    public int deleteMdShiftByIds(Long[] ids);

    /**
     * 删除班次信息
     */
    public int deleteMdShiftById(Long id);
}
