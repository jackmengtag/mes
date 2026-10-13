package com.ruoyi.mes.qc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.ruoyi.mes.qc.domain.QcInspection;
import com.ruoyi.mes.qc.domain.QcInspectionValue;
import com.ruoyi.mes.qc.service.QualityService;

/**
 * 质量业务流控制器（FR-QC-001~010）：检验、判定、返工复检、质量放行
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/qc/workflow")
public class QualityWorkflowController extends BaseController
{
    @Autowired
    private QualityService qualityService;

    /** 创建检验单（按检验方案生成自检项） */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspection:add')")
    @Log(title = "创建检验单", businessType = BusinessType.INSERT)
    @PostMapping("/inspection")
    public AjaxResult createInspection(@RequestBody QcInspection inspection)
    {
        if (inspection.getInspectorNo() == null)
        {
            inspection.setInspectorNo(getUsername());
        }
        return success(qualityService.createInspection(inspection));
    }

    /** 提交检验结果并自动判定（不合格自动隔离） */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspection:edit')")
    @Log(title = "提交检验结果", businessType = BusinessType.UPDATE)
    @PostMapping("/inspection/submit/{id}")
    public AjaxResult submit(@PathVariable("id") Long id, @RequestBody Map<String, Object> body)
    {
        List<QcInspectionValue> values = new ArrayList<>();
        Object vs = body.get("values");
        if (vs instanceof List)
        {
            for (Object o : (List<?>) vs)
            {
                @SuppressWarnings("unchecked")
                Map<String, Object> m = (Map<String, Object>) o;
                QcInspectionValue v = new QcInspectionValue();
                v.setId(lng(m.get("id")));
                v.setItemCode(str(m.get("itemCode")));
                v.setItemName(str(m.get("itemName")));
                v.setItemValue(str(m.get("itemValue")));
                v.setLowerLimit(dec(m.get("lowerLimit")));
                v.setUpperLimit(dec(m.get("upperLimit")));
                values.add(v);
            }
        }
        String inspector = body.get("inspectorNo") == null ? getUsername() : String.valueOf(body.get("inspectorNo"));
        return success(qualityService.submitResult(id, values, inspector, null));
    }

    /** 不合格品判定 */
    @PreAuthorize("@ss.hasPermi('mes:qc:nonconformity:edit')")
    @Log(title = "不合格品判定", businessType = BusinessType.UPDATE)
    @PostMapping("/nonconformity/judge/{id}")
    public AjaxResult judge(@PathVariable("id") Long id, @RequestBody Map<String, Object> body)
    {
        String disposalType = str(body.get("disposalType"));
        String reason = str(body.get("reason"));
        boolean authorized = Boolean.parseBoolean(String.valueOf(body.get("authorized")));
        return success(qualityService.judge(id, disposalType, reason, getUsername(), authorized));
    }

    /** 返工复检 */
    @PreAuthorize("@ss.hasPermi('mes:qc:rework:edit')")
    @Log(title = "返工复检", businessType = BusinessType.UPDATE)
    @PostMapping("/nonconformity/reinspect/{id}")
    public AjaxResult reinspect(@PathVariable("id") Long id, @RequestBody Map<String, Object> body)
    {
        return success(qualityService.reinspect(id, str(body.get("result")), getUsername()));
    }

    /** 质量放行 */
    @PreAuthorize("@ss.hasPermi('mes:qc:release:add')")
    @Log(title = "质量放行", businessType = BusinessType.INSERT)
    @PostMapping("/release/{orderId}")
    public AjaxResult release(@PathVariable("orderId") Long orderId, @RequestBody(required = false) Map<String, Object> body)
    {
        boolean special = body != null && Boolean.parseBoolean(String.valueOf(body.get("special")));
        return success(qualityService.releaseOrder(orderId, getUsername(), special, null));
    }

    /** 查询质量放行阻塞项 */
    @PreAuthorize("@ss.hasPermi('mes:qc:release:list')")
    @GetMapping("/blockers/{orderId}")
    public AjaxResult blockers(@PathVariable("orderId") Long orderId)
    {
        return success(qualityService.collectBlockers(orderId));
    }

    private static Long lng(Object o)
    {
        return o == null ? null : Long.valueOf(String.valueOf(o));
    }

    private static String str(Object o)
    {
        return o == null ? null : String.valueOf(o);
    }

    private static BigDecimal dec(Object o)
    {
        if (o == null || String.valueOf(o).isEmpty())
        {
            return null;
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
