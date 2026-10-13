package com.ruoyi.mes.integration.service;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntDomainEvent;

/**
 * 领域事件Service接口
 *
 * @author ruoyi
 */
public interface IIntDomainEventService
{
    /**
     * 查询领域事件
     */
    public IntDomainEvent selectIntDomainEventById(Long id);

    /**
     * 查询领域事件列表
     */
    public List<IntDomainEvent> selectIntDomainEventList(IntDomainEvent intDomainEvent);

    /**
     * 新增领域事件
     */
    public int insertIntDomainEvent(IntDomainEvent intDomainEvent);

    /**
     * 修改领域事件
     */
    public int updateIntDomainEvent(IntDomainEvent intDomainEvent);

    /**
     * 批量删除领域事件
     */
    public int deleteIntDomainEventByIds(Long[] ids);

    /**
     * 删除领域事件信息
     */
    public int deleteIntDomainEventById(Long id);
}
