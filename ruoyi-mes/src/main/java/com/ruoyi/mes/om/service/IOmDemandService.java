package com.ruoyi.mes.om.service;

import java.util.List;
import com.ruoyi.mes.om.domain.OmDemand;

/**
 * 生产需求Service接口
 *
 * @author ruoyi
 */
public interface IOmDemandService
{
    /**
     * 查询生产需求
     */
    public OmDemand selectOmDemandById(Long id);

    /**
     * 查询生产需求列表
     */
    public List<OmDemand> selectOmDemandList(OmDemand omDemand);

    /**
     * 新增生产需求
     */
    public int insertOmDemand(OmDemand omDemand);

    /**
     * 修改生产需求
     */
    public int updateOmDemand(OmDemand omDemand);

    /**
     * 批量删除生产需求
     */
    public int deleteOmDemandByIds(Long[] ids);

    /**
     * 删除生产需求信息
     */
    public int deleteOmDemandById(Long id);
}
