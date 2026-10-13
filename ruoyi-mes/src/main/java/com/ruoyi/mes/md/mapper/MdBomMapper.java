package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdBom;

/**
 * BOM主数据Mapper接口
 *
 * @author ruoyi
 */
public interface MdBomMapper
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
     * 删除BOM主数据
     */
    public int deleteMdBomById(Long id);

    /**
     * 批量删除BOM主数据
     */
    public int deleteMdBomByIds(Long[] ids);
}
