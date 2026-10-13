package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdBom;

/**
 * BOM主数据Service接口
 *
 * @author ruoyi
 */
public interface IMdBomService
{
    /**
     * 查询BOM主数据
     */
    public MdBom selectMdBomById(Long id);

    /**
     * 查询BOM主数据列表
     */
    public List<MdBom> selectMdBomList(MdBom mdBom);

    /**
     * 新增BOM主数据
     */
    public int insertMdBom(MdBom mdBom);

    /**
     * 修改BOM主数据
     */
    public int updateMdBom(MdBom mdBom);

    /**
     * 批量删除BOM主数据
     */
    public int deleteMdBomByIds(Long[] ids);

    /**
     * 删除BOM主数据信息
     */
    public int deleteMdBomById(Long id);
}
