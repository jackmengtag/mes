package com.ruoyi.mes.integration.mapper;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntDeadLetter;

/**
 * 集成死信Mapper接口
 *
 * @author ruoyi
 */
public interface IntDeadLetterMapper
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
     * 删除集成死信
     */
    public int deleteIntDeadLetterById(Long id);

    /**
     * 批量删除集成死信
     */
    public int deleteIntDeadLetterByIds(Long[] ids);
}
