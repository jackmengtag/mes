package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdVersionApprovalMapper;
import com.ruoyi.mes.md.domain.MdVersionApproval;
import com.ruoyi.mes.md.service.IMdVersionApprovalService;

/**
 * 主数据版本审批Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdVersionApprovalServiceImpl implements IMdVersionApprovalService
{
    @Autowired
    private MdVersionApprovalMapper mdVersionApprovalMapper;

    /**
     * 查询主数据版本审批
     */
    @Override
    public MdVersionApproval selectMdVersionApprovalById(Long id)
    {
        return mdVersionApprovalMapper.selectMdVersionApprovalById(id);
    }

    /**
     * 查询主数据版本审批列表
     */
    @Override
    public List<MdVersionApproval> selectMdVersionApprovalList(MdVersionApproval mdVersionApproval)
    {
        return mdVersionApprovalMapper.selectMdVersionApprovalList(mdVersionApproval);
    }

    /**
     * 新增主数据版本审批
     */
    @Override
    public int insertMdVersionApproval(MdVersionApproval mdVersionApproval)
    {
        return mdVersionApprovalMapper.insertMdVersionApproval(mdVersionApproval);
    }

    /**
     * 修改主数据版本审批
     */
    @Override
    public int updateMdVersionApproval(MdVersionApproval mdVersionApproval)
    {
        return mdVersionApprovalMapper.updateMdVersionApproval(mdVersionApproval);
    }

    /**
     * 批量删除主数据版本审批
     */
    @Override
    public int deleteMdVersionApprovalByIds(Long[] ids)
    {
        return mdVersionApprovalMapper.deleteMdVersionApprovalByIds(ids);
    }

    /**
     * 删除主数据版本审批信息
     */
    @Override
    public int deleteMdVersionApprovalById(Long id)
    {
        return mdVersionApprovalMapper.deleteMdVersionApprovalById(id);
    }
}
