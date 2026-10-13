package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdRouteOperation;

/**
 * 工艺工序Service接口
 *
 * @author ruoyi
 */
public interface IMdRouteOperationService
{
    /**
     * 查询工艺工序
     */
    public MdRouteOperation selectMdRouteOperationById(Long id);

    /**
     * 查询工艺工序列表
     */
    public List<MdRouteOperation> selectMdRouteOperationList(MdRouteOperation mdRouteOperation);

    /**
     * 新增工艺工序
     */
    public int insertMdRouteOperation(MdRouteOperation mdRouteOperation);

    /**
     * 修改工艺工序
     */
    public int updateMdRouteOperation(MdRouteOperation mdRouteOperation);

    /**
     * 批量删除工艺工序
     */
    public int deleteMdRouteOperationByIds(Long[] ids);

    /**
     * 删除工艺工序信息
     */
    public int deleteMdRouteOperationById(Long id);
}
