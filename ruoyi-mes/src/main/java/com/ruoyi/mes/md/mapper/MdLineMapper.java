package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdLine;

/**
 * 产线Mapper接口
 *
 * @author ruoyi
 */
public interface MdLineMapper
{
    /**
     * 查询产线
     */
    public MdLine selectMdLineById(Long id);

    /**
     * 查询产线列表
     */
    public List<MdLine> selectMdLineList(MdLine mdLine);

    /**
     * 新增产线
     */
    public int insertMdLine(MdLine mdLine);

    /**
     * 修改产线
     */
    public int updateMdLine(MdLine mdLine);

    /**
     * 删除产线
     */
    public int deleteMdLineById(Long id);

    /**
     * 批量删除产线
     */
    public int deleteMdLineByIds(Long[] ids);
}
