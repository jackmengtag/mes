package com.ruoyi.mes.md.controller;

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
import com.ruoyi.mes.md.domain.MdShift;
import com.ruoyi.mes.md.service.IMdShiftService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 班次Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/shift")
public class MdShiftController extends BaseController
{
    @Autowired
    private IMdShiftService mdShiftService;

    /**
     * 查询班次列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:shift:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdShift mdShift)
    {
        startPage();
        List<MdShift> list = mdShiftService.selectMdShiftList(mdShift);
        return getDataTable(list);
    }

    /**
     * 导出班次列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:shift:export')")
    @Log(title = "班次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdShift mdShift)
    {
        List<MdShift> list = mdShiftService.selectMdShiftList(mdShift);
        ExcelUtil<MdShift> util = new ExcelUtil<MdShift>(MdShift.class);
        util.exportExcel(response, list, "班次数据");
    }

    /**
     * 获取班次详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:shift:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdShiftService.selectMdShiftById(id));
    }

    /**
     * 新增班次
     */
    @PreAuthorize("@ss.hasPermi('mes:md:shift:add')")
    @Log(title = "班次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdShift mdShift)
    {
        mdShift.setCreateBy(getUsername());
        return toAjax(mdShiftService.insertMdShift(mdShift));
    }

    /**
     * 修改班次
     */
    @PreAuthorize("@ss.hasPermi('mes:md:shift:edit')")
    @Log(title = "班次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdShift mdShift)
    {
        mdShift.setUpdateBy(getUsername());
        return toAjax(mdShiftService.updateMdShift(mdShift));
    }

    /**
     * 删除班次
     */
    @PreAuthorize("@ss.hasPermi('mes:md:shift:remove')")
    @Log(title = "班次", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdShiftService.deleteMdShiftByIds(ids));
    }
}
