package com.ruoyi.mes.mt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.integration.dto.MesDtos;
import com.ruoyi.mes.mt.service.MaterialService;

/**
 * 物料业务流控制器（FR-MT-001~006）：物料需求、齐套、消耗、备料
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/mt/workflow")
public class MaterialWorkflowController extends BaseController
{
    @Autowired
    private MaterialService materialService;

    /** 查询工单物料需求 */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:list')")
    @GetMapping("/requirement/{orderId}")
    public AjaxResult requirement(@PathVariable("orderId") Long orderId)
    {
        return success(materialService.listRequirementByOrder(orderId));
    }

    /** 判断关键物料是否齐套 */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:list')")
    @GetMapping("/kit/{orderId}")
    public AjaxResult kit(@PathVariable("orderId") Long orderId)
    {
        AjaxResult ajax = success();
        ajax.put("kitted", materialService.checkKit(orderId));
        return ajax;
    }

    /** 物料消耗确认（幂等） */
    @PreAuthorize("@ss.hasPermi('mes:mt:consumption:add')")
    @Log(title = "物料消耗确认", businessType = BusinessType.INSERT)
    @PostMapping("/consumption")
    public AjaxResult consumption(@RequestBody MesDtos.MaterialConsumptionRequest request)
    {
        return success(materialService.confirmConsumption(request, null));
    }

    /** 应用 WMS 备料/配送结果 */
    @PreAuthorize("@ss.hasPermi('mes:mt:requirement:edit')")
    @Log(title = "备料结果确认", businessType = BusinessType.UPDATE)
    @PostMapping("/picking")
    public AjaxResult picking(@RequestBody MesDtos.PickingResultRequest request)
    {
        materialService.applyPicking(request);
        return success();
    }
}
