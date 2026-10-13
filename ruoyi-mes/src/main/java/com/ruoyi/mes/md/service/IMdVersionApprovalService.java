package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdVersionApproval;

/**
 * 主数据版本审批Service接口
 *
 * @author ruoyi
 */
public interface IMdVersionApprovalService
{
    /**
     * 查询主数据版本审批
     */
    public MdVersionApproval selectMdVersionApprovalById(Long id);

    /**
     * 查询主数据版本审批列表
     */
    public List<MdVersionApproval> selectMdVersionApprovalList(MdVersionApproval mdVersionApproval);

    /**
     * 新增主数据版本审批
     */
    public int insertMdVersionApproval(MdVersionApproval mdVersionApproval);

    /**
     * 修改主数据版本审批
     */
    public int updateMdVersionApproval(MdVersionApproval mdVersionApproval);

    /**
     * 批量删除主数据版本审批
     */
    public int deleteMdVersionApprovalByIds(Long[] ids);

    /**
     * 删除主数据版本审批信息
     */
    public int deleteMdVersionApprovalById(Long id);
}
