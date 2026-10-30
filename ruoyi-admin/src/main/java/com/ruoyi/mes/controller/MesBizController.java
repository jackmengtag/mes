package com.ruoyi.mes.controller;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.domain.MesArrival;
import com.ruoyi.mes.domain.MesReport;
import com.ruoyi.mes.domain.MesShipment;
import com.ruoyi.mes.domain.MesStock;
import com.ruoyi.mes.service.MesBizService;

/**
 * MES 业务联动Controller
 * 覆盖采购到货入库、销售出库、生产报工、不合格处置、库存调整、计划下达、U8C 重推等跨模块动作
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/biz")
public class MesBizController extends BaseController
{
    @Autowired
    private MesBizService bizService;

    /** 采购到货登记：到货单 + 采购订单回写 + 入库 + 库存 + 凭证 */
    @PreAuthorize("@ss.hasPermi('mes:arrival:add')")
    @Log(title = "采购到货登记", businessType = BusinessType.INSERT)
    @PostMapping("/arrival")
    public AjaxResult registerArrival(@RequestBody MesArrival arrival)
    {
        Map<String, Object> data = bizService.registerArrival(arrival, getUsername());
        return AjaxResult.success("到货登记成功，已生成入库与存货凭证", data);
    }

    /** 销售出库：扣减库存 + 出库单 + 存货凭证 */
    @PreAuthorize("@ss.hasPermi('mes:shipment:add')")
    @Log(title = "销售出库", businessType = BusinessType.INSERT)
    @PostMapping("/shipment")
    public AjaxResult ship(@RequestBody MesShipment shipment)
    {
        Map<String, Object> data = bizService.shipOrder(shipment, getUsername());
        return AjaxResult.success("出库成功，库存已扣减并生成存货凭证", data);
    }

    /** 生产报工：工单进度回写 + 不良自动生成质量问题 */
    @PreAuthorize("@ss.hasPermi('mes:report:add')")
    @Log(title = "生产报工", businessType = BusinessType.INSERT)
    @PostMapping("/report")
    public AjaxResult report(@RequestBody MesReport report)
    {
        Map<String, Object> data = bizService.submitReport(report, getUsername());
        return AjaxResult.success("报工成功", data);
    }

    /** 不合格品处置 */
    @PreAuthorize("@ss.hasPermi('mes:quality:dispose')")
    @Log(title = "不合格品处置", businessType = BusinessType.UPDATE)
    @PostMapping("/issue/dispose")
    public AjaxResult dispose(@RequestBody Map<String, Object> body)
    {
        Long issueId = body.get("issueId") == null ? null : Long.valueOf(String.valueOf(body.get("issueId")));
        String dispose = body.get("dispose") == null ? null : String.valueOf(body.get("dispose"));
        String status = body.get("status") == null ? null : String.valueOf(body.get("status"));
        return AjaxResult.success(bizService.disposeIssue(issueId, dispose, status, getUsername()));
    }

    /** 批量记账 */
    @PreAuthorize("@ss.hasPermi('mes:voucher:post')")
    @Log(title = "存货记账", businessType = BusinessType.UPDATE)
    @PostMapping("/voucher/post")
    public AjaxResult postVoucher(@RequestBody Long[] ids)
    {
        int count = bizService.postVouchers(ids);
        return AjaxResult.success("已记账 " + count + " 张凭证", count);
    }

    /** U8C 凭证重推 */
    @PreAuthorize("@ss.hasPermi('mes:voucher:sync')")
    @Log(title = "U8C凭证同步", businessType = BusinessType.UPDATE)
    @PostMapping("/voucher/sync/{voucherId}")
    public AjaxResult syncVoucher(@PathVariable Long voucherId)
    {
        return AjaxResult.success("同步成功", bizService.syncVoucher(voucherId));
    }

    /** 其他入库 / 其他出库 */
    @PreAuthorize("@ss.hasPermi('mes:stock:adjust')")
    @Log(title = "库存调整", businessType = BusinessType.UPDATE)
    @PostMapping("/stock/adjust")
    public AjaxResult adjustStock(@RequestBody Map<String, Object> body)
    {
        MesStock req = new MesStock();
        req.setMaterialCode(str(body.get("materialCode")));
        req.setMaterialName(str(body.get("materialName")));
        req.setWarehouseCode(str(body.get("warehouseCode")));
        req.setBatchNo(str(body.get("batchNo")));
        req.setUnit(str(body.get("unit")));
        req.setSpec(str(body.get("spec")));
        req.setLocation(str(body.get("location")));
        req.setQty(decimal(body.get("qty")));
        req.setPrice(decimal(body.get("price")));
        String type = str(body.get("type"));
        return AjaxResult.success(bizService.adjustStock(type, req, getUsername()));
    }

    /** 仓库调拨 */
    @PreAuthorize("@ss.hasPermi('mes:stock:transfer')")
    @Log(title = "库存调拨", businessType = BusinessType.UPDATE)
    @PostMapping("/stock/transfer")
    public AjaxResult transferStock(@RequestBody Map<String, Object> body)
    {
        MesStock req = new MesStock();
        req.setMaterialCode(str(body.get("materialCode")));
        req.setWarehouseCode(str(body.get("warehouseCode")));
        req.setBatchNo(str(body.get("batchNo")));
        req.setQty(decimal(body.get("qty")));
        return AjaxResult.success(bizService.transferStock(req, str(body.get("toWarehouse")), getUsername()));
    }

    /** 计划发布 / 下达 */
    @PreAuthorize("@ss.hasPermi('mes:plan:publish')")
    @Log(title = "计划发布", businessType = BusinessType.UPDATE)
    @PostMapping("/plan/publish/{planId}")
    public AjaxResult publishPlan(@PathVariable Long planId)
    {
        return AjaxResult.success(bizService.publishPlan(planId));
    }

    /** 计划生成工单 */
    @PreAuthorize("@ss.hasPermi('mes:plan:edit')")
    @Log(title = "计划生成工单", businessType = BusinessType.INSERT)
    @PostMapping("/plan/workOrder/{planId}")
    public AjaxResult planToWorkOrder(@PathVariable Long planId)
    {
        return AjaxResult.success("工单已生成", bizService.planToWorkOrder(planId, getUsername()));
    }

    /** 协同异常生成质量问题 */
    @PreAuthorize("@ss.hasPermi('mes:quality:add')")
    @Log(title = "协同异常转质量", businessType = BusinessType.INSERT)
    @PostMapping("/collab/quality/{collabId}")
    public AjaxResult collabToQuality(@PathVariable Long collabId)
    {
        return AjaxResult.success(bizService.collabToQuality(collabId, getUsername()));
    }

    /** 设备指令 */
    @PreAuthorize("@ss.hasPermi('mes:equipment:command')")
    @Log(title = "设备指令", businessType = BusinessType.UPDATE)
    @PostMapping("/equipment/command")
    public AjaxResult equipmentCommand(@RequestBody Map<String, Object> body)
    {
        return AjaxResult.success(bizService.equipmentCommand(str(body.get("equipmentCode")), str(body.get("command")), getUsername()));
    }

    /** 报警处理 */
    @PreAuthorize("@ss.hasPermi('mes:alarm:handle')")
    @Log(title = "设备报警处理", businessType = BusinessType.UPDATE)
    @PutMapping("/alarm/handle/{alarmId}")
    public AjaxResult handleAlarm(@PathVariable Long alarmId)
    {
        return AjaxResult.success(bizService.handleAlarm(alarmId, getUsername()));
    }

    /** 接口测试连接 */
    @PreAuthorize("@ss.hasPermi('mes:interface:test')")
    @Log(title = "接口测试", businessType = BusinessType.OTHER)
    @PostMapping("/interface/test/{interfaceId}")
    public AjaxResult testInterface(@PathVariable Long interfaceId)
    {
        return AjaxResult.success(bizService.testInterface(interfaceId));
    }

    /** 质量追溯详情（序列号 / 批次） */
    @PreAuthorize("@ss.hasPermi('mes:trace:query')")
    @GetMapping("/trace/detail")
    public AjaxResult traceDetail(@RequestParam(required = false) String keyword)
    {
        return AjaxResult.success(bizService.traceDetail(keyword));
    }

    private String str(Object v)
    {
        return v == null ? null : String.valueOf(v);
    }

    private BigDecimal decimal(Object v)
    {
        if (v == null || String.valueOf(v).isEmpty())
        {
            return null;
        }
        return new BigDecimal(String.valueOf(v));
    }
}
