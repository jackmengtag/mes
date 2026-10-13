package com.ruoyi.mes.integration.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.mes.common.constant.MesConstants;
import com.ruoyi.mes.common.utils.MesSeqUtils;
import com.ruoyi.mes.integration.domain.IntReconcileResult;
import com.ruoyi.mes.integration.domain.IntReconcileTask;
import com.ruoyi.mes.integration.mapper.IntReconcileResultMapper;
import com.ruoyi.mes.integration.mapper.IntReconcileTaskMapper;
import com.ruoyi.mes.om.domain.OmDemand;
import com.ruoyi.mes.om.mapper.OmDemandMapper;
import com.ruoyi.mes.wo.domain.WoProductionOrder;
import com.ruoyi.mes.wo.mapper.WoProductionOrderMapper;

/**
 * 对账业务服务（架构文档 8.5 对账机制 / IR-ERP-008 / IR-WMS-009 / IR-DEV-008）
 *
 * <p>说明：外部系统侧数量在此为对账框架的占位实现（外部系统未接入时按一致处理），
 * 接入真实 ERP/WMS 后可将 extValue 替换为外部接口返回值。</p>
 *
 * @author ruoyi
 */
@Service
public class ReconciliationService
{
    @Autowired
    private IntReconcileTaskMapper taskMapper;

    @Autowired
    private IntReconcileResultMapper resultMapper;

    @Autowired
    private OmDemandMapper demandMapper;

    @Autowired
    private WoProductionOrderMapper orderMapper;

    /**
     * 执行对账
     *
     * @param targetSystem ERP / WMS / DEV
     * @param bizDate 对账日期
     * @return 对账任务
     */
    @Transactional
    public IntReconcileTask run(String targetSystem, Date bizDate)
    {
        IntReconcileTask task = new IntReconcileTask();
        task.setTaskNo(MesSeqUtils.generate(MesConstants.PREFIX_RECONCILE));
        task.setTargetSystem(targetSystem);
        task.setBizDate(bizDate == null ? new Date() : bizDate);
        task.setStatus("RUNNING");
        task.setStartTime(new Date());
        task.setCreateBy("system");
        taskMapper.insertIntReconcileTask(task);

        int diff = 0;
        if ("ERP".equals(targetSystem))
        {
            int demandCount = demandMapper.selectOmDemandList(new OmDemand()).size();
            diff += record(task, "ERP", "DEMAND", "TOTAL", "CONSISTENT", String.valueOf(demandCount), String.valueOf(demandCount));
            int orderCount = orderMapper.selectWoProductionOrderList(new WoProductionOrder()).size();
            diff += record(task, "ERP", "WORK_ORDER", "TOTAL", "CONSISTENT", String.valueOf(orderCount), String.valueOf(orderCount));
        }
        else if ("WMS".equals(targetSystem))
        {
            diff += record(task, "WMS", "CONSUMPTION", "TOTAL", "CONSISTENT", "0", "0");
        }
        else if ("DEV".equals(targetSystem))
        {
            diff += record(task, "DEV", "TEST_RESULT", "TOTAL", "CONSISTENT", "0", "0");
        }
        task.setDiffCount(diff);
        task.setStatus("FINISHED");
        task.setEndTime(new Date());
        task.setUpdateBy("system");
        taskMapper.updateIntReconcileTask(task);
        return task;
    }

    private int record(IntReconcileTask task, String target, String bizType, String bizKey, String diffType,
            String mesValue, String extValue)
    {
        IntReconcileResult result = new IntReconcileResult();
        result.setTaskId(task.getId());
        result.setTargetSystem(target);
        result.setBizType(bizType);
        result.setBizKey(bizKey);
        result.setDiffType(diffType);
        result.setMesValue(mesValue);
        result.setExtValue(extValue);
        result.setHandleStatus("CONSISTENT".equals(diffType) ? "CLOSED" : "OPEN");
        result.setCreateBy("system");
        resultMapper.insertIntReconcileResult(result);
        return "CONSISTENT".equals(diffType) ? 0 : 1;
    }
}
