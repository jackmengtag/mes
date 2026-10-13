package com.ruoyi.mes.integration.controller;

import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.mes.ex.domain.ExProductSerial;
import com.ruoyi.mes.ex.mapper.ExProductSerialMapper;
import com.ruoyi.mes.integration.dto.MesDtos;
import com.ruoyi.mes.integration.service.ReconciliationService;
import com.ruoyi.mes.integration.support.MessageService;
import com.ruoyi.mes.mt.service.MaterialService;
import com.ruoyi.mes.qc.service.QualityService;

/**
 * WMS 集成接口（架构文档 7.5 / IR-WMS-001~010）
 *
 * @author ruoyi
 */
@Anonymous
@RestController
@RequestMapping("/integration/v1/wms")
public class WmsIntegrationController extends BaseController
{
    @Autowired
    private MaterialService materialService;

    @Autowired
    private QualityService qualityService;

    @Autowired
    private ExProductSerialMapper serialMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ReconciliationService reconciliationService;

    /**
     * 物料消耗上报（IR-WMS-004/005），幂等
     */
    @PostMapping("/material-consumptions")
    public AjaxResult consumption(@RequestBody MesDtos.MaterialConsumptionRequest request)
    {
        messageService.recordInbound(null, "{\"type\":\"MaterialConsumption\",\"consumeNo\":\"" + request.consumeNo + "\"}");
        return success(materialService.confirmConsumption(request, null));
    }

    /**
     * 拣料与配送确认（IR-WMS-002/003）
     */
    @PostMapping("/picking-results")
    public AjaxResult picking(@RequestBody MesDtos.PickingResultRequest request)
    {
        materialService.applyPicking(request);
        return success();
    }

    /**
     * 成品入库结果（IR-WMS-007）
     */
    @PostMapping("/inbound-results")
    public AjaxResult inbound(@RequestBody MesDtos.InboundResultRequest request)
    {
        qualityService.confirmInbound(request.workOrderNo, request.receiptNo, "WMS");
        return success();
    }

    /**
     * 库存冻结与解冻（IR-WMS-008）
     */
    @PostMapping("/stock-freeze")
    public AjaxResult freeze(@RequestBody MesDtos.FreezeRequest request)
    {
        messageService.recordInbound(null, "{\"type\":\"StockFreeze\",\"scopeValue\":\"" + request.scopeValue + "\"}");
        String target = "Y".equals(request.freezeFlag) ? "FROZEN" : "RELEASED";
        if ("SERIAL".equals(request.scopeType)) {
            ExProductSerial q = new ExProductSerial();
            q.setSerialNo(request.scopeValue);
            for (ExProductSerial s : serialMapper.selectExProductSerialList(q))
            {
                if (request.scopeValue.equals(s.getSerialNo()))
                {
                    s.setWarehouseStatus(target);
                    s.setUpdateBy("WMS");
                    serialMapper.updateExProductSerial(s);
                }
            }
        }
        return success();
    }

    /**
     * 触发 WMS 对账（IR-WMS-009）
     */
    @PostMapping("/reconcile")
    public AjaxResult reconcile(@RequestBody(required = false) Map<String, Object> body)
    {
        return success(reconciliationService.run("WMS", new Date()));
    }
}
