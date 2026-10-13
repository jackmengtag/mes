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
import com.ruoyi.mes.md.domain.MdBomLine;
import com.ruoyi.mes.md.service.IMdBomLineService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * BOM明细Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/bomLine")
public class MdBomLineController extends BaseController
{
    @Autowired
    private IMdBomLineService mdBomLineService;

    /**
     * 查询BOM明细列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bomLine:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdBomLine mdBomLine)
    {
        startPage();
        List<MdBomLine> list = mdBomLineService.selectMdBomLineList(mdBomLine);
        return getDataTable(list);
    }

    /**
     * 导出BOM明细列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bomLine:export')")
    @Log(title = "BOM明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdBomLine mdBomLine)
    {
        List<MdBomLine> list = mdBomLineService.selectMdBomLineList(mdBomLine);
        ExcelUtil<MdBomLine> util = new ExcelUtil<MdBomLine>(MdBomLine.class);
        util.exportExcel(response, list, "BOM明细数据");
    }

    /**
     * 获取BOM明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bomLine:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdBomLineService.selectMdBomLineById(id));
    }

    /**
     * 新增BOM明细
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bomLine:add')")
    @Log(title = "BOM明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdBomLine mdBomLine)
    {
        mdBomLine.setCreateBy(getUsername());
        return toAjax(mdBomLineService.insertMdBomLine(mdBomLine));
    }

    /**
     * 修改BOM明细
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bomLine:edit')")
    @Log(title = "BOM明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdBomLine mdBomLine)
    {
        mdBomLine.setUpdateBy(getUsername());
        return toAjax(mdBomLineService.updateMdBomLine(mdBomLine));
    }

    /**
     * 删除BOM明细
     */
    @PreAuthorize("@ss.hasPermi('mes:md:bomLine:remove')")
    @Log(title = "BOM明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdBomLineService.deleteMdBomLineByIds(ids));
    }
}
