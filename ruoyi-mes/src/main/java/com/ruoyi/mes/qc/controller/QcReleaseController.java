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
import com.ruoyi.mes.qc.domain.QcRelease;
import com.ruoyi.mes.qc.service.IQcReleaseService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 质量放行Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/qc/release")
public class QcReleaseController extends BaseController
{
    @Autowired
    private IQcReleaseService qcReleaseService;

    /**
     * 查询质量放行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:release:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcRelease qcRelease)
    {
        startPage();
        List<QcRelease> list = qcReleaseService.selectQcReleaseList(qcRelease);
        return getDataTable(list);
    }

    /**
     * 导出质量放行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:release:export')")
    @Log(title = "质量放行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcRelease qcRelease)
    {
        List<QcRelease> list = qcReleaseService.selectQcReleaseList(qcRelease);
        ExcelUtil<QcRelease> util = new ExcelUtil<QcRelease>(QcRelease.class);
        util.exportExcel(response, list, "质量放行数据");
    }

    /**
     * 获取质量放行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:release:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qcReleaseService.selectQcReleaseById(id));
    }

    /**
     * 新增质量放行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:release:add')")
    @Log(title = "质量放行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcRelease qcRelease)
    {
        qcRelease.setCreateBy(getUsername());
        return toAjax(qcReleaseService.insertQcRelease(qcRelease));
    }

    /**
     * 修改质量放行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:release:edit')")
    @Log(title = "质量放行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcRelease qcRelease)
    {
        qcRelease.setUpdateBy(getUsername());
        return toAjax(qcReleaseService.updateQcRelease(qcRelease));
    }

    /**
     * 删除质量放行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:release:remove')")
    @Log(title = "质量放行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qcReleaseService.deleteQcReleaseByIds(ids));
    }
}
