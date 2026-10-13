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
import com.ruoyi.mes.qc.domain.QcInspection;
import com.ruoyi.mes.qc.service.IQcInspectionService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 质量检验Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/qc/inspection")
public class QcInspectionController extends BaseController
{
    @Autowired
    private IQcInspectionService qcInspectionService;

    /**
     * 查询质量检验列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspection:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcInspection qcInspection)
    {
        startPage();
        List<QcInspection> list = qcInspectionService.selectQcInspectionList(qcInspection);
        return getDataTable(list);
    }

    /**
     * 导出质量检验列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspection:export')")
    @Log(title = "质量检验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcInspection qcInspection)
    {
        List<QcInspection> list = qcInspectionService.selectQcInspectionList(qcInspection);
        ExcelUtil<QcInspection> util = new ExcelUtil<QcInspection>(QcInspection.class);
        util.exportExcel(response, list, "质量检验数据");
    }

    /**
     * 获取质量检验详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspection:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qcInspectionService.selectQcInspectionById(id));
    }

    /**
     * 新增质量检验
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspection:add')")
    @Log(title = "质量检验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcInspection qcInspection)
    {
        qcInspection.setCreateBy(getUsername());
        return toAjax(qcInspectionService.insertQcInspection(qcInspection));
    }

    /**
     * 修改质量检验
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspection:edit')")
    @Log(title = "质量检验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcInspection qcInspection)
    {
        qcInspection.setUpdateBy(getUsername());
        return toAjax(qcInspectionService.updateQcInspection(qcInspection));
    }

    /**
     * 删除质量检验
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:inspection:remove')")
    @Log(title = "质量检验", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qcInspectionService.deleteQcInspectionByIds(ids));
    }
}
