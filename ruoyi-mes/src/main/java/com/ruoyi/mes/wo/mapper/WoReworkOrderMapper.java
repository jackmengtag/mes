package com.ruoyi.mes.wo.mapper;

import java.util.List;
import com.ruoyi.mes.wo.domain.WoReworkOrder;

/**
 * 返工工单Mapper接口
 *
 * @author ruoyi
 */
public interface WoReworkOrderMapper
{
    /**
     * 查询返工工单
     */
    public WoReworkOrder selectWoReworkOrderById(Long id);

    /**
     * 查询返工工单列表
     */
    public List<WoReworkOrder> selectWoReworkOrderList(WoReworkOrder woReworkOrder);

    /**
     * 新增返工工单
     */
    public int insertWoReworkOrder(WoReworkOrder woReworkOrder);

    /**
     * 修改返工工单
     */
    public int updateWoReworkOrder(WoReworkOrder woReworkOrder);

    /**
     * 删除返工工单
     */
    public int deleteWoReworkOrderById(Long id);

    /**
     * 批量删除返工工单
     */
    public int deleteWoReworkOrderByIds(Long[] ids);
}
