package com.ruoyi.mes.md.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.mes.common.enums.DocumentStatus;
import com.ruoyi.mes.md.domain.MdBom;
import com.ruoyi.mes.md.domain.MdInspectionPlan;
import com.ruoyi.mes.md.domain.MdProductVersion;
import com.ruoyi.mes.md.domain.MdRoute;
import com.ruoyi.mes.md.domain.MdVersionApproval;
import com.ruoyi.mes.md.mapper.MdBomMapper;
import com.ruoyi.mes.md.mapper.MdInspectionPlanMapper;
import com.ruoyi.mes.md.mapper.MdProductVersionMapper;
import com.ruoyi.mes.md.mapper.MdRouteMapper;
import com.ruoyi.mes.md.mapper.MdVersionApprovalMapper;

/**
 * 主数据版本与审批服务（FR-MD-010，架构文档 6.4 主数据版本策略）
 *
 * <p>未发布版本不允许用于生产；已发布版本变更必须生成新版本（此处通过发布动作与审批留痕实现）。</p>
 *
 * @author ruoyi
 */
@Service
public class MdVersionService
{
    /** 产品版本 */
    public static final String BIZ_PRODUCT_VERSION = "PRODUCT_VERSION";
    /** BOM */
    public static final String BIZ_BOM = "BOM";
    /** 工艺路线 */
    public static final String BIZ_ROUTE = "ROUTE";
    /** 检验方案 */
    public static final String BIZ_INSPECTION_PLAN = "INSPECTION_PLAN";

    @Autowired
    private MdProductVersionMapper productVersionMapper;

    @Autowired
    private MdBomMapper bomMapper;

    @Autowired
    private MdRouteMapper routeMapper;

    @Autowired
    private MdInspectionPlanMapper inspectionPlanMapper;

    @Autowired
    private MdVersionApprovalMapper versionApprovalMapper;

    /**
     * 发布主数据版本（草稿/待审批 -> 已发布）
     */
    @Transactional
    public MdVersionApproval publish(String bizType, Long bizId, String changeReason, String operator)
    {
        String versionNo = applyDocStatus(bizType, bizId, DocumentStatus.PUBLISHED.name());
        return recordApproval(bizType, bizId, versionNo, DocumentStatus.PUBLISHED.name(), changeReason, operator);
    }

    /**
     * 提交审批（草稿 -> 待审批）
     */
    @Transactional
    public MdVersionApproval submitApproval(String bizType, Long bizId, String changeReason, String operator)
    {
        String versionNo = applyDocStatus(bizType, bizId, DocumentStatus.PENDING_APPROVAL.name());
        return recordApproval(bizType, bizId, versionNo, DocumentStatus.PENDING_APPROVAL.name(), changeReason, operator);
    }

    /**
     * 失效版本
     */
    @Transactional
    public MdVersionApproval invalidate(String bizType, Long bizId, String changeReason, String operator)
    {
        String versionNo = applyDocStatus(bizType, bizId, DocumentStatus.INVALID.name());
        return recordApproval(bizType, bizId, versionNo, DocumentStatus.INVALID.name(), changeReason, operator);
    }

    private String applyDocStatus(String bizType, Long bizId, String docStatus)
    {
        if (BIZ_PRODUCT_VERSION.equals(bizType))
        {
            MdProductVersion v = productVersionMapper.selectMdProductVersionById(bizId);
            if (v == null)
            {
                throw new ServiceException("产品版本不存在：" + bizId);
            }
            v.setDocStatus(docStatus);
            v.setUpdateBy("system");
            productVersionMapper.updateMdProductVersion(v);
            return v.getVersionNo();
        }
        else if (BIZ_BOM.equals(bizType))
        {
            MdBom v = bomMapper.selectMdBomById(bizId);
            if (v == null)
            {
                throw new ServiceException("BOM不存在：" + bizId);
            }
            v.setDocStatus(docStatus);
            v.setUpdateBy("system");
            bomMapper.updateMdBom(v);
            return v.getVersionNo();
        }
        else if (BIZ_ROUTE.equals(bizType))
        {
            MdRoute v = routeMapper.selectMdRouteById(bizId);
            if (v == null)
            {
                throw new ServiceException("工艺路线不存在：" + bizId);
            }
            v.setDocStatus(docStatus);
            v.setUpdateBy("system");
            routeMapper.updateMdRoute(v);
            return v.getVersionNo();
        }
        else if (BIZ_INSPECTION_PLAN.equals(bizType))
        {
            MdInspectionPlan v = inspectionPlanMapper.selectMdInspectionPlanById(bizId);
            if (v == null)
            {
                throw new ServiceException("检验方案不存在：" + bizId);
            }
            v.setDocStatus(docStatus);
            v.setUpdateBy("system");
            inspectionPlanMapper.updateMdInspectionPlan(v);
            return v.getVersionNo();
        }
        throw new ServiceException("不支持的主数据类型：" + bizType);
    }

    private MdVersionApproval recordApproval(String bizType, Long bizId, String versionNo, String docStatus,
            String changeReason, String operator)
    {
        MdVersionApproval approval = new MdVersionApproval();
        approval.setBizType(bizType);
        approval.setBizId(bizId);
        approval.setVersionNo(versionNo);
        approval.setDocStatus(docStatus);
        approval.setChangeReason(changeReason);
        approval.setChangeBy(operator);
        approval.setChangeTime(new Date());
        approval.setCreateBy(operator);
        versionApprovalMapper.insertMdVersionApproval(approval);
        return approval;
    }
}
