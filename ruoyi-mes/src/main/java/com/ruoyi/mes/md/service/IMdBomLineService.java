package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdBomLine;

/**
 * BOM明细Service接口
 *
 * @author ruoyi
 */
public interface IMdBomLineService
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
     * 批量删除BOM明细
     */
    public int deleteMdBomLineByIds(Long[] ids);

    /**
     * 删除BOM明细信息
     */
    public int deleteMdBomLineById(Long id);
}
