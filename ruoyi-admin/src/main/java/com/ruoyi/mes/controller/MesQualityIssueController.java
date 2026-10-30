package com.ruoyi.mes.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.mes.domain.MesQualityIssue;
import com.ruoyi.mes.service.IMesQualityIssueService;

/**
 * 质量问题Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/quality")
public class MesQualityIssueController extends BaseController
{
    @Autowired
    private IMesQualityIssueService mesQualityIssueService;

    @PreAuthorize("@ss.hasPermi('mes:quality:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesQualityIssue mesQualityIssue)
    {
        startPage();
        List<MesQualityIssue> list = mesQualityIssueService.selectMesQualityIssueList(mesQualityIssue);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:quality:export')")
    @Log(title = "质量问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesQualityIssue mesQualityIssue)
    {
        List<MesQualityIssue> list = mesQualityIssueService.selectMesQualityIssueList(mesQualityIssue);
        ExcelUtil<MesQualityIssue> util = new ExcelUtil<MesQualityIssue>(MesQualityIssue.class);
        util.exportExcel(response, list, "质量问题数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:quality:query')")
    @GetMapping(value = "/{issueId}")
    public AjaxResult getInfo(@PathVariable("issueId") Long issueId)
    {
        return success(mesQualityIssueService.selectMesQualityIssueById(issueId));
    }

    @PreAuthorize("@ss.hasPermi('mes:quality:add')")
    @Log(title = "质量问题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesQualityIssue mesQualityIssue)
    {
        mesQualityIssue.setCreateBy(getUsername());
        return toAjax(mesQualityIssueService.insertMesQualityIssue(mesQualityIssue));
    }

    @PreAuthorize("@ss.hasPermi('mes:quality:edit')")
    @Log(title = "质量问题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesQualityIssue mesQualityIssue)
    {
        mesQualityIssue.setUpdateBy(getUsername());
        return toAjax(mesQualityIssueService.updateMesQualityIssue(mesQualityIssue));
    }

    @PreAuthorize("@ss.hasPermi('mes:quality:remove')")
    @Log(title = "质量问题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{issueIds}")
    public AjaxResult remove(@PathVariable Long[] issueIds)
    {
        return toAjax(mesQualityIssueService.deleteMesQualityIssueByIds(issueIds));
    }
}
