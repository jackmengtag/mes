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
import com.ruoyi.mes.qc.domain.QcRework;
import com.ruoyi.mes.qc.service.IQcReworkService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 返工返修Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/qc/rework")
public class QcReworkController extends BaseController
{
    @Autowired
    private IQcReworkService qcReworkService;

    /**
     * 查询返工返修列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:rework:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcRework qcRework)
    {
        startPage();
        List<QcRework> list = qcReworkService.selectQcReworkList(qcRework);
        return getDataTable(list);
    }

    /**
     * 导出返工返修列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:rework:export')")
    @Log(title = "返工返修", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcRework qcRework)
    {
        List<QcRework> list = qcReworkService.selectQcReworkList(qcRework);
        ExcelUtil<QcRework> util = new ExcelUtil<QcRework>(QcRework.class);
        util.exportExcel(response, list, "返工返修数据");
    }

    /**
     * 获取返工返修详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:rework:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qcReworkService.selectQcReworkById(id));
    }

    /**
     * 新增返工返修
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:rework:add')")
    @Log(title = "返工返修", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcRework qcRework)
    {
        qcRework.setCreateBy(getUsername());
        return toAjax(qcReworkService.insertQcRework(qcRework));
    }

    /**
     * 修改返工返修
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:rework:edit')")
    @Log(title = "返工返修", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcRework qcRework)
    {
        qcRework.setUpdateBy(getUsername());
        return toAjax(qcReworkService.updateQcRework(qcRework));
    }

    /**
     * 删除返工返修
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:rework:remove')")
    @Log(title = "返工返修", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qcReworkService.deleteQcReworkByIds(ids));
    }
}
