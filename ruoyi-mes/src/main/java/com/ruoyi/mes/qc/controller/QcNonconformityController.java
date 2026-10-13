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
import com.ruoyi.mes.qc.domain.QcNonconformity;
import com.ruoyi.mes.qc.service.IQcNonconformityService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 不合格品记录Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/qc/nonconformity")
public class QcNonconformityController extends BaseController
{
    @Autowired
    private IQcNonconformityService qcNonconformityService;

    /**
     * 查询不合格品记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:nonconformity:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcNonconformity qcNonconformity)
    {
        startPage();
        List<QcNonconformity> list = qcNonconformityService.selectQcNonconformityList(qcNonconformity);
        return getDataTable(list);
    }

    /**
     * 导出不合格品记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:nonconformity:export')")
    @Log(title = "不合格品记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcNonconformity qcNonconformity)
    {
        List<QcNonconformity> list = qcNonconformityService.selectQcNonconformityList(qcNonconformity);
        ExcelUtil<QcNonconformity> util = new ExcelUtil<QcNonconformity>(QcNonconformity.class);
        util.exportExcel(response, list, "不合格品记录数据");
    }

    /**
     * 获取不合格品记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:nonconformity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qcNonconformityService.selectQcNonconformityById(id));
    }

    /**
     * 新增不合格品记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:nonconformity:add')")
    @Log(title = "不合格品记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcNonconformity qcNonconformity)
    {
        qcNonconformity.setCreateBy(getUsername());
        return toAjax(qcNonconformityService.insertQcNonconformity(qcNonconformity));
    }

    /**
     * 修改不合格品记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:nonconformity:edit')")
    @Log(title = "不合格品记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcNonconformity qcNonconformity)
    {
        qcNonconformity.setUpdateBy(getUsername());
        return toAjax(qcNonconformityService.updateQcNonconformity(qcNonconformity));
    }

    /**
     * 删除不合格品记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:nonconformity:remove')")
    @Log(title = "不合格品记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qcNonconformityService.deleteQcNonconformityByIds(ids));
    }
}
