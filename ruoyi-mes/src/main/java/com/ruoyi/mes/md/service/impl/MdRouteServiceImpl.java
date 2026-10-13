package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdRouteMapper;
import com.ruoyi.mes.md.domain.MdRoute;
import com.ruoyi.mes.md.service.IMdRouteService;

/**
 * 工艺路线Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdRouteServiceImpl implements IMdRouteService
{
    @Autowired
    private MdRouteMapper mdRouteMapper;

    /**
     * 查询工艺路线
     */
    @Override
    public MdRoute selectMdRouteById(Long id)
    {
        return mdRouteMapper.selectMdRouteById(id);
    }

    /**
     * 查询工艺路线列表
     */
    @Override
    public List<MdRoute> selectMdRouteList(MdRoute mdRoute)
    {
        return mdRouteMapper.selectMdRouteList(mdRoute);
    }

    /**
     * 新增工艺路线
     */
    @Override
    public int insertMdRoute(MdRoute mdRoute)
    {
        return mdRouteMapper.insertMdRoute(mdRoute);
    }

    /**
     * 修改工艺路线
     */
    @Override
    public int updateMdRoute(MdRoute mdRoute)
    {
        return mdRouteMapper.updateMdRoute(mdRoute);
    }

    /**
     * 批量删除工艺路线
     */
    @Override
    public int deleteMdRouteByIds(Long[] ids)
    {
        return mdRouteMapper.deleteMdRouteByIds(ids);
    }

    /**
     * 删除工艺路线信息
     */
    @Override
    public int deleteMdRouteById(Long id)
    {
        return mdRouteMapper.deleteMdRouteById(id);
    }
}
