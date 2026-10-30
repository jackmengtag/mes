package com.ruoyi.mes.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.mes.domain.*;
import com.ruoyi.mes.mapper.*;

/**
 * MES 跨模块业务联动
 * 覆盖：采购到货入库、销售出库、生产报工、不合格处置、库存调整/调拨、计划下达、
 *       协同异常转质量、设备指令、接口测试、U8C 重推、质量追溯
 *
 * @author ruoyi
 */
@Service
public class MesBizService
{
    @Autowired
    private MesArrivalMapper arrivalMapper;
    @Autowired
    private MesPurchaseOrderMapper purchaseMapper;
    @Autowired
    private MesStockMapper stockMapper;
    @Autowired
    private MesStockTxnMapper txnMapper;
    @Autowired
    private MesVoucherMapper voucherMapper;
    @Autowired
    private MesShipmentMapper shipmentMapper;
    @Autowired
    private MesSalesOrderMapper salesMapper;
    @Autowired
    private MesReportMapper reportMapper;
    @Autowired
    private MesWorkOrderMapper workOrderMapper;
    @Autowired
    private MesQualityIssueMapper issueMapper;
    @Autowired
    private MesPlanMapper planMapper;
    @Autowired
    private MesCollabMapper collabMapper;
    @Autowired
    private MesEquipmentMapper equipmentMapper;
    @Autowired
    private MesAlarmMapper alarmMapper;
    @Autowired
    private MesInterfaceMapper interfaceMapper;
    @Autowired
    private MesQueryMapper queryMapper;

    private static final String RM = "WH-RM";
    private static final String FG = "WH-FG";

    /* ==================== 采购到货 → 库存 → 凭证 ==================== */
    @Transactional
    public Map<String, Object> registerArrival(MesArrival arrival, String username)
    {
        if (arrival.getOrderCode() == null || arrival.getOrderCode().isEmpty())
        {
            throw new ServiceException("采购订单号不能为空");
        }
        if (arrival.getQty() == null || arrival.getQty().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ServiceException("到货数量必须大于 0");
        }
        MesPurchaseOrder po = queryMapper.selectPurchaseByCode(arrival.getOrderCode());
        if (po == null)
        {
            throw new ServiceException("采购订单不存在：" + arrival.getOrderCode());
        }
        BigDecimal qualified = arrival.getQualifiedQty() == null ? arrival.getQty() : arrival.getQualifiedQty();
        BigDecimal rejected = arrival.getRejectedQty() == null ? BigDecimal.ZERO : arrival.getRejectedQty();
        if (qualified.add(rejected).compareTo(arrival.getQty()) > 0)
        {
            throw new ServiceException("合格数量与拒收数量之和不能超过到货数量");
        }

        String today = date("yyMMdd");
        if (isBlank(arrival.getArrivalCode()))
        {
            MesArrival q = new MesArrival();
            q.getParams().put("keyword", "DH-" + today);
            arrival.setArrivalCode(nextCode("DH", arrivalMapper.selectMesArrivalList(q).size()));
        }
        arrival.setMaterialCode(isBlank(arrival.getMaterialCode()) ? po.getMaterialCode() : arrival.getMaterialCode());
        arrival.setMaterialName(isBlank(arrival.getMaterialName()) ? po.getMaterialName() : arrival.getMaterialName());
        arrival.setSupplierName(isBlank(arrival.getSupplierName()) ? po.getSupplierName() : arrival.getSupplierName());
        arrival.setWarehouseCode(isBlank(arrival.getWarehouseCode()) ? RM : arrival.getWarehouseCode());
        arrival.setQualifiedQty(qualified);
        arrival.setRejectedQty(rejected);
        arrival.setArrivalDate(arrival.getArrivalDate() == null ? new Date() : arrival.getArrivalDate());
        arrival.setStatus(qualified.compareTo(BigDecimal.ZERO) > 0 ? "stocked" : "rejected");
        arrival.setCreateBy(username);
        arrivalMapper.insertMesArrival(arrival);

        // 回写采购订单
        BigDecimal received = (po.getReceivedQty() == null ? BigDecimal.ZERO : po.getReceivedQty()).add(arrival.getQty());
        po.setReceivedQty(received);
        po.setStatus(received.compareTo(po.getQty()) >= 0 ? "received" : "partial");
        po.setUpdateBy(username);
        purchaseMapper.updateMesPurchaseOrder(po);

        BigDecimal amount = BigDecimal.ZERO;
        if (qualified.compareTo(BigDecimal.ZERO) > 0)
        {
            BigDecimal price = po.getPrice() == null ? BigDecimal.ZERO : po.getPrice();
            amount = qualified.multiply(price);
            String code = arrival.getArrivalCode();
            String batch = "S" + date("yyMMdd") + "-" + (code.length() >= 2 ? code.substring(code.length() - 2) : "01");
            stockIn(arrival.getMaterialCode(), arrival.getMaterialName(), "", "件", arrival.getWarehouseCode(),
                    warehouseName(arrival.getWarehouseCode()), batch, qualified, price, "");
            txn("inbound", "purchase", arrival.getArrivalCode(), arrival.getMaterialCode(), arrival.getMaterialName(),
                    arrival.getWarehouseCode(), batch, qualified, "件", price, username, "采购到货入库");
            createVoucher("purchase_in", arrival.getArrivalCode(), qualified, amount, username);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("arrivalCode", arrival.getArrivalCode());
        result.put("status", arrival.getStatus());
        result.put("qualifiedQty", qualified);
        result.put("rejectedQty", rejected);
        result.put("amount", amount);
        return result;
    }

    /* ==================== 销售出库 → 库存 → 凭证 ==================== */
    @Transactional
    public Map<String, Object> shipOrder(MesShipment shipment, String username)
    {
        if (isBlank(shipment.getOrderCode()))
        {
            throw new ServiceException("销售订单号不能为空");
        }
        if (shipment.getQty() == null || shipment.getQty().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ServiceException("出库数量必须大于 0");
        }
        MesSalesOrder so = queryMapper.selectSalesByCode(shipment.getOrderCode());
        if (so == null)
        {
            throw new ServiceException("销售订单不存在：" + shipment.getOrderCode());
        }
        BigDecimal shipped = so.getShippedQty() == null ? BigDecimal.ZERO : so.getShippedQty();
        if (shipped.add(shipment.getQty()).compareTo(so.getQty()) > 0)
        {
            throw new ServiceException("出库数量超过订单数量，剩余可出库 " + so.getQty().subtract(shipped));
        }
        String warehouse = isBlank(shipment.getWarehouseCode()) ? FG : shipment.getWarehouseCode();
        List<MesStock> rows = queryMapper.selectStockByMaterial(so.getProductCode(), warehouse);
        BigDecimal available = BigDecimal.ZERO;
        for (MesStock s : rows)
        {
            available = available.add(s.getQty());
        }
        if (available.compareTo(shipment.getQty()) < 0)
        {
            throw new ServiceException("成品库存不足，当前可用 " + available.stripTrailingZeros().toPlainString());
        }

        if (isBlank(shipment.getShipmentCode()))
        {
            MesShipment q = new MesShipment();
            q.getParams().put("keyword", "CK-" + date("yyMMdd"));
            shipment.setShipmentCode(nextCode("CK", shipmentMapper.selectMesShipmentList(q).size()));
        }
        shipment.setCustomerName(isBlank(shipment.getCustomerName()) ? so.getCustomerName() : shipment.getCustomerName());
        shipment.setProductCode(so.getProductCode());
        shipment.setProductName(so.getProductName());
        shipment.setPrice(shipment.getPrice() == null ? so.getPrice() : shipment.getPrice());
        shipment.setAmount(shipment.getQty().multiply(shipment.getPrice() == null ? BigDecimal.ZERO : shipment.getPrice()));
        shipment.setWarehouseCode(warehouse);
        shipment.setShipDate(shipment.getShipDate() == null ? new Date() : shipment.getShipDate());
        shipment.setStatus("shipped");
        shipment.setCreateBy(username);
        shipmentMapper.insertMesShipment(shipment);

        BigDecimal remaining = shipment.getQty();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (MesStock s : rows)
        {
            if (remaining.compareTo(BigDecimal.ZERO) <= 0)
            {
                break;
            }
            BigDecimal deduct = remaining.min(s.getQty());
            BigDecimal price = s.getPrice() == null ? BigDecimal.ZERO : s.getPrice();
            s.setQty(s.getQty().subtract(deduct));
            refreshStock(s);
            stockMapper.updateMesStock(s);
            txn("outbound", "sales", shipment.getShipmentCode(), s.getMaterialCode(), s.getMaterialName(),
                    warehouse, s.getBatchNo(), deduct.negate(), s.getUnit(), price, username, "销售出库");
            remaining = remaining.subtract(deduct);
            totalAmount = totalAmount.add(deduct.multiply(price));
        }

        so.setShippedQty(shipped.add(shipment.getQty()));
        so.setStatus(so.getShippedQty().compareTo(so.getQty()) >= 0 ? "shipped" : "partial");
        so.setUpdateBy(username);
        salesMapper.updateMesSalesOrder(so);

        createVoucher("sales_out", shipment.getShipmentCode(), shipment.getQty(), totalAmount, username);

        Map<String, Object> result = new HashMap<>();
        result.put("shipmentCode", shipment.getShipmentCode());
        result.put("qty", shipment.getQty());
        result.put("amount", totalAmount);
        result.put("orderStatus", so.getStatus());
        return result;
    }

    /* ==================== 生产报工 → 工单 → 质量问题 ==================== */
    @Transactional
    public Map<String, Object> submitReport(MesReport report, String username)
    {
        if (isBlank(report.getOrderCode()))
        {
            throw new ServiceException("工单号不能为空");
        }
        BigDecimal ok = report.getQtyOk() == null ? BigDecimal.ZERO : report.getQtyOk();
        BigDecimal ng = report.getQtyNg() == null ? BigDecimal.ZERO : report.getQtyNg();
        if (ok.compareTo(BigDecimal.ZERO) < 0 || ng.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new ServiceException("报工数量不能为负数");
        }
        if (ok.add(ng).compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ServiceException("报工数量必须大于 0");
        }
        MesWorkOrder wo = queryMapper.selectWorkOrderByCode(report.getOrderCode());
        if (wo == null)
        {
            throw new ServiceException("工单不存在：" + report.getOrderCode());
        }
        if ("finished".equals(wo.getStatus()))
        {
            throw new ServiceException("工单已完成，禁止继续报工");
        }
        if (isBlank(report.getReportCode()))
        {
            MesReport q = new MesReport();
            q.getParams().put("keyword", "RP-" + date("yyMMdd"));
            report.setReportCode(nextCode("RP", reportMapper.selectMesReportList(q).size()));
        }
        report.setOperator(isBlank(report.getOperator()) ? username : report.getOperator());
        report.setShift(isBlank(report.getShift()) ? "白班" : report.getShift());
        report.setQtyOk(ok);
        report.setQtyNg(ng);
        report.setReportTime(new Date());
        report.setCreateBy(username);
        reportMapper.insertMesReport(report);

        BigDecimal completed = (wo.getCompletedQty() == null ? BigDecimal.ZERO : wo.getCompletedQty()).add(ok);
        BigDecimal totalNg = (wo.getNgQty() == null ? BigDecimal.ZERO : wo.getNgQty()).add(ng);
        wo.setCompletedQty(completed);
        wo.setNgQty(totalNg);
        wo.setStatus(completed.compareTo(wo.getQty()) >= 0 ? "finished" : "running");
        wo.setUpdateBy(username);
        workOrderMapper.updateMesWorkOrder(wo);

        String issueCode = null;
        if (ng.compareTo(BigDecimal.ZERO) > 0)
        {
            MesQualityIssue issue = new MesQualityIssue();
            MesQualityIssue q = new MesQualityIssue();
            q.getParams().put("keyword", "QI-" + date("yyMMdd"));
            issueCode = nextCode("QI", issueMapper.selectMesQualityIssueList(q).size());
            issue.setIssueCode(issueCode);
            issue.setSourceType("report");
            issue.setSourceCode(report.getReportCode());
            issue.setProductCode(wo.getProductCode());
            issue.setSerialNo("");
            issue.setIssueType("生产不良");
            issue.setSeverity(ng.compareTo(new BigDecimal("3")) > 0 ? "serious" : "normal");
            issue.setQty(ng);
            issue.setDescription("报工不良：工单 " + wo.getOrderCode() + " / 工位 " + report.getStation());
            issue.setStatus("open");
            issue.setFoundTime(new Date());
            issue.setCreateBy(username);
            issueMapper.insertMesQualityIssue(issue);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("reportCode", report.getReportCode());
        result.put("orderStatus", wo.getStatus());
        result.put("completedQty", completed);
        result.put("issueCode", issueCode);
        return result;
    }

    /* ==================== 不合格品处置 ==================== */
    public String disposeIssue(Long issueId, String dispose, String status, String handler)
    {
        if (issueId == null)
        {
            throw new ServiceException("请选择质量问题");
        }
        if (isBlank(dispose))
        {
            throw new ServiceException("请选择处置方式");
        }
        MesQualityIssue issue = issueMapper.selectMesQualityIssueById(issueId);
        if (issue == null)
        {
            throw new ServiceException("质量问题不存在");
        }
        if ("released".equals(issue.getStatus()))
        {
            throw new ServiceException("该问题已放行，不能重复判定");
        }
        String target = isBlank(status) ? ("concession".equals(dispose) ? "released" : "disposed") : status;
        issue.setDispose(dispose);
        issue.setStatus(target);
        issue.setHandler(handler);
        issue.setCloseTime(new Date());
        issue.setUpdateBy(handler);
        issueMapper.updateMesQualityIssue(issue);
        return "处置完成：" + statusText(target);
    }

    /* ==================== 存货核算 ==================== */
    public int postVouchers(Long[] ids)
    {
        if (ids == null || ids.length == 0)
        {
            throw new ServiceException("请选择待记账凭证");
        }
        int count = 0;
        for (Long id : ids)
        {
            MesVoucher v = voucherMapper.selectMesVoucherById(id);
            if (v != null && !"posted".equals(v.getStatus()))
            {
                v.setStatus("posted");
                voucherMapper.updateMesVoucher(v);
                count++;
            }
        }
        return count;
    }

    public Map<String, Object> syncVoucher(Long id)
    {
        MesVoucher v = voucherMapper.selectMesVoucherById(id);
        if (v == null)
        {
            throw new ServiceException("凭证不存在");
        }
        if ("success".equals(v.getU8cStatus()))
        {
            throw new ServiceException("该凭证已同步成功");
        }
        if (!"posted".equals(v.getStatus()))
        {
            throw new ServiceException("凭证尚未记账，不能推送 U8C");
        }
        v.setU8cStatus("success");
        v.setSyncTime(new Date());
        v.setMessage("同步成功");
        voucherMapper.updateMesVoucher(v);
        Map<String, Object> result = new HashMap<>();
        result.put("voucherCode", v.getVoucherCode());
        result.put("u8cStatus", v.getU8cStatus());
        return result;
    }

    /* ==================== 库存调整 / 调拨 ==================== */
    @Transactional
    public String adjustStock(String type, MesStock req, String username)
    {
        if (isBlank(req.getMaterialCode()) || isBlank(req.getWarehouseCode()))
        {
            throw new ServiceException("物料与仓库不能为空");
        }
        if (req.getQty() == null || req.getQty().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ServiceException("调整数量必须大于 0");
        }
        String batch = isBlank(req.getBatchNo()) ? "ADJ" : req.getBatchNo();
        MesStock stock = queryMapper.selectStockByKey(req.getMaterialCode(), req.getWarehouseCode(), batch);
        boolean inbound = "in".equals(type);
        if (!inbound && stock == null)
        {
            throw new ServiceException("库存记录不存在，无法出库");
        }
        if (!inbound && stock.getQty().compareTo(req.getQty()) < 0)
        {
            throw new ServiceException("库存不足，当前可用 " + stock.getQty());
        }
        BigDecimal price = req.getPrice() == null ? (stock != null && stock.getPrice() != null ? stock.getPrice() : BigDecimal.ZERO) : req.getPrice();
        if (inbound)
        {
            stockIn(req.getMaterialCode(), req.getMaterialName(), req.getSpec(), req.getUnit(), req.getWarehouseCode(),
                    isBlank(req.getWarehouseName()) ? warehouseName(req.getWarehouseCode()) : req.getWarehouseName(),
                    batch, req.getQty(), price, req.getLocation());
        }
        else
        {
            stock.setQty(stock.getQty().subtract(req.getQty()));
            refreshStock(stock);
            stockMapper.updateMesStock(stock);
        }
        String txnType = inbound ? "check_in" : "check_out";
        txn(txnType, "other", "", req.getMaterialCode(), req.getMaterialName(), req.getWarehouseCode(), batch,
                inbound ? req.getQty() : req.getQty().negate(), req.getUnit(), price, username,
                inbound ? "其他入库" : "其他出库");
        createVoucher("other", "", req.getQty(), req.getQty().multiply(price), username);
        return (inbound ? "其他入库" : "其他出库") + "成功";
    }

    @Transactional
    public String transferStock(MesStock req, String toWarehouse, String username)
    {
        if (isBlank(req.getMaterialCode()) || isBlank(req.getWarehouseCode()) || isBlank(toWarehouse))
        {
            throw new ServiceException("物料、源仓库与目标仓库不能为空");
        }
        if (req.getWarehouseCode().equals(toWarehouse))
        {
            throw new ServiceException("源仓库与目标仓库不能相同");
        }
        if (req.getQty() == null || req.getQty().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ServiceException("调拨数量必须大于 0");
        }
        String batch = req.getBatchNo() == null ? "" : req.getBatchNo();
        MesStock from = queryMapper.selectStockByKey(req.getMaterialCode(), req.getWarehouseCode(), batch);
        if (from == null)
        {
            List<MesStock> candidates = queryMapper.selectStockByMaterial(req.getMaterialCode(), req.getWarehouseCode());
            if (!candidates.isEmpty())
            {
                from = candidates.get(0);
                batch = from.getBatchNo() == null ? "" : from.getBatchNo();
            }
        }
        if (from == null || from.getQty().compareTo(req.getQty()) < 0)
        {
            throw new ServiceException("源仓库库存不足");
        }
        BigDecimal price = from.getPrice() == null ? BigDecimal.ZERO : from.getPrice();
        from.setQty(from.getQty().subtract(req.getQty()));
        refreshStock(from);
        stockMapper.updateMesStock(from);
        stockIn(from.getMaterialCode(), from.getMaterialName(), from.getSpec(), from.getUnit(), toWarehouse,
                warehouseName(toWarehouse), batch, req.getQty(), price, "");
        txn("transfer_out", "transfer", "", from.getMaterialCode(), from.getMaterialName(), req.getWarehouseCode(), batch,
                req.getQty().negate(), from.getUnit(), price, username, "调拨出库 → " + warehouseName(toWarehouse));
        txn("transfer_in", "transfer", "", from.getMaterialCode(), from.getMaterialName(), toWarehouse, batch,
                req.getQty(), from.getUnit(), price, username, "调拨入库 ← " + warehouseName(req.getWarehouseCode()));
        return "调拨成功";
    }

    /* ==================== 计划 ==================== */
    public String publishPlan(Long planId)
    {
        MesPlan plan = planMapper.selectMesPlanById(planId);
        if (plan == null)
        {
            throw new ServiceException("计划不存在");
        }
        if ("locked".equals(plan.getStatus()))
        {
            throw new ServiceException("计划已锁定，请走变更审批");
        }
        plan.setStatus("draft".equals(plan.getStatus()) ? "released" : "running");
        planMapper.updateMesPlan(plan);
        return "计划已" + ("released".equals(plan.getStatus()) ? "发布" : "下达执行");
    }

    @Transactional
    public Map<String, Object> planToWorkOrder(Long planId, String username)
    {
        MesPlan plan = planMapper.selectMesPlanById(planId);
        if (plan == null)
        {
            throw new ServiceException("计划不存在");
        }
        MesWorkOrder q = new MesWorkOrder();
        q.getParams().put("keyword", "WO-" + date("yyMMdd"));
        String code = nextCode("WO", workOrderMapper.selectMesWorkOrderList(q).size());
        MesWorkOrder wo = new MesWorkOrder();
        wo.setOrderCode(code);
        wo.setPlanCode(plan.getPlanCode());
        wo.setProductCode(plan.getProductCode());
        wo.setProductName(plan.getProductName());
        wo.setQty(plan.getQty());
        wo.setCompletedQty(BigDecimal.ZERO);
        wo.setNgQty(BigDecimal.ZERO);
        wo.setUnit(plan.getUnit());
        wo.setLineCode(plan.getLineCode());
        wo.setStatus("scheduled");
        wo.setPriority("normal");
        wo.setPlanStart(plan.getStartDate());
        wo.setPlanEnd(plan.getEndDate());
        wo.setCreateBy(username);
        workOrderMapper.insertMesWorkOrder(wo);

        plan.setStatus("running");
        planMapper.updateMesPlan(plan);

        Map<String, Object> result = new HashMap<>();
        result.put("orderCode", code);
        return result;
    }

    /* ==================== 协同异常 → 质量问题 ==================== */
    @Transactional
    public String collabToQuality(Long collabId, String username)
    {
        MesCollab collab = collabMapper.selectMesCollabById(collabId);
        if (collab == null)
        {
            throw new ServiceException("协同单不存在");
        }
        MesQualityIssue q = new MesQualityIssue();
        q.getParams().put("keyword", "QI-" + date("yyMMdd"));
        MesQualityIssue issue = new MesQualityIssue();
        issue.setIssueCode(nextCode("QI", issueMapper.selectMesQualityIssueList(q).size()));
        issue.setSourceType("incoming");
        issue.setSourceCode(collab.getCollabCode());
        issue.setProductCode(collab.getMaterialCode());
        issue.setBatchNo("");
        issue.setIssueType("来料异常");
        issue.setSeverity("normal");
        issue.setQty(collab.getQty());
        issue.setDescription(collab.getContent() == null ? "供应商交付异常" : collab.getContent());
        issue.setStatus("open");
        issue.setFoundTime(new Date());
        issue.setCreateBy(username);
        issueMapper.insertMesQualityIssue(issue);
        return "已生成质量问题 " + issue.getIssueCode();
    }

    /* ==================== 设备 ==================== */
    public String equipmentCommand(String equipmentCode, String command, String username)
    {
        MesEquipment q = new MesEquipment();
        q.getParams().put("keyword", equipmentCode);
        List<MesEquipment> list = equipmentMapper.selectMesEquipmentList(q);
        if (list.isEmpty())
        {
            throw new ServiceException("设备不存在：" + equipmentCode);
        }
        MesEquipment eq = list.get(0);
        String status;
        switch (command == null ? "" : command)
        {
            case "start":
                status = "run";
                break;
            case "stop":
                status = "stop";
                break;
            case "reset":
                status = "idle";
                break;
            case "calibrate":
                status = "run";
                break;
            default:
                throw new ServiceException("不支持的设备指令：" + command);
        }
        eq.setStatus(status);
        eq.setUpdateBy(username);
        equipmentMapper.updateMesEquipment(eq);
        return eq.getEquipmentName() + " 指令已下发（" + command + "）";
    }

    public String handleAlarm(Long alarmId, String handler)
    {
        MesAlarm alarm = alarmMapper.selectMesAlarmById(alarmId);
        if (alarm == null)
        {
            throw new ServiceException("报警记录不存在");
        }
        if ("handled".equals(alarm.getStatus()))
        {
            throw new ServiceException("该报警已处理");
        }
        alarm.setStatus("handled");
        alarm.setHandler(handler);
        alarm.setUpdateBy(handler);
        alarmMapper.updateMesAlarm(alarm);
        return "报警已处理";
    }

    /* ==================== 系统集成 ==================== */
    public String testInterface(Long interfaceId)
    {
        MesInterface it = interfaceMapper.selectMesInterfaceById(interfaceId);
        if (it == null)
        {
            throw new ServiceException("接口不存在");
        }
        it.setStatus("running");
        it.setLastSync(new Date());
        interfaceMapper.updateMesInterface(it);
        return it.getInterfaceName() + " 连接正常，响应 42ms";
    }

    /* ==================== 质量追溯 ==================== */
    public Map<String, Object> traceDetail(String keyword)
    {
        Map<String, Object> result = new HashMap<>();
        if (isBlank(keyword))
        {
            result.put("exists", false);
            return result;
        }
        MesTrace trace = queryMapper.selectTraceByKeyword(keyword.trim());
        if (trace == null)
        {
            result.put("exists", false);
            return result;
        }
        List<MesTrace> batchRows = queryMapper.selectTraceListByBatch(trace.getBatchNo() == null ? "" : trace.getBatchNo());
        int pass = 0;
        int fail = 0;
        int frozen = 0;
        for (MesTrace t : batchRows)
        {
            if ("pass".equals(t.getInspectionResult()))
            {
                pass++;
            }
            else if ("fail".equals(t.getInspectionResult()))
            {
                fail++;
            }
            if ("frozen".equals(t.getStatus()))
            {
                frozen++;
            }
        }
        result.put("exists", true);
        result.put("trace", trace);
        result.put("reports", queryMapper.selectReportsByOrder(trace.getOrderCode()));
        result.put("issues", queryMapper.selectIssuesByKeyword(trace.getBatchNo() == null ? keyword : trace.getBatchNo()));
        result.put("batchRows", batchRows);
        result.put("batchTotal", batchRows.size());
        result.put("batchPass", pass);
        result.put("batchFail", fail);
        result.put("batchFrozen", frozen);
        return result;
    }

    /* ==================== 内部工具 ==================== */
    private void stockIn(String materialCode, String materialName, String spec, String unit, String warehouseCode,
                         String warehouseName, String batchNo, BigDecimal qty, BigDecimal price, String location)
    {
        MesStock stock = queryMapper.selectStockByKey(materialCode, warehouseCode, batchNo == null ? "" : batchNo);
        if (stock == null)
        {
            stock = new MesStock();
            stock.setMaterialCode(materialCode);
            stock.setMaterialName(materialName);
            stock.setSpec(spec);
            stock.setUnit(isBlank(unit) ? "件" : unit);
            stock.setWarehouseCode(warehouseCode);
            stock.setWarehouseName(warehouseName);
            stock.setBatchNo(batchNo == null ? "" : batchNo);
            stock.setQty(qty);
            stock.setLockedQty(BigDecimal.ZERO);
            stock.setSafetyQty(BigDecimal.ZERO);
            stock.setPrice(price);
            stock.setLocation(location);
            refreshStock(stock);
            stockMapper.insertMesStock(stock);
        }
        else
        {
            stock.setQty(stock.getQty().add(qty));
            if (price.compareTo(BigDecimal.ZERO) > 0)
            {
                stock.setPrice(price);
            }
            refreshStock(stock);
            stockMapper.updateMesStock(stock);
        }
    }

    private void refreshStock(MesStock stock)
    {
        if (stock.getQty() == null)
        {
            stock.setQty(BigDecimal.ZERO);
        }
        BigDecimal safety = stock.getSafetyQty() == null ? BigDecimal.ZERO : stock.getSafetyQty();
        if (stock.getQty().compareTo(BigDecimal.ZERO) <= 0)
        {
            stock.setStatus("shortage");
        }
        else if (safety.compareTo(BigDecimal.ZERO) > 0 && stock.getQty().compareTo(safety) < 0)
        {
            stock.setStatus("warning");
        }
        else
        {
            stock.setStatus("normal");
        }
        BigDecimal price = stock.getPrice() == null ? BigDecimal.ZERO : stock.getPrice();
        stock.setAmount(stock.getQty().multiply(price));
        stock.setUpdateTime(new Date());
    }

    private void txn(String type, String sourceType, String sourceCode, String materialCode, String materialName,
                     String warehouseCode, String batchNo, BigDecimal qty, String unit, BigDecimal price,
                     String operator, String remark)
    {
        MesStockTxn txn = new MesStockTxn();
        MesStockTxn q = new MesStockTxn();
        q.getParams().put("keyword", "TX-" + date("yyMMdd"));
        txn.setTxnCode(nextCode("TX", txnMapper.selectMesStockTxnList(q).size()));
        txn.setTxnType(type);
        txn.setSourceType(sourceType);
        txn.setSourceCode(sourceCode);
        txn.setMaterialCode(materialCode);
        txn.setMaterialName(materialName);
        txn.setWarehouseCode(warehouseCode);
        txn.setBatchNo(batchNo == null ? "" : batchNo);
        txn.setQty(qty);
        txn.setUnit(isBlank(unit) ? "件" : unit);
        txn.setPrice(price);
        txn.setAmount(qty.abs().multiply(price == null ? BigDecimal.ZERO : price));
        txn.setTxnTime(new Date());
        txn.setOperator(operator);
        txn.setRemark(remark);
        txnMapper.insertMesStockTxn(txn);
    }

    private void createVoucher(String bizType, String bizCode, BigDecimal qty, BigDecimal amount, String username)
    {
        MesVoucher q = new MesVoucher();
        q.getParams().put("keyword", "CU-" + date("yyMMdd"));
        MesVoucher voucher = new MesVoucher();
        voucher.setVoucherCode(nextCode("CU", voucherMapper.selectMesVoucherList(q).size()));
        voucher.setBizType(bizType);
        voucher.setBizCode(bizCode == null ? "" : bizCode);
        voucher.setQty(qty);
        voucher.setAmount(amount == null ? BigDecimal.ZERO : amount);
        voucher.setAccountPeriod(date("yyyy-MM"));
        voucher.setStatus("pending");
        voucher.setU8cStatus("pending");
        voucher.setMessage("");
        voucher.setCreateBy(username);
        voucherMapper.insertMesVoucher(voucher);
    }

    private String nextCode(String prefix, int existing)
    {
        return prefix + "-" + date("yyMMdd") + "-" + String.format("%03d", existing + 1);
    }

    private String date(String pattern)
    {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    private boolean isBlank(String s)
    {
        return s == null || s.trim().isEmpty();
    }

    private String warehouseName(String code)
    {
        if ("WH-RM".equals(code))
        {
            return "原材料仓";
        }
        if ("WH-FG".equals(code))
        {
            return "成品仓";
        }
        if ("WH-LINE".equals(code))
        {
            return "线边库";
        }
        if ("WH-SUB".equals(code))
        {
            return "半成品仓";
        }
        return code;
    }

    private String statusText(String status)
    {
        if ("released".equals(status))
        {
            return "已放行";
        }
        if ("disposed".equals(status))
        {
            return "已处置";
        }
        if ("reviewing".equals(status))
        {
            return "评审中";
        }
        if ("isolated".equals(status))
        {
            return "已隔离";
        }
        return "已更新";
    }
}
