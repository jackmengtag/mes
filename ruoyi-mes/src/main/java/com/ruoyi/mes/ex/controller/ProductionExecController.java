package com.ruoyi.mes.ex.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.ex.service.ProductionExecService;
import com.ruoyi.mes.integration.dto.MesDtos;

/**
 * 生产执行控制器（FR-EX-003/004/005）：开工、报工、序列号绑定
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ex/exec")
public class ProductionExecController extends BaseController
{
    @Autowired
    private ProductionExecService productionExecService;

    /** 工位开工（前置条件校验） */
    @PreAuthorize("@ss.hasPermi('mes:ex:report:add')")
    @Log(title = "工序开工", businessType = BusinessType.UPDATE)
    @PostMapping("/start/{taskId}")
    public AjaxResult start(@PathVariable("taskId") Long taskId, @RequestBody(required = false) Map<String, Object> body)
    {
        String operator = body != null && body.get("operatorNo") != null
                ? String.valueOf(body.get("operatorNo")) : getUsername();
        return success(productionExecService.startOperation(taskId, operator, null));
    }

    /** 工序报工（幂等） */
    @PreAuthorize("@ss.hasPermi('mes:ex:report:add')")
    @Log(title = "工序报工", businessType = BusinessType.INSERT)
    @PostMapping("/report")
    public AjaxResult report(@RequestBody MesDtos.ProductionReportRequest request)
    {
        if (request.reportNo == null || request.reportNo.isEmpty())
        {
            request.reportNo = com.ruoyi.mes.common.utils.MesSeqUtils.generate(com.ruoyi.mes.common.constant.MesConstants.PREFIX_REPORT);
        }
        return success(productionExecService.report(request, null));
    }

    /** 绑定产品序列号 */
    @PreAuthorize("@ss.hasPermi('mes:ex:serial:add')")
    @Log(title = "序列号绑定", businessType = BusinessType.INSERT)
    @PostMapping("/bindSerial")
    public AjaxResult bindSerial(@RequestBody Map<String, Object> body)
    {
        String serialNo = body.get("serialNo") == null ? null : String.valueOf(body.get("serialNo"));
        Long orderId = body.get("orderId") == null ? null : Long.valueOf(String.valueOf(body.get("orderId")));
        return success(productionExecService.bindSerial(serialNo, orderId, getUsername()));
    }
}
