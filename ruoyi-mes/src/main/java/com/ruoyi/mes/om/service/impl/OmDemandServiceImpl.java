package com.ruoyi.mes.om.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.om.mapper.OmDemandMapper;
import com.ruoyi.mes.om.domain.OmDemand;
import com.ruoyi.mes.om.service.IOmDemandService;

/**
 * 生产需求Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class OmDemandServiceImpl implements IOmDemandService
{
    @Autowired
    private OmDemandMapper omDemandMapper;

    /**
     * 查询生产需求
     */
    @Override
    public OmDemand selectOmDemandById(Long id)
    {
        return omDemandMapper.selectOmDemandById(id);
    }

    /**
     * 查询生产需求列表
     */
    @Override
    public List<OmDemand> selectOmDemandList(OmDemand omDemand)
    {
        return omDemandMapper.selectOmDemandList(omDemand);
    }

    /**
     * 新增生产需求
     */
    @Override
    public int insertOmDemand(OmDemand omDemand)
    {
        return omDemandMapper.insertOmDemand(omDemand);
    }

    /**
     * 修改生产需求
     */
    @Override
    public int updateOmDemand(OmDemand omDemand)
    {
        return omDemandMapper.updateOmDemand(omDemand);
    }

    /**
     * 批量删除生产需求
     */
    @Override
    public int deleteOmDemandByIds(Long[] ids)
    {
        return omDemandMapper.deleteOmDemandByIds(ids);
    }

    /**
     * 删除生产需求信息
     */
    @Override
    public int deleteOmDemandById(Long id)
    {
        return omDemandMapper.deleteOmDemandById(id);
    }
}
