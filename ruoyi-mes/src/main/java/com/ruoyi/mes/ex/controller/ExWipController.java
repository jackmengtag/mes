package com.ruoyi.mes.ex.controller;

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
import com.ruoyi.mes.ex.domain.ExWip;
import com.ruoyi.mes.ex.service.IExWipService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 在制品Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ex/wip")
public class ExWipController extends BaseController
{
    @Autowired
    private IExWipService exWipService;

    /**
     * 查询在制品列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:wip:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExWip exWip)
    {
        startPage();
        List<ExWip> list = exWipService.selectExWipList(exWip);
        return getDataTable(list);
    }

    /**
     * 导出在制品列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:wip:export')")
    @Log(title = "在制品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExWip exWip)
    {
        List<ExWip> list = exWipService.selectExWipList(exWip);
        ExcelUtil<ExWip> util = new ExcelUtil<ExWip>(ExWip.class);
        util.exportExcel(response, list, "在制品数据");
    }

    /**
     * 获取在制品详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:wip:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exWipService.selectExWipById(id));
    }

    /**
     * 新增在制品
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:wip:add')")
    @Log(title = "在制品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExWip exWip)
    {
        exWip.setCreateBy(getUsername());
        return toAjax(exWipService.insertExWip(exWip));
    }

    /**
     * 修改在制品
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:wip:edit')")
    @Log(title = "在制品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExWip exWip)
    {
        exWip.setUpdateBy(getUsername());
        return toAjax(exWipService.updateExWip(exWip));
    }

    /**
     * 删除在制品
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:wip:remove')")
    @Log(title = "在制品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exWipService.deleteExWipByIds(ids));
    }
}
