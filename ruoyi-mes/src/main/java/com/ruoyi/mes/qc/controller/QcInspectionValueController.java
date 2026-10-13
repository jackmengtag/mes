package com.ruoyi.mes.qc.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.qc.domain.QcInspectionValue;
import com.ruoyi.mes.qc.service.IQcInspectionValueService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 检验项目值Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/qc/inspectionValue")
public class QcInspectionValueController extends BaseController
{
    @Autowired
    private IQcInspectionValueService qcInspectionValueService;

    /**
     * 查询检验项目值列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspectionValue:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcInspectionValue qcInspectionValue)
    {
        startPage();
        List<QcInspectionValue> list = qcInspectionValueService.selectQcInspectionValueList(qcInspectionValue);
        return getDataTable(list);
    }

    /**
     * 导出检验项目值列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspectionValue:export')")
    @Log(title = "检验项目值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcInspectionValue qcInspectionValue)
    {
        List<QcInspectionValue> list = qcInspectionValueService.selectQcInspectionValueList(qcInspectionValue);
        ExcelUtil<QcInspectionValue> util = new ExcelUtil<QcInspectionValue>(QcInspectionValue.class);
        util.exportExcel(response, list, "检验项目值数据");
    }

    /**
     * 获取检验项目值详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspectionValue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qcInspectionValueService.selectQcInspectionValueById(id));
    }

    /**
     * 新增检验项目值
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspectionValue:add')")
    @Log(title = "检验项目值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcInspectionValue qcInspectionValue)
    {
        qcInspectionValue.setCreateBy(getUsername());
        return toAjax(qcInspectionValueService.insertQcInspectionValue(qcInspectionValue));
    }

    /**
     * 修改检验项目值
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspectionValue:edit')")
    @Log(title = "检验项目值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcInspectionValue qcInspectionValue)
    {
        qcInspectionValue.setUpdateBy(getUsername());
        return toAjax(qcInspectionValueService.updateQcInspectionValue(qcInspectionValue));
    }

    /**
     * 删除检验项目值
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspectionValue:remove')")
    @Log(title = "检验项目值", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qcInspectionValueService.deleteQcInspectionValueByIds(ids));
    }
}
