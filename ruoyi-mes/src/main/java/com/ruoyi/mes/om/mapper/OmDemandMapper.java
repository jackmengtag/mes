package com.ruoyi.mes.om.mapper;

import java.util.List;
import com.ruoyi.mes.om.domain.OmDemand;

/**
 * 生产需求Mapper接口
 *
 * @author ruoyi
 */
public interface OmDemandMapper
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
     * 删除生产需求
     */
    public int deleteOmDemandById(Long id);

    /**
     * 批量删除生产需求
     */
    public int deleteOmDemandByIds(Long[] ids);
}
