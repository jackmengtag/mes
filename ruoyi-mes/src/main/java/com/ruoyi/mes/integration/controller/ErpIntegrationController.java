package com.ruoyi.mes.integration.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.enums.DocumentStatus;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.integration.dto.MesDtos;
import com.ruoyi.mes.integration.service.ReconciliationService;
import com.ruoyi.mes.integration.support.MapUtils;
import com.ruoyi.mes.integration.support.MessageService;
import com.ruoyi.mes.md.domain.MdBom;
import com.ruoyi.mes.md.domain.MdBomLine;
import com.ruoyi.mes.md.domain.MdMaterial;
import com.ruoyi.mes.md.domain.MdProduct;
import com.ruoyi.mes.md.domain.MdProductVersion;
import com.ruoyi.mes.md.domain.MdRoute;
import com.ruoyi.mes.md.domain.MdRouteOperation;
import com.ruoyi.mes.md.mapper.MdBomLineMapper;
import com.ruoyi.mes.md.mapper.MdBomMapper;
import com.ruoyi.mes.md.mapper.MdMaterialMapper;
import com.ruoyi.mes.md.mapper.MdProductMapper;
import com.ruoyi.mes.md.mapper.MdProductVersionMapper;
import com.ruoyi.mes.md.mapper.MdRouteMapper;
import com.ruoyi.mes.md.mapper.MdRouteOperationMapper;
import com.ruoyi.mes.om.service.DemandReceiveService;

/**
 * ERP 集成接口（架构文档 7.4 / IR-ERP-001~008）
 *
 * <p>外部系统通过本入口与 MES 交互，不直接访问 MES 业务数据库（ADR-007）。</p>
 *
 * @author ruoyi
 */
@Anonymous
@RestController
@RequestMapping("/integration/v1/erp")
public class ErpIntegrationController extends BaseController
{
    @Autowired
    private MdMaterialMapper materialMapper;

    @Autowired
    private MdProductMapper productMapper;

    @Autowired
    private MdProductVersionMapper productVersionMapper;

    @Autowired
    private MdBomMapper bomMapper;

    @Autowired
    private MdBomLineMapper bomLineMapper;

    @Autowired
    private MdRouteMapper routeMapper;

    @Autowired
    private MdRouteOperationMapper routeOperationMapper;

    @Autowired
    private DemandReceiveService demandReceiveService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ReconciliationService reconciliationService;

    /**
     * 物料主数据同步（IR-ERP-001），以物料编码为幂等键
     */
    @PostMapping("/material-master")
    public AjaxResult materialMaster(@RequestBody List<Map<String, Object>> items)
    {
        int count = 0;
        for (Map<String, Object> item : items)
        {
            String code = MapUtils.str(item, "materialCode");
            if (code == null)
            {
                continue;
            }
            MdMaterial material = findMaterial(code);
            boolean exists = material != null;
            if (!exists)
            {
                material = new MdMaterial();
                material.setMaterialCode(code);
                material.setCreateBy("ERP");
            }
            material.setMaterialName(MapUtils.str(item, "materialName"));
            material.setUnit(MapUtils.str(item, "unit"));
            material.setMaterialType(MapUtils.str(item, "materialType"));
            material.setBatchFlag(MapUtils.str(item, "batchFlag", MesConstants.NO));
            material.setSerialFlag(MapUtils.str(item, "serialFlag", MesConstants.NO));
            material.setShelfLife(MapUtils.integer(item, "shelfLife"));
            material.setKeyFlag(MapUtils.str(item, "keyFlag", MesConstants.NO));
            material.setInspectFlag(MapUtils.str(item, "inspectFlag", MesConstants.NO));
            material.setSubstituteFlag(MapUtils.str(item, "substituteFlag", MesConstants.NO));
            material.setStatus(MapUtils.str(item, "status", MesConstants.STATUS_NORMAL));
            material.setUpdateBy("ERP");
            if (exists)
            {
                materialMapper.updateMdMaterial(material);
            }
            else
            {
                materialMapper.insertMdMaterial(material);
            }
            count++;
        }
        return success("同步物料 " + count + " 条");
    }

    /**
     * 产品与版本同步（IR-ERP-002）
     */
    @PostMapping("/product-master")
    public AjaxResult productMaster(@RequestBody List<Map<String, Object>> items)
    {
        int count = 0;
        for (Map<String, Object> item : items)
        {
            String code = MapUtils.str(item, "productCode");
            if (code == null)
            {
                continue;
            }
            MdProduct product = findProduct(code);
            boolean exists = product != null;
            if (!exists)
            {
                product = new MdProduct();
                product.setProductCode(code);
                product.setCreateBy("ERP");
            }
            product.setProductName(MapUtils.str(item, "productName"));
            product.setProductType(MapUtils.str(item, "productType"));
            product.setSerialFlag(MapUtils.str(item, "serialFlag", MesConstants.NO));
            product.setStatus(MesConstants.STATUS_NORMAL);
            product.setUpdateBy("ERP");
            if (exists)
            {
                productMapper.updateMdProduct(product);
            }
            else
            {
                productMapper.insertMdProduct(product);
            }
            // 版本
            Object versions = item.get("versions");
            if (versions instanceof List)
            {
                for (Object vo : (List<?>) versions)
                {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> v = (Map<String, Object>) vo;
                    upsertProductVersion(product.getId(), code, v);
                }
            }
            count++;
        }
        return success("同步产品 " + count + " 条");
    }

    /**
     * BOM 同步（IR-ERP-003）
     */
    @PostMapping("/bom")
    public AjaxResult bom(@RequestBody List<Map<String, Object>> items)
    {
        int count = 0;
        for (Map<String, Object> item : items)
        {
            String code = MapUtils.str(item, "bomCode");
            if (code == null)
            {
                continue;
            }
            MdBom bom = findBom(code);
            boolean exists = bom != null;
            if (!exists)
            {
                bom = new MdBom();
                bom.setBomCode(code);
                bom.setCreateBy("ERP");
            }
            bom.setProductCode(MapUtils.str(item, "productCode"));
            bom.setVersionNo(MapUtils.str(item, "versionNo", "V1"));
            bom.setDocStatus(MapUtils.str(item, "docStatus", DocumentStatus.PUBLISHED.name()));
            bom.setStatus(MesConstants.STATUS_NORMAL);
            bom.setUpdateBy("ERP");
            if (exists)
            {
                bomMapper.updateMdBom(bom);
            }
            else
            {
                bomMapper.insertMdBom(bom);
            }
            Object lines = item.get("lines");
            if (lines instanceof List)
            {
                // 先清理旧明细再写入（版本固化后历史工单仍引用自身快照）
                MdBomLine del = new MdBomLine();
                del.setBomId(bom.getId());
                for (MdBomLine old : bomLineMapper.selectMdBomLineList(del))
                {
                    bomLineMapper.deleteMdBomLineById(old.getId());
                }
                for (Object lo : (List<?>) lines)
                {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> l = (Map<String, Object>) lo;
                    MdBomLine line = new MdBomLine();
                    line.setBomId(bom.getId());
                    line.setMaterialCode(MapUtils.str(l, "materialCode"));
                    line.setMaterialName(MapUtils.str(l, "materialName"));
                    line.setQuantity(MapUtils.decimal(l, "quantity"));
                    line.setLossRate(MapUtils.decimal(l, "lossRate"));
                    line.setFeedingOperationCode(MapUtils.str(l, "feedingOperationCode"));
                    line.setKeyFlag(MapUtils.str(l, "keyFlag", MesConstants.NO));
                    line.setBatchBindFlag(MapUtils.str(l, "batchBindFlag", MesConstants.NO));
                    line.setSubstituteFlag(MapUtils.str(l, "substituteFlag", MesConstants.NO));
                    line.setSubstituteMaterialCode(MapUtils.str(l, "substituteMaterialCode"));
                    line.setCreateBy("ERP");
                    bomLineMapper.insertMdBomLine(line);
                }
            }
            count++;
        }
        return success("同步BOM " + count + " 条");
    }

    /**
     * 工艺路线同步（IR-ERP-004）
     */
    @PostMapping("/route")
    public AjaxResult route(@RequestBody List<Map<String, Object>> items)
    {
        int count = 0;
        for (Map<String, Object> item : items)
        {
            String code = MapUtils.str(item, "routeCode");
            if (code == null)
            {
                continue;
            }
            MdRoute route = findRoute(code);
            boolean exists = route != null;
            if (!exists)
            {
                route = new MdRoute();
                route.setRouteCode(code);
                route.setCreateBy("ERP");
            }
            route.setRouteName(MapUtils.str(item, "routeName"));
            route.setProductCode(MapUtils.str(item, "productCode"));
            route.setVersionNo(MapUtils.str(item, "versionNo", "V1"));
            route.setDocStatus(MapUtils.str(item, "docStatus", DocumentStatus.PUBLISHED.name()));
            route.setStatus(MesConstants.STATUS_NORMAL);
            route.setUpdateBy("ERP");
            if (exists)
            {
                routeMapper.updateMdRoute(route);
            }
            else
            {
                routeMapper.insertMdRoute(route);
            }
            Object ops = item.get("operations");
            if (ops instanceof List)
            {
                MdRouteOperation del = new MdRouteOperation();
                del.setRouteId(route.getId());
                for (MdRouteOperation old : routeOperationMapper.selectMdRouteOperationList(del))
                {
                    routeOperationMapper.deleteMdRouteOperationById(old.getId());
                }
                for (Object oo : (List<?>) ops)
                {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> o = (Map<String, Object>) oo;
                    MdRouteOperation op = new MdRouteOperation();
                    op.setRouteId(route.getId());
                    op.setOperationCode(MapUtils.str(o, "operationCode"));
                    op.setOperationName(MapUtils.str(o, "operationName"));
                    op.setSequenceNo(MapUtils.integer(o, "sequenceNo"));
                    op.setWorkCenterId(MapUtils.lng(o, "workCenterId"));
                    op.setEquipmentType(MapUtils.str(o, "equipmentType"));
                    op.setForceInspect(MapUtils.str(o, "forceInspect", MesConstants.NO));
                    op.setStandardHours(MapUtils.decimal(o, "standardHours"));
                    op.setStatus(MesConstants.STATUS_NORMAL);
                    op.setCreateBy("ERP");
                    routeOperationMapper.insertMdRouteOperation(op);
                }
            }
            count++;
        }
        return success("同步工艺 " + count + " 条");
    }

    /**
     * 生产需求下达（IR-ERP-005），幂等
     */
    @PostMapping("/production-demands")
    public AjaxResult productionDemands(@RequestBody MesDtos.DemandRequest request)
    {
        messageService.recordInbound(null, "{\"type\":\"ProductionDemand\",\"sourceOrderNo\":\"" + request.sourceOrderNo + "\"}");
        DemandReceiveService.Result result = demandReceiveService.receive(request, null);
        return result.success ? success(result.message) : error(result.message);
    }

    /**
     * 工单状态/报工回执（IR-ERP-006/007 回执）
     */
    @PostMapping("/ack")
    public AjaxResult ack(@RequestBody Map<String, Object> body)
    {
        String key = MapUtils.str(body, "idempotencyKey");
        String status = MapUtils.str(body, "status", "CONFIRMED");
        com.ruoyi.mes.integration.domain.IntMessage msg = messageService.findByIdempotencyKey(key);
        if (msg == null)
        {
            return error("未找到对应消息：" + key);
        }
        messageService.markResult(msg, !"FAILED".equals(status), MapUtils.str(body, "errorMsg"));
        return success();
    }

    /**
     * 触发 ERP 对账（IR-ERP-008）
     */
    @PostMapping("/reconcile")
    public AjaxResult reconcile(@RequestBody(required = false) Map<String, Object> body)
    {
        return success(reconciliationService.run("ERP", new Date()));
    }

    private void upsertProductVersion(Long productId, String productCode, Map<String, Object> v)
    {
        String versionNo = MapUtils.str(v, "versionNo");
        MdProductVersion query = new MdProductVersion();
        query.setProductId(productId);
        query.setVersionNo(versionNo);
        MdProductVersion version = null;
        for (MdProductVersion pv : productVersionMapper.selectMdProductVersionList(query))
        {
            if (versionNo != null && versionNo.equals(pv.getVersionNo()))
            {
                version = pv;
                break;
            }
        }
        boolean exists = version != null;
        if (!exists)
        {
            version = new MdProductVersion();
            version.setProductId(productId);
            version.setProductCode(productCode);
            version.setVersionNo(versionNo);
            version.setCreateBy("ERP");
        }
        version.setDocStatus(MapUtils.str(v, "docStatus", DocumentStatus.PUBLISHED.name()));
        version.setStatus(MesConstants.STATUS_NORMAL);
        version.setUpdateBy("ERP");
        if (exists)
        {
            productVersionMapper.updateMdProductVersion(version);
        }
        else
        {
            productVersionMapper.insertMdProductVersion(version);
        }
    }

    private MdMaterial findMaterial(String code)
    {
        MdMaterial q = new MdMaterial();
        q.setMaterialCode(code);
        for (MdMaterial m : materialMapper.selectMdMaterialList(q))
        {
            if (code.equals(m.getMaterialCode()))
            {
                return m;
            }
        }
        return null;
    }

    private MdProduct findProduct(String code)
    {
        MdProduct q = new MdProduct();
        q.setProductCode(code);
        for (MdProduct p : productMapper.selectMdProductList(q))
        {
            if (code.equals(p.getProductCode()))
            {
                return p;
            }
        }
        return null;
    }

    private MdBom findBom(String code)
    {
        MdBom q = new MdBom();
        q.setBomCode(code);
        for (MdBom b : bomMapper.selectMdBomList(q))
        {
            if (code.equals(b.getBomCode()))
            {
                return b;
            }
        }
        return null;
    }

    private MdRoute findRoute(String code)
    {
        MdRoute q = new MdRoute();
        q.setRouteCode(code);
        for (MdRoute r : routeMapper.selectMdRouteList(q))
        {
            if (code.equals(r.getRouteCode()))
            {
                return r;
            }
        }
        return null;
    }
}
