package com.ruoyi.mes.integration.service;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntDeadLetter;

/**
 * 集成死信Service接口
 *
 * @author ruoyi
 */
public interface IIntDeadLetterService
{
    /**
     * 查询集成死信
     */
    public IntDeadLetter selectIntDeadLetterById(Long id);

    /**
     * 查询集成死信列表
     */
    public List<IntDeadLetter> selectIntDeadLetterList(IntDeadLetter intDeadLetter);

    /**
     * 新增集成死信
     */
    public int insertIntDeadLetter(IntDeadLetter intDeadLetter);

    /**
     * 修改集成死信
     */
    public int updateIntDeadLetter(IntDeadLetter intDeadLetter);

    /**
     * 批量删除集成死信
     */
    public int deleteIntDeadLetterByIds(Long[] ids);

    /**
     * 删除集成死信信息
     */
    public int deleteIntDeadLetterById(Long id);
}
