package com.ruoyi.mes.integration.mapper;

import java.util.List;
import com.ruoyi.mes.integration.domain.IntMessage;

/**
 * 集成消息Mapper接口
 *
 * @author ruoyi
 */
public interface IntMessageMapper
{
    /**
     * 查询集成消息
     */
    public IntMessage selectIntMessageById(Long id);

    /**
     * 查询集成消息列表
     */
    public List<IntMessage> selectIntMessageList(IntMessage intMessage);

    /**
     * 新增集成消息
     */
    public int insertIntMessage(IntMessage intMessage);

    /**
     * 修改集成消息
     */
    public int updateIntMessage(IntMessage intMessage);

    /**
     * 删除集成消息
     */
    public int deleteIntMessageById(Long id);

    /**
     * 批量删除集成消息
     */
    public int deleteIntMessageByIds(Long[] ids);
}
