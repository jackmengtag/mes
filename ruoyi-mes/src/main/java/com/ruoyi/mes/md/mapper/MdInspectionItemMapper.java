package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdInspectionItem;

/**
 * 检验项目Mapper接口
 *
 * @author ruoyi
 */
public interface MdInspectionItemMapper
{
    /**
     * 查询检验项目
     */
    public MdInspectionItem selectMdInspectionItemById(Long id);

    /**
     * 查询检验项目列表
     */
    public List<MdInspectionItem> selectMdInspectionItemList(MdInspectionItem mdInspectionItem);

    /**
     * 新增检验项目
     */
    public int insertMdInspectionItem(MdInspectionItem mdInspectionItem);

    /**
     * 修改检验项目
     */
    public int updateMdInspectionItem(MdInspectionItem mdInspectionItem);

    /**
     * 删除检验项目
     */
    public int deleteMdInspectionItemById(Long id);

    /**
     * 批量删除检验项目
     */
    public int deleteMdInspectionItemByIds(Long[] ids);
}
