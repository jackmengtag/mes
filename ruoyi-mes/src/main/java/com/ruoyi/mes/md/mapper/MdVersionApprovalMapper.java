package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdVersionApproval;

/**
 * 主数据版本审批Mapper接口
 *
 * @author ruoyi
 */
public interface MdVersionApprovalMapper
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
     * 删除主数据版本审批
     */
    public int deleteMdVersionApprovalById(Long id);

    /**
     * 批量删除主数据版本审批
     */
    public int deleteMdVersionApprovalByIds(Long[] ids);
}
