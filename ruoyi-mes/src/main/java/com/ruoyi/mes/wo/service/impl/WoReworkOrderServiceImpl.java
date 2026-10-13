package com.ruoyi.mes.wo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.wo.mapper.WoReworkOrderMapper;
import com.ruoyi.mes.wo.domain.WoReworkOrder;
import com.ruoyi.mes.wo.service.IWoReworkOrderService;

/**
 * 返工工单Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class WoReworkOrderServiceImpl implements IWoReworkOrderService
{
    @Autowired
    private WoReworkOrderMapper woReworkOrderMapper;

    /**
     * 查询返工工单
     */
    @Override
    public WoReworkOrder selectWoReworkOrderById(Long id)
    {
        return woReworkOrderMapper.selectWoReworkOrderById(id);
    }

    /**
     * 查询返工工单列表
     */
    @Override
    public List<WoReworkOrder> selectWoReworkOrderList(WoReworkOrder woReworkOrder)
    {
        return woReworkOrderMapper.selectWoReworkOrderList(woReworkOrder);
    }

    /**
     * 新增返工工单
     */
    @Override
    public int insertWoReworkOrder(WoReworkOrder woReworkOrder)
    {
        return woReworkOrderMapper.insertWoReworkOrder(woReworkOrder);
    }

    /**
     * 修改返工工单
     */
    @Override
    public int updateWoReworkOrder(WoReworkOrder woReworkOrder)
    {
        return woReworkOrderMapper.updateWoReworkOrder(woReworkOrder);
    }

    /**
     * 批量删除返工工单
     */
    @Override
    public int deleteWoReworkOrderByIds(Long[] ids)
    {
        return woReworkOrderMapper.deleteWoReworkOrderByIds(ids);
    }

    /**
     * 删除返工工单信息
     */
    @Override
    public int deleteWoReworkOrderById(Long id)
    {
        return woReworkOrderMapper.deleteWoReworkOrderById(id);
    }
}
