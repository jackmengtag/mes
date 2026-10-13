package com.ruoyi.mes.om.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.enums.DemandStatus;
import com.ruoyi.mes.common.enums.DocumentStatus;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.integration.dto.MesDtos;
import com.ruoyi.mes.integration.support.DomainEventService;
import com.ruoyi.mes.integration.support.IdempotentService;
import com.ruoyi.mes.md.domain.MdProduct;
import com.ruoyi.mes.md.domain.MdProductVersion;
import com.ruoyi.mes.md.mapper.MdProductMapper;
import com.ruoyi.mes.md.mapper.MdProductVersionMapper;
import com.ruoyi.mes.om.domain.OmDemand;
import com.ruoyi.mes.om.mapper.OmDemandMapper;

/**
 * 生产需求接收服务（FR-OM-001/002，IR-ERP-005）
 *
 * <p>以「来源系统 + 工厂 + 订单号 + 行号」为幂等键，重复下达返回首次结果；
 * 校验产品与版本有效性，校验失败生成异常记录（状态校验失败）且不生成工单。</p>
 *
 * @author ruoyi
 */
@Service
public class DemandReceiveService
{
    @Autowired
    private OmDemandMapper demandMapper;

    @Autowired
    private MdProductMapper productMapper;

    @Autowired
    private MdProductVersionMapper productVersionMapper;

    @Autowired
    private IdempotentService idempotentService;

    @Autowired
    private DomainEventService domainEventService;

    /**
     * 接收结果
     */
    public static class Result
    {
        public boolean success;
        public String message;
        public OmDemand demand;

        public Result(boolean success, String message, OmDemand demand)
        {
            this.success = success;
            this.message = message;
            this.demand = demand;
        }
    }

    /**
     * 接收 ERP 生产需求
     */
    @Transactional
    public Result receive(MesDtos.DemandRequest req, String traceId)
    {
        String sourceSystem = StringUtils.isNotEmpty(req.plantCode) ? "ERP" : "ERP";
        String plant = StringUtils.isNotEmpty(req.plantCode) ? req.plantCode : MesConstants.DEFAULT_PLANT;
        String lineNo = req.sourceOrderLineNo == null ? "10" : req.sourceOrderLineNo;
        String key = sourceSystem + "#" + plant + "#" + req.sourceOrderNo + "#" + lineNo;

        IdempotentService.Result reg = idempotentService.tryRegister(key, "ERP_DEMAND");
        if (reg.isDuplicated())
        {
            Long bizId = reg.getRecord() == null ? null : reg.getRecord().getBizId();
            OmDemand exist = bizId == null ? null : demandMapper.selectOmDemandById(bizId);
            return new Result(true, "重复下达，返回首次处理结果", exist);
        }

        List<String> errors = new ArrayList<>();
        MdProduct product = findProduct(req.productCode);
        if (product == null)
        {
            errors.add("产品不存在：" + req.productCode);
        }
        else if (StringUtils.isNotEmpty(req.productVersion))
        {
            MdProductVersion version = findVersion(product.getId(), req.productVersion);
            if (version == null)
            {
                errors.add("产品版本无效：" + req.productVersion);
            }
            else if (!DocumentStatus.PUBLISHED.name().equals(version.getDocStatus()))
            {
                errors.add("产品版本未发布：" + req.productVersion);
            }
            else if (version.getExpireTime() != null && version.getExpireTime().before(new Date()))
            {
                errors.add("产品版本已失效：" + req.productVersion);
            }
        }

        OmDemand demand = new OmDemand();
        demand.setDemandNo(MesSeqUtils.generate(MesConstants.PREFIX_DEMAND));
        demand.setSourceOrderNo(req.sourceOrderNo);
        demand.setSourceOrderLineNo(lineNo);
        demand.setSourceSystem(sourceSystem);
        demand.setPlantCode(plant);
        demand.setProductCode(req.productCode);
        if (product != null)
        {
            demand.setProductId(product.getId());
        }
        demand.setProductVersion(req.productVersion);
        demand.setRequiredQty(nz(req.requiredQuantity));
        demand.setDueDate(parseDate(req.dueDate));
        demand.setPriority(StringUtils.isNotEmpty(req.priority) ? req.priority : "NORMAL");
        demand.setIdempotencyKey(key);
        demand.setStatus(errors.isEmpty() ? DemandStatus.CONFIRMED.name() : DemandStatus.VALIDATION_FAILED.name());
        if (!errors.isEmpty())
        {
            demand.setChangeReason(String.join("；", errors));
        }
        demand.setCreateBy("ERP");
        demandMapper.insertOmDemand(demand);

        idempotentService.complete(reg.getRecord(), demand.getId(),
                errors.isEmpty() ? "OK" : "VALIDATION_FAILED", String.join("；", errors));

        if (errors.isEmpty())
        {
            domainEventService.publish("DemandReceived", "OmDemand", demand.getId(),
                    "{\"demandNo\":\"" + demand.getDemandNo() + "\",\"productCode\":\"" + req.productCode + "\"}", traceId);
            return new Result(true, "接收成功", demand);
        }
        return new Result(false, String.join("；", errors), demand);
    }

    private MdProduct findProduct(String productCode)
    {
        if (StringUtils.isEmpty(productCode))
        {
            return null;
        }
        MdProduct query = new MdProduct();
        query.setProductCode(productCode);
        for (MdProduct p : productMapper.selectMdProductList(query))
        {
            if (productCode.equals(p.getProductCode()))
            {
                return p;
            }
        }
        return null;
    }

    private MdProductVersion findVersion(Long productId, String versionNo)
    {
        MdProductVersion query = new MdProductVersion();
        query.setProductId(productId);
        query.setVersionNo(versionNo);
        for (MdProductVersion v : productVersionMapper.selectMdProductVersionList(query))
        {
            if (versionNo.equals(v.getVersionNo()))
            {
                return v;
            }
        }
        return null;
    }

    private static Date parseDate(String s)
    {
        if (StringUtils.isEmpty(s))
        {
            return null;
        }
        try
        {
            if (s.length() <= 10)
            {
                return new SimpleDateFormat("yyyy-MM-dd").parse(s);
            }
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(s.substring(0, 19));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private static BigDecimal nz(BigDecimal v)
    {
        return v == null ? BigDecimal.ZERO : v;
    }
}
