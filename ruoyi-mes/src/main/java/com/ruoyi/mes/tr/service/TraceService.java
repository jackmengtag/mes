package com.ruoyi.mes.tr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mes.ex.domain.ExProcessParam;
import com.ruoyi.mes.ex.domain.ExProductSerial;
import com.ruoyi.mes.ex.domain.ExProductionReport;
import com.ruoyi.mes.ex.mapper.ExProcessParamMapper;
import com.ruoyi.mes.ex.mapper.ExProductSerialMapper;
import com.ruoyi.mes.ex.mapper.ExProductionReportMapper;
import com.ruoyi.mes.mt.domain.MtMaterialConsumption;
import com.ruoyi.mes.mt.mapper.MtMaterialConsumptionMapper;
import com.ruoyi.mes.qc.domain.QcInspection;
import com.ruoyi.mes.qc.domain.QcRework;
import com.ruoyi.mes.qc.mapper.QcInspectionMapper;
import com.ruoyi.mes.qc.mapper.QcReworkMapper;
import com.ruoyi.mes.tr.domain.TrTraceIndex;
import com.ruoyi.mes.tr.mapper.TrTraceIndexMapper;
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.mapper.WoProductionOrderMapper;

/**
 * 追溯业务服务：正向追溯、反向追溯、批次召回（FR-TR-001~005）
 *
 * @author ruoyi
 */
@Service
public class TraceService
{
    @Autowired
    private TrTraceIndexMapper traceIndexMapper;

    @Autowired
    private ExProductSerialMapper serialMapper;

    @Autowired
    private ExProductionReportMapper reportMapper;

    @Autowired
    private MtMaterialConsumptionMapper consumptionMapper;

    @Autowired
    private QcInspectionMapper inspectionMapper;

    @Autowired
    private QcReworkMapper reworkMapper;

    @Autowired
    private ExProcessParamMapper processParamMapper;

    @Autowired
    private WoProductionOrderMapper orderMapper;

    /**
     * 反向追溯（FR-TR-002）：输入产品序列号，输出完整履历
     */
    public Map<String, Object> backward(String serialNo)
    {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("serialNo", serialNo);

        ExProductSerial query = new ExProductSerial();
        query.setSerialNo(serialNo);
        List<ExProductSerial> serials = serialMapper.selectExProductSerialList(query);
        ExProductSerial serial = pickFirst(serials, serialNo);
        result.put("serial", serial);
        if (serial == null)
        {
            result.put("dataCompleteness", "INCOMPLETE");
            result.put("message", "未查询到该序列号的档案，履历可能不完整");
            return result;
        }
        result.put("productionOrderId", serial.getProductionOrderId());
        if (serial.getProductionOrderId() != null)
        {
            WoProductionOrder order = orderMapper.selectWoProductionOrderById(serial.getProductionOrderId());
            result.put("order", order);
        }
        result.put("materialHistory", consumptionList(serialNo));
        result.put("reportHistory", reportList(serialNo));
        result.put("inspectionHistory", inspectionList(serialNo));
        result.put("reworkHistory", reworkList(serialNo));
        result.put("parameterHistory", paramList(serialNo));
        result.put("dataCompleteness", verifyCompleteness(serialNo));
        return result;
    }

    /**
     * 正向追溯（FR-TR-001）：输入物料批次或设备编号，输出受影响的产品序列号与工单
     */
    public Map<String, Object> forward(String materialBatchNo, String equipmentCode)
    {
        TrTraceIndex query = new TrTraceIndex();
        if (StringUtils.isNotEmpty(materialBatchNo))
        {
            query.setMaterialBatchNo(materialBatchNo);
        }
        if (StringUtils.isNotEmpty(equipmentCode))
        {
            query.setEquipmentCode(equipmentCode);
        }
        List<TrTraceIndex> indexes = traceIndexMapper.selectTrTraceIndexList(query);
        List<String> serials = new ArrayList<>();
        List<String> orders = new ArrayList<>();
        for (TrTraceIndex idx : indexes)
        {
            if (StringUtils.isNotEmpty(idx.getSerialNo()) && !serials.contains(idx.getSerialNo()))
            {
                serials.add(idx.getSerialNo());
            }
            if (StringUtils.isNotEmpty(idx.getOrderNo()) && !orders.contains(idx.getOrderNo()))
            {
                orders.add(idx.getOrderNo());
            }
        }
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("materialBatchNo", materialBatchNo);
        result.put("equipmentCode", equipmentCode);
        result.put("affectedSerials", serials);
        result.put("affectedOrders", orders);
        result.put("affectedCount", serials.size());
        return result;
    }

    /**
     * 批次召回分析（FR-TR-003）
     */
    public Map<String, Object> recall(String materialBatchNo)
    {
        Map<String, Object> result = forward(materialBatchNo, null);
        List<Map<String, Object>> details = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<String> serials = (List<String>) result.get("affectedSerials");
        for (String sn : serials)
        {
            ExProductSerial query = new ExProductSerial();
            query.setSerialNo(sn);
            ExProductSerial serial = pickFirst(serialMapper.selectExProductSerialList(query), sn);
            if (serial != null)
            {
                Map<String, Object> d = new HashMap<>();
                d.put("serialNo", sn);
                d.put("warehouseStatus", serial.getWarehouseStatus());
                d.put("currentStatus", serial.getCurrentStatus());
                d.put("productionOrderId", serial.getProductionOrderId());
                details.add(d);
            }
        }
        result.put("recallDetails", details);
        return result;
    }

    /**
     * 追溯报告导出数据（FR-TR-004）
     */
    public List<Map<String, Object>> exportReport(String serialNo)
    {
        List<Map<String, Object>> rows = new ArrayList<>();
        for (MtMaterialConsumption c : consumptionList(serialNo))
        {
            Map<String, Object> row = new HashMap<>();
            row.put("serialNo", serialNo);
            row.put("refType", "物料消耗");
            row.put("refKey", c.getMaterialCode() + "/" + c.getMaterialBatchNo());
            row.put("detail", c.getConsumedQty() + "");
            row.put("operator", c.getOperatorNo());
            row.put("bizTime", c.getConsumedAt());
            rows.add(row);
        }
        for (ExProductionReport r : reportList(serialNo))
        {
            Map<String, Object> row = new HashMap<>();
            row.put("serialNo", serialNo);
            row.put("refType", "报工");
            row.put("refKey", r.getOperationCode());
            row.put("detail", "合格" + r.getQualifiedQty());
            row.put("operator", r.getOperatorNo());
            row.put("bizTime", r.getReportedAt());
            rows.add(row);
        }
        for (QcInspection i : inspectionList(serialNo))
        {
            Map<String, Object> row = new HashMap<>();
            row.put("serialNo", serialNo);
            row.put("refType", "检验");
            row.put("refKey", i.getInspectionType());
            row.put("detail", i.getResult());
            row.put("operator", i.getInspectorNo());
            row.put("bizTime", i.getInspectedAt());
            rows.add(row);
        }
        return rows;
    }

    private List<MtMaterialConsumption> consumptionList(String serialNo)
    {
        MtMaterialConsumption q = new MtMaterialConsumption();
        q.setSerialNo(serialNo);
        return consumptionMapper.selectMtMaterialConsumptionList(q);
    }

    private List<ExProductionReport> reportList(String serialNo)
    {
        ExProductionReport q = new ExProductionReport();
        q.setSerialNo(serialNo);
        return reportMapper.selectExProductionReportList(q);
    }

    private List<QcInspection> inspectionList(String serialNo)
    {
        QcInspection q = new QcInspection();
        q.setSerialNo(serialNo);
        return inspectionMapper.selectQcInspectionList(q);
    }

    private List<QcRework> reworkList(String serialNo)
    {
        QcRework q = new QcRework();
        q.setSerialNo(serialNo);
        return reworkMapper.selectQcReworkList(q);
    }

    private List<ExProcessParam> paramList(String serialNo)
    {
        ExProcessParam q = new ExProcessParam();
        q.setSerialNo(serialNo);
        return processParamMapper.selectExProcessParamList(q);
    }

    private String verifyCompleteness(String serialNo)
    {
        if (reportList(serialNo).isEmpty())
        {
            return "INCOMPLETE";
        }
        return "COMPLETE";
    }

    private static ExProductSerial pickFirst(List<ExProductSerial> list, String serialNo)
    {
        for (ExProductSerial s : list)
        {
            if (serialNo != null && serialNo.equals(s.getSerialNo()))
            {
                return s;
            }
        }
        return list.isEmpty() ? null : list.get(0);
    }
}
