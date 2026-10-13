package com.ruoyi.mes.integration.dto;

import java.util.List;
import java.util.Map;

/**
 * 集成接口传输对象集合（对应架构文档 7.4 / 7.5 / 7.6 的报文示例）
 *
 * @author ruoyi
 */
public class MesDtos
{
    private MesDtos()
    {
    }

    /** 生产需求（ERP -> MES） */
    public static class DemandRequest
    {
        public String sourceOrderNo;
        public String sourceOrderLineNo;
        public String productCode;
        public String productVersion;
        public java.math.BigDecimal requiredQuantity;
        public String dueDate;
        public String priority;
        public String plantCode;
    }

    /** 报工回传（MES -> ERP 报文结构，同时用于内部报工） */
    public static class ProductionReportRequest
    {
        public String reportNo;
        public String mesWorkOrderNo;
        public String erpWorkOrderNo;
        public String operationCode;
        public String workCenterCode;
        public String operatorCode;
        public String shiftCode;
        public java.math.BigDecimal qualifiedQuantity;
        public java.math.BigDecimal unqualifiedQuantity;
        public java.math.BigDecimal scrapQuantity;
        public java.math.BigDecimal laborMinutes;
        public java.math.BigDecimal machineMinutes;
        public String reportedAt;
    }

    /** 物料消耗明细项 */
    public static class ConsumeItem
    {
        public String materialCode;
        public String batchNo;
        public java.math.BigDecimal quantity;
        public String unit;
    }

    /** 物料消耗（MES -> WMS） */
    public static class MaterialConsumptionRequest
    {
        public String consumeNo;
        public String workOrderNo;
        public String operationCode;
        public String productSerialNo;
        public String consumedBy;
        public String consumedAt;
        public List<Map<String, Object>> items;
    }

    /** 测试结果（设备 -> MES，对应 7.6.4） */
    public static class TestResultRequest
    {
        public String eventId;
        public String eventType;
        public String deviceCode;
        public String workOrderNo;
        public String productSerialNo;
        public String operationCode;
        public String testProgramCode;
        public String result;
        public List<Map<String, Object>> parameters;
        public String completedAt;
    }

    /** 设备状态（设备 -> MES） */
    public static class DeviceStatusRequest
    {
        public String eventId;
        public String deviceCode;
        public String status;
        public String changedAt;
    }

    /** 设备报警（设备 -> MES） */
    public static class AlarmRequest
    {
        public String eventId;
        public String deviceCode;
        public String alarmCode;
        public String alarmLevel;
        public String alarmDesc;
        public String workOrderNo;
        public String operationCode;
        public String startTime;
        public String endTime;
    }

    /** 工艺参数（设备 -> MES） */
    public static class ProcessParamRequest
    {
        public String eventId;
        public String deviceCode;
        public String workOrderNo;
        public String productSerialNo;
        public String operationCode;
        public List<Map<String, Object>> parameters;
        public String collectedAt;
    }

    /** 拣料/配送结果（WMS -> MES） */
    public static class PickingResultRequest
    {
        public String workOrderNo;
        public String requirementNo;
        public List<Map<String, Object>> items;
        public String deliveryFlag;
    }

    /** 成品入库结果（WMS -> MES） */
    public static class InboundResultRequest
    {
        public String workOrderNo;
        public String inboundNo;
        public String receiptNo;
        public java.math.BigDecimal quantity;
    }

    /** 库存冻结/解冻（MES -> WMS） */
    public static class FreezeRequest
    {
        public String scopeType;
        public String scopeValue;
        public String freezeFlag;
        public String reason;
    }
}
