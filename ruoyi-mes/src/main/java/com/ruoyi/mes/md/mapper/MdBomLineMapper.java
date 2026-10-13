package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdBomLine;

/**
 * BOM明细Mapper接口
 *
 * @author ruoyi
 */
public interface MdBomLineMapper
{
    /**
     * 查询BOM明细
     */
    public MdBomLine selectMdBomLineById(Long id);

    /**
     * 查询BOM明细列表
     */
    public List<MdBomLine> selectMdBomLineList(MdBomLine mdBomLine);

    /**
     * 新增BOM明细
     */
    public int insertMdBomLine(MdBomLine mdBomLine);

    /**
     * 修改BOM明细
     */
    public int updateMdBomLine(MdBomLine mdBomLine);

    /**
     * 删除BOM明细
     */
    public int deleteMdBomLineById(Long id);

    /**
     * 批量删除BOM明细
     */
    public int deleteMdBomLineByIds(Long[] ids);
}
