package com.ruoyi.mes.integration.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.integration.mapper.IntDeadLetterMapper;
import com.ruoyi.mes.integration.domain.IntDeadLetter;
import com.ruoyi.mes.integration.service.IIntDeadLetterService;

/**
 * 集成死信Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class IntDeadLetterServiceImpl implements IIntDeadLetterService
{
    @Autowired
    private IntDeadLetterMapper intDeadLetterMapper;

    /**
     * 查询集成死信
     */
    @Override
    public IntDeadLetter selectIntDeadLetterById(Long id)
    {
        return intDeadLetterMapper.selectIntDeadLetterById(id);
    }

    /**
     * 查询集成死信列表
     */
    @Override
    public List<IntDeadLetter> selectIntDeadLetterList(IntDeadLetter intDeadLetter)
    {
        return intDeadLetterMapper.selectIntDeadLetterList(intDeadLetter);
    }

    /**
     * 新增集成死信
     */
    @Override
    public int insertIntDeadLetter(IntDeadLetter intDeadLetter)
    {
        return intDeadLetterMapper.insertIntDeadLetter(intDeadLetter);
    }

    /**
     * 修改集成死信
     */
    @Override
    public int updateIntDeadLetter(IntDeadLetter intDeadLetter)
    {
        return intDeadLetterMapper.updateIntDeadLetter(intDeadLetter);
    }

    /**
     * 批量删除集成死信
     */
    @Override
    public int deleteIntDeadLetterByIds(Long[] ids)
    {
        return intDeadLetterMapper.deleteIntDeadLetterByIds(ids);
    }

    /**
     * 删除集成死信信息
     */
    @Override
    public int deleteIntDeadLetterById(Long id)
    {
        return intDeadLetterMapper.deleteIntDeadLetterById(id);
    }
}
