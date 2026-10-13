package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdStationMapper;
import com.ruoyi.mes.md.domain.MdStation;
import com.ruoyi.mes.md.service.IMdStationService;

/**
 * 工位Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdStationServiceImpl implements IMdStationService
{
    @Autowired
    private MdStationMapper mdStationMapper;

    /**
     * 查询工位
     */
    @Override
    public MdStation selectMdStationById(Long id)
    {
        return mdStationMapper.selectMdStationById(id);
    }

    /**
     * 查询工位列表
     */
    @Override
    public List<MdStation> selectMdStationList(MdStation mdStation)
    {
        return mdStationMapper.selectMdStationList(mdStation);
    }

    /**
     * 新增工位
     */
    @Override
    public int insertMdStation(MdStation mdStation)
    {
        return mdStationMapper.insertMdStation(mdStation);
    }

    /**
     * 修改工位
     */
    @Override
    public int updateMdStation(MdStation mdStation)
    {
        return mdStationMapper.updateMdStation(mdStation);
    }

    /**
     * 批量删除工位
     */
    @Override
    public int deleteMdStationByIds(Long[] ids)
    {
        return mdStationMapper.deleteMdStationByIds(ids);
    }

    /**
     * 删除工位信息
     */
    @Override
    public int deleteMdStationById(Long id)
    {
        return mdStationMapper.deleteMdStationById(id);
    }
}
