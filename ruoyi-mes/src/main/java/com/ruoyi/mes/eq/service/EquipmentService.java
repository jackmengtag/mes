package com.ruoyi.mes.eq.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.enums.InspectionType;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.eq.domain.EqAlarm;
import com.ruoyi.mes.eq.domain.EqBind;
import com.ruoyi.mes.eq.domain.EqCommand;
import com.ruoyi.mes.eq.domain.EqMaintenance;
import com.ruoyi.mes.eq.domain.EqOee;
import com.ruoyi.mes.eq.domain.EqStatus;
import com.ruoyi.mes.eq.mapper.EqAlarmMapper;
import com.ruoyi.mes.eq.mapper.EqBindMapper;
import com.ruoyi.mes.eq.mapper.EqCommandMapper;
import com.ruoyi.mes.eq.mapper.EqMaintenanceMapper;
import com.ruoyi.mes.eq.mapper.EqOeeMapper;
import com.ruoyi.mes.eq.mapper.EqStatusMapper;
import com.ruoyi.mes.ex.domain.ExProcessParam;
import com.ruoyi.mes.ex.domain.ExProductionReport;
import com.ruoyi.mes.ex.mapper.ExProcessParamMapper;
import com.ruoyi.mes.ex.mapper.ExProductionReportMapper;
import com.ruoyi.mes.integration.dto.MesDtos;
import com.ruoyi.mes.integration.support.DomainEventService;
import com.ruoyi.mes.md.domain.MdEquipment;
import com.ruoyi.mes.md.mapper.MdEquipmentMapper;
import com.ruoyi.mes.qc.domain.QcInspection;
import com.ruoyi.mes.qc.domain.QcInspectionValue;
import com.ruoyi.mes.qc.service.QualityService;

/**
 * 设备管理业务服务：状态、报警、绑定、命令、点检维护、OEE（FR-EQ / IR-DEV）
 *
 * @author ruoyi
 */
@Service
public class EquipmentService
{
    @Autowired
    private MdEquipmentMapper equipmentMapper;

    @Autowired
    private EqStatusMapper statusMapper;

    @Autowired
    private EqAlarmMapper alarmMapper;

    @Autowired
    private EqBindMapper bindMapper;

    @Autowired
    private EqCommandMapper commandMapper;

    @Autowired
    private EqMaintenanceMapper maintenanceMapper;

    @Autowired
    private EqOeeMapper oeeMapper;

    @Autowired
    private ExProcessParamMapper processParamMapper;

    @Autowired
    private ExProductionReportMapper reportMapper;

    @Autowired
    private QualityService qualityService;

    @Autowired
    private DomainEventService domainEventService;

    /**
     * 采集设备状态（FR-EQ-002 / IR-DEV-001）
     */
    @Transactional
    public EqStatus reportStatus(MesDtos.DeviceStatusRequest req, String traceId)
    {
        EqStatus status = new EqStatus();
        status.setEquipmentCode(req.deviceCode);
        status.setEquipmentId(findEquipmentId(req.deviceCode));
        status.setStatus(req.status);
        status.setChangedAt(new Date());
        status.setCreateBy("device");
        statusMapper.insertEqStatus(status);
        if ("OFFLINE".equals(req.status) || "FAULT".equals(req.status))
        {
            domainEventService.publish("EquipmentAbnormal", "MdEquipment", status.getEquipmentId(),
                    "{\"equipmentCode\":\"" + req.deviceCode + "\",\"status\":\"" + req.status + "\"}", traceId);
        }
        return status;
    }

    /**
     * 采集设备报警（FR-EQ-003 / IR-DEV-002）
     */
    @Transactional
    public EqAlarm reportAlarm(MesDtos.AlarmRequest req)
    {
        EqAlarm alarm = new EqAlarm();
        alarm.setEquipmentId(findEquipmentId(req.deviceCode));
        alarm.setEquipmentCode(req.deviceCode);
        alarm.setAlarmCode(req.alarmCode);
        alarm.setAlarmLevel(req.alarmLevel);
        alarm.setAlarmDesc(req.alarmDesc);
        alarm.setOperationCode(req.operationCode);
        alarm.setStartTime(new Date());
        alarm.setCreateBy("device");
        alarmMapper.insertEqAlarm(alarm);
        return alarm;
    }

    /**
     * 设备与工单/工序/序列号绑定（FR-EQ-004）
     */
    @Transactional
    public EqBind bind(EqBind bind)
    {
        bind.setEquipmentId(findEquipmentId(bind.getEquipmentCode()));
        bind.setBindTime(new Date());
        bind.setCreateBy("system");
        bindMapper.insertEqBind(bind);
        return bind;
    }

    /**
     * 设备命令下发（FR-EQ-005 / IR-DEV-005）
     */
    @Transactional
    public EqCommand sendCommand(EqCommand command)
    {
        if (command.getCommandNo() == null || command.getCommandNo().isEmpty())
        {
            command.setCommandNo(MesSeqUtils.generate(MesConstants.PREFIX_COMMAND));
        }
        command.setEquipmentId(findEquipmentId(command.getEquipmentCode()));
        command.setSendTime(new Date());
        command.setRetryCount(0);
        command.setStatus("SENT");
        command.setCreateBy("system");
        commandMapper.insertEqCommand(command);
        return command;
    }

    /**
     * 命令重试
     */
    @Transactional
    public EqCommand retryCommand(Long commandId)
    {
        EqCommand command = commandMapper.selectEqCommandById(commandId);
        if (command != null)
        {
            command.setRetryCount((command.getRetryCount() == null ? 0 : command.getRetryCount()) + 1);
            command.setSendTime(new Date());
            command.setUpdateBy("system");
            commandMapper.updateEqCommand(command);
        }
        return command;
    }

    /**
     * 采集测试结果（IR-DEV-003 / 架构 7.6.4）：绑定工单、工序、序列号并触发检验判定
     */
    @Transactional
    public QcInspection collectTestResult(MesDtos.TestResultRequest req, String traceId)
    {
        QcInspection inspection = new QcInspection();
        inspection.setInspectionType(InspectionType.FQC.name());
        inspection.setOperationCode(req.operationCode);
        inspection.setSerialNo(req.productSerialNo);
        inspection.setInspectorNo("DEVICE");
        java.util.List<QcInspectionValue> values = new java.util.ArrayList<>();
        if (req.parameters != null)
        {
            for (java.util.Map<String, Object> p : req.parameters)
            {
                // 工艺参数快照
                ExProcessParam param = new ExProcessParam();
                param.setSerialNo(req.productSerialNo);
                param.setParamName(str(p.get("name")));
                param.setParamValue(str(p.get("value")));
                param.setParamUnit(str(p.get("unit")));
                param.setLowerLimit(decimal(p.get("lowerLimit")));
                param.setUpperLimit(decimal(p.get("upperLimit")));
                param.setResult(str(p.get("result")));
                param.setCollectedAt(new Date());
                param.setCreateBy("device");
                processParamMapper.insertExProcessParam(param);

                QcInspectionValue v = new QcInspectionValue();
                v.setItemCode(str(p.get("name")));
                v.setItemName(str(p.get("name")));
                v.setItemValue(str(p.get("value")));
                v.setLowerLimit(decimal(p.get("lowerLimit")));
                v.setUpperLimit(decimal(p.get("upperLimit")));
                values.add(v);
            }
        }
        QcInspection created = qualityService.createInspection(inspection);
        qualityService.submitResult(created.getId(), values, "DEVICE", traceId);
        return created;
    }

    /**
     * OEE 计算（FR-EQ-008）：开动率 × 性能效率 × 良品率
     */
    @Transactional
    public void calcOee(Date statDate, String equipmentCode)
    {
        MdEquipment equipment = null;
        if (equipmentCode != null)
        {
            MdEquipment q = new MdEquipment();
            q.setEquipmentCode(equipmentCode);
            List<MdEquipment> list = equipmentMapper.selectMdEquipmentList(q);
            for (MdEquipment e : list)
            {
                if (equipmentCode.equals(e.getEquipmentCode()))
                {
                    equipment = e;
                    break;
                }
            }
        }
        if (equipment == null)
        {
            return;
        }
        BigDecimal runMinutes = new BigDecimal("480");
        BigDecimal stopMinutes = BigDecimal.ZERO;
        EqMaintenance mq = new EqMaintenance();
        mq.setEquipmentId(equipment.getId());
        for (EqMaintenance m : maintenanceMapper.selectEqMaintenanceList(mq))
        {
            if (m.getStopMinutes() != null)
            {
                stopMinutes = stopMinutes.add(m.getStopMinutes());
            }
        }
        BigDecimal availability = runMinutes.subtract(stopMinutes).divide(runMinutes, 4, RoundingMode.HALF_UP);
        if (availability.compareTo(BigDecimal.ZERO) < 0)
        {
            availability = BigDecimal.ZERO;
        }
        // 良品率
        ExProductionReport rq = new ExProductionReport();
        rq.setEquipmentId(equipment.getId());
        BigDecimal qualified = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal machineMinutes = BigDecimal.ZERO;
        for (ExProductionReport r : reportMapper.selectExProductionReportList(rq))
        {
            qualified = qualified.add(nz(r.getQualifiedQty()));
            total = total.add(nz(r.getQualifiedQty())).add(nz(r.getUnqualifiedQty())).add(nz(r.getScrapQty()));
            machineMinutes = machineMinutes.add(nz(r.getMachineMinutes()));
        }
        BigDecimal quality = total.compareTo(BigDecimal.ZERO) > 0
                ? qualified.divide(total, 4, RoundingMode.HALF_UP) : BigDecimal.ONE;
        BigDecimal performance = machineMinutes.compareTo(BigDecimal.ZERO) > 0
                ? machineMinutes.divide(runMinutes, 4, RoundingMode.HALF_UP).min(BigDecimal.ONE) : BigDecimal.ONE;
        BigDecimal oee = availability.multiply(performance).multiply(quality).setScale(4, RoundingMode.HALF_UP);

        EqOee record = new EqOee();
        record.setEquipmentId(equipment.getId());
        record.setEquipmentCode(equipment.getEquipmentCode());
        record.setStatDate(statDate == null ? new Date() : statDate);
        record.setAvailability(availability);
        record.setPerformance(performance);
        record.setQuality(quality);
        record.setOee(oee);
        record.setRunMinutes(runMinutes);
        record.setStopMinutes(stopMinutes);
        record.setCreateBy("system");
        oeeMapper.insertEqOee(record);
    }

    private Long findEquipmentId(String equipmentCode)
    {
        if (equipmentCode == null)
        {
            return null;
        }
        MdEquipment q = new MdEquipment();
        q.setEquipmentCode(equipmentCode);
        for (MdEquipment e : equipmentMapper.selectMdEquipmentList(q))
        {
            if (equipmentCode.equals(e.getEquipmentCode()))
            {
                return e.getId();
            }
        }
        return null;
    }

    private static BigDecimal nz(BigDecimal v)
    {
        return v == null ? BigDecimal.ZERO : v;
    }

    private static String str(Object o)
    {
        return o == null ? null : String.valueOf(o);
    }

    private static BigDecimal decimal(Object o)
    {
        if (o == null)
        {
            return null;
        }
        if (o instanceof BigDecimal)
        {
            return (BigDecimal) o;
        }
        try
        {
            return new BigDecimal(String.valueOf(o));
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
