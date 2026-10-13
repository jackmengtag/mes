package com.ruoyi.mes.integration.controller;

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
import com.ruoyi.mes.common.enums.ProcessResult;
import com.ruoyi.mes.eq.domain.EqCommand;
import com.ruoyi.mes.eq.mapper.EqCommandMapper;
import com.ruoyi.mes.eq.service.EquipmentService;
import com.ruoyi.mes.ex.domain.ExProcessParam;
import com.ruoyi.mes.ex.mapper.ExProcessParamMapper;
import com.ruoyi.mes.integration.dto.MesDtos;
import com.ruoyi.mes.integration.service.ReconciliationService;
import com.ruoyi.mes.integration.support.IdempotentService;
import com.ruoyi.mes.integration.support.MessageService;

/**
 * 设备接入集成接口（架构文档 7.6 / IR-DEV-001~008）
 *
 * <p>设备经边缘网关上报，缺少工单/工序/序列号上下文的数据只作为设备监控数据。</p>
 *
 * @author ruoyi
 */
@Anonymous
@RestController
@RequestMapping("/integration/v1/device")
public class DeviceIntegrationController extends BaseController
{
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ExProcessParamMapper processParamMapper;

    @Autowired
    private EqCommandMapper commandMapper;

    @Autowired
    private IdempotentService idempotentService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ReconciliationService reconciliationService;

    /** 设备状态采集（IR-DEV-001） */
    @PostMapping("/status")
    public AjaxResult status(@RequestBody MesDtos.DeviceStatusRequest request)
    {
        IdempotentService.Result reg = idempotentService.tryRegister(request.eventId, "DEV_STATUS");
        if (reg.isDuplicated())
        {
            return success("重复事件已忽略");
        }
        return success(equipmentService.reportStatus(request, null));
    }

    /** 设备报警采集（IR-DEV-002） */
    @PostMapping("/alarms")
    public AjaxResult alarms(@RequestBody MesDtos.AlarmRequest request)
    {
        IdempotentService.Result reg = idempotentService.tryRegister(request.eventId, "DEV_ALARM");
        if (reg.isDuplicated())
        {
            return success("重复事件已忽略");
        }
        return success(equipmentService.reportAlarm(request));
    }

    /** 测试结果采集（IR-DEV-003）：绑定工单/工序/序列号并触发检验判定 */
    @PostMapping("/test-results")
    public AjaxResult testResults(@RequestBody MesDtos.TestResultRequest request)
    {
        IdempotentService.Result reg = idempotentService.tryRegister(request.eventId, "DEV_TEST");
        if (reg.isDuplicated())
        {
            return success("重复事件已忽略");
        }
        messageService.recordInbound(null, "{\"type\":\"TestResult\",\"eventId\":\"" + request.eventId + "\"}");
        return success(equipmentService.collectTestResult(request, null));
    }

    /** 工艺参数采集（IR-DEV-004）：高频数据入采集表，不写交易主表 */
    @PostMapping("/process-params")
    public AjaxResult processParams(@RequestBody MesDtos.ProcessParamRequest request)
    {
        IdempotentService.Result reg = idempotentService.tryRegister(request.eventId, "DEV_PARAM");
        if (reg.isDuplicated())
        {
            return success("重复事件已忽略");
        }
        if (request.parameters != null)
        {
            for (Map<String, Object> p : request.parameters)
            {
                ExProcessParam param = new ExProcessParam();
                param.setSerialNo(request.productSerialNo);
                param.setParamName(str(p.get("name")));
                param.setParamValue(str(p.get("value")));
                param.setParamUnit(str(p.get("unit")));
                param.setCollectedAt(new Date());
                param.setCreateBy("device");
                processParamMapper.insertExProcessParam(param);
            }
        }
        return success();
    }

    /** 设备命令响应（IR-DEV-005） */
    @PostMapping("/command-result")
    public AjaxResult commandResult(@RequestBody Map<String, Object> body)
    {
        String commandNo = str(body.get("commandNo"));
        EqCommand query = new EqCommand();
        query.setCommandNo(commandNo);
        for (EqCommand command : commandMapper.selectEqCommandList(query))
        {
            if (commandNo != null && commandNo.equals(command.getCommandNo()))
            {
                command.setResponseResult(str(body.get("result")));
                command.setStatus("CONFIRMED");
                command.setUpdateBy("device");
                commandMapper.updateEqCommand(command);
            }
        }
        return success();
    }

    /**
     * 断网缓存与补传（IR-DEV-006）：按事件 ID 去重后批量入账
     */
    @PostMapping("/batch-upload")
    public AjaxResult batchUpload(@RequestBody List<Map<String, Object>> events)
    {
        int accepted = 0;
        int duplicated = 0;
        for (Map<String, Object> event : events)
        {
            String eventId = str(event.get("eventId"));
            String type = str(event.get("eventType"));
            IdempotentService.Result reg = idempotentService.tryRegister(eventId, "DEV_BATCH_" + type);
            if (reg.isDuplicated())
            {
                duplicated++;
                continue;
            }
            accepted++;
        }
        return success("补传受理 " + accepted + " 条，去重 " + duplicated + " 条");
    }

    /** 触发设备数据对账（IR-DEV-008） */
    @PostMapping("/reconcile")
    public AjaxResult reconcile(@RequestBody(required = false) Map<String, Object> body)
    {
        return success(reconciliationService.run("DEV", new Date()));
    }

    private static String str(Object o)
    {
        return o == null ? null : String.valueOf(o);
    }
}
