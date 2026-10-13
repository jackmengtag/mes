package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdWorkCenter;

/**
 * 工作中心Mapper接口
 *
 * @author ruoyi
 */
public interface MdWorkCenterMapper
{
    /**
     * 查询工作中心
     */
    public MdWorkCenter selectMdWorkCenterById(Long id);

    /**
     * 查询工作中心列表
     */
    public List<MdWorkCenter> selectMdWorkCenterList(MdWorkCenter mdWorkCenter);

    /**
     * 新增工作中心
     */
    public int insertMdWorkCenter(MdWorkCenter mdWorkCenter);

    /**
     * 修改工作中心
     */
    public int updateMdWorkCenter(MdWorkCenter mdWorkCenter);

    /**
     * 删除工作中心
     */
    public int deleteMdWorkCenterById(Long id);

    /**
     * 批量删除工作中心
     */
    public int deleteMdWorkCenterByIds(Long[] ids);
}
