package com.ruoyi.mes.integration.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.integration.mapper.IntDomainEventMapper;
import com.ruoyi.mes.integration.domain.IntDomainEvent;
import com.ruoyi.mes.integration.service.IIntDomainEventService;

/**
 * 领域事件Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class IntDomainEventServiceImpl implements IIntDomainEventService
{
    @Autowired
    private IntDomainEventMapper intDomainEventMapper;

    /**
     * 查询领域事件
     */
    @Override
    public IntDomainEvent selectIntDomainEventById(Long id)
    {
        return intDomainEventMapper.selectIntDomainEventById(id);
    }

    /**
     * 查询领域事件列表
     */
    @Override
    public List<IntDomainEvent> selectIntDomainEventList(IntDomainEvent intDomainEvent)
    {
        return intDomainEventMapper.selectIntDomainEventList(intDomainEvent);
    }

    /**
     * 新增领域事件
     */
    @Override
    public int insertIntDomainEvent(IntDomainEvent intDomainEvent)
    {
        return intDomainEventMapper.insertIntDomainEvent(intDomainEvent);
    }

    /**
     * 修改领域事件
     */
    @Override
    public int updateIntDomainEvent(IntDomainEvent intDomainEvent)
    {
        return intDomainEventMapper.updateIntDomainEvent(intDomainEvent);
    }

    /**
     * 批量删除领域事件
     */
    @Override
    public int deleteIntDomainEventByIds(Long[] ids)
    {
        return intDomainEventMapper.deleteIntDomainEventByIds(ids);
    }

    /**
     * 删除领域事件信息
     */
    @Override
    public int deleteIntDomainEventById(Long id)
    {
        return intDomainEventMapper.deleteIntDomainEventById(id);
    }
}
