package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdRoute;

/**
 * 工艺路线Service接口
 *
 * @author ruoyi
 */
public interface IMdRouteService
{
    /**
     * 查询工艺路线
     */
    public MdRoute selectMdRouteById(Long id);

    /**
     * 查询工艺路线列表
     */
    public List<MdRoute> selectMdRouteList(MdRoute mdRoute);

    /**
     * 新增工艺路线
     */
    public int insertMdRoute(MdRoute mdRoute);

    /**
     * 修改工艺路线
     */
    public int updateMdRoute(MdRoute mdRoute);

    /**
     * 批量删除工艺路线
     */
    public int deleteMdRouteByIds(Long[] ids);

    /**
     * 删除工艺路线信息
     */
    public int deleteMdRouteById(Long id);
}
