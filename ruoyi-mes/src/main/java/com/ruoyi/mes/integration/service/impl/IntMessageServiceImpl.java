package com.ruoyi.mes.integration.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.integration.mapper.IntMessageMapper;
import com.ruoyi.mes.integration.domain.IntMessage;
import com.ruoyi.mes.integration.service.IIntMessageService;

/**
 * 集成消息Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class IntMessageServiceImpl implements IIntMessageService
{
    @Autowired
    private IntMessageMapper intMessageMapper;

    /**
     * 查询集成消息
     */
    @Override
    public IntMessage selectIntMessageById(Long id)
    {
        return intMessageMapper.selectIntMessageById(id);
    }

    /**
     * 查询集成消息列表
     */
    @Override
    public List<IntMessage> selectIntMessageList(IntMessage intMessage)
    {
        return intMessageMapper.selectIntMessageList(intMessage);
    }

    /**
     * 新增集成消息
     */
    @Override
    public int insertIntMessage(IntMessage intMessage)
    {
        return intMessageMapper.insertIntMessage(intMessage);
    }

    /**
     * 修改集成消息
     */
    @Override
    public int updateIntMessage(IntMessage intMessage)
    {
        return intMessageMapper.updateIntMessage(intMessage);
    }

    /**
     * 批量删除集成消息
     */
    @Override
    public int deleteIntMessageByIds(Long[] ids)
    {
        return intMessageMapper.deleteIntMessageByIds(ids);
    }

    /**
     * 删除集成消息信息
     */
    @Override
    public int deleteIntMessageById(Long id)
    {
        return intMessageMapper.deleteIntMessageById(id);
    }
}
