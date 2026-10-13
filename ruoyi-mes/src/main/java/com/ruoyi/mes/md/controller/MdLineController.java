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
import com.ruoyi.mes.md.domain.MdLine;
import com.ruoyi.mes.md.service.IMdLineService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产线Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/line")
public class MdLineController extends BaseController
{
    @Autowired
    private IMdLineService mdLineService;

    /**
     * 查询产线列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:line:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdLine mdLine)
    {
        startPage();
        List<MdLine> list = mdLineService.selectMdLineList(mdLine);
        return getDataTable(list);
    }

    /**
     * 导出产线列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:line:export')")
    @Log(title = "产线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdLine mdLine)
    {
        List<MdLine> list = mdLineService.selectMdLineList(mdLine);
        ExcelUtil<MdLine> util = new ExcelUtil<MdLine>(MdLine.class);
        util.exportExcel(response, list, "产线数据");
    }

    /**
     * 获取产线详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:line:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdLineService.selectMdLineById(id));
    }

    /**
     * 新增产线
     */
    @PreAuthorize("@ss.hasPermi('mes:md:line:add')")
    @Log(title = "产线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdLine mdLine)
    {
        mdLine.setCreateBy(getUsername());
        return toAjax(mdLineService.insertMdLine(mdLine));
    }

    /**
     * 修改产线
     */
    @PreAuthorize("@ss.hasPermi('mes:md:line:edit')")
    @Log(title = "产线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdLine mdLine)
    {
        mdLine.setUpdateBy(getUsername());
        return toAjax(mdLineService.updateMdLine(mdLine));
    }

    /**
     * 删除产线
     */
    @PreAuthorize("@ss.hasPermi('mes:md:line:remove')")
    @Log(title = "产线", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdLineService.deleteMdLineByIds(ids));
    }
}
