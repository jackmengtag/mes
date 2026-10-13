package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdStation;

/**
 * 工位Mapper接口
 *
 * @author ruoyi
 */
public interface MdStationMapper
{
    /**
     * 查询工位
     */
    public MdStation selectMdStationById(Long id);

    /**
     * 查询工位列表
     */
    public List<MdStation> selectMdStationList(MdStation mdStation);

    /**
     * 新增工位
     */
    public int insertMdStation(MdStation mdStation);

    /**
     * 修改工位
     */
    public int updateMdStation(MdStation mdStation);

    /**
     * 删除工位
     */
    public int deleteMdStationById(Long id);

    /**
     * 批量删除工位
     */
    public int deleteMdStationByIds(Long[] ids);
}
