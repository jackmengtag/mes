package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdWorkCenterMapper;
import com.ruoyi.mes.md.domain.MdWorkCenter;
import com.ruoyi.mes.md.service.IMdWorkCenterService;

/**
 * 工作中心Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdWorkCenterServiceImpl implements IMdWorkCenterService
{
    @Autowired
    private MdWorkCenterMapper mdWorkCenterMapper;

    /**
     * 查询工作中心
     */
    @Override
    public MdWorkCenter selectMdWorkCenterById(Long id)
    {
        return mdWorkCenterMapper.selectMdWorkCenterById(id);
    }

    /**
     * 查询工作中心列表
     */
    @Override
    public List<MdWorkCenter> selectMdWorkCenterList(MdWorkCenter mdWorkCenter)
    {
        return mdWorkCenterMapper.selectMdWorkCenterList(mdWorkCenter);
    }

    /**
     * 新增工作中心
     */
    @Override
    public int insertMdWorkCenter(MdWorkCenter mdWorkCenter)
    {
        return mdWorkCenterMapper.insertMdWorkCenter(mdWorkCenter);
    }

    /**
     * 修改工作中心
     */
    @Override
    public int updateMdWorkCenter(MdWorkCenter mdWorkCenter)
    {
        return mdWorkCenterMapper.updateMdWorkCenter(mdWorkCenter);
    }

    /**
     * 批量删除工作中心
     */
    @Override
    public int deleteMdWorkCenterByIds(Long[] ids)
    {
        return mdWorkCenterMapper.deleteMdWorkCenterByIds(ids);
    }

    /**
     * 删除工作中心信息
     */
    @Override
    public int deleteMdWorkCenterById(Long id)
    {
        return mdWorkCenterMapper.deleteMdWorkCenterById(id);
    }
}
