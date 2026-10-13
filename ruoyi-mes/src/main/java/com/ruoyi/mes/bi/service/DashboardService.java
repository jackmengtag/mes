package com.ruoyi.mes.bi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.mes.common.mapper.MesQueryMapper;

/**
 * 看板与分析业务服务（FR-BI-001~005）：生产、质量、设备、物料指标与报表
 *
 * @author ruoyi
 */
@Service
public class DashboardService
{
    @Autowired
    private MesQueryMapper queryMapper;

    /** 生产看板 */
    public Map<String, Object> productionKpi()
    {
        return queryMapper.selectProductionKpi();
    }

    /** 质量看板 */
    public Map<String, Object> qualityKpi()
    {
        return queryMapper.selectQualityKpi();
    }

    /** 设备看板 */
    public Map<String, Object> equipmentKpi()
    {
        return queryMapper.selectEquipmentKpi();
    }

    /** 物料看板 */
    public Map<String, Object> materialKpi()
    {
        return queryMapper.selectMaterialKpi();
    }

    /** 报表查询（FR-BI-004） */
    public List<Map<String, Object>> report(String reportType, Map<String, Object> params)
    {
        Map<String, Object> p = params == null ? new HashMap<>() : params;
        if ("reportDetail".equals(reportType))
        {
            return queryMapper.selectReportDetail(p);
        }
        else if ("orderProgress".equals(reportType))
        {
            return queryMapper.selectOrderProgress(p);
        }
        else if ("consumption".equals(reportType))
        {
            return queryMapper.selectConsumptionReport(p);
        }
        else if ("inspection".equals(reportType))
        {
            return queryMapper.selectInspectionReport(p);
        }
        throw new ServiceException("未知报表类型：" + reportType);
    }
}
