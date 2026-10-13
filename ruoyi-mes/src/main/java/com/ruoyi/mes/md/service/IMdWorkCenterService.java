package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdWorkCenter;

/**
 * 工作中心Service接口
 *
 * @author ruoyi
 */
public interface IMdWorkCenterService
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
     * 批量删除工作中心
     */
    public int deleteMdWorkCenterByIds(Long[] ids);

    /**
     * 删除工作中心信息
     */
    public int deleteMdWorkCenterById(Long id);
}
