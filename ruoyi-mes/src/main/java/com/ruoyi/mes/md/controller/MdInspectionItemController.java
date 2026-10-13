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
import com.ruoyi.mes.md.domain.MdInspectionItem;
import com.ruoyi.mes.md.service.IMdInspectionItemService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 检验项目Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/inspectionItem")
public class MdInspectionItemController extends BaseController
{
    @Autowired
    private IMdInspectionItemService mdInspectionItemService;

    /**
     * 查询检验项目列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionItem:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdInspectionItem mdInspectionItem)
    {
        startPage();
        List<MdInspectionItem> list = mdInspectionItemService.selectMdInspectionItemList(mdInspectionItem);
        return getDataTable(list);
    }

    /**
     * 导出检验项目列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionItem:export')")
    @Log(title = "检验项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdInspectionItem mdInspectionItem)
    {
        List<MdInspectionItem> list = mdInspectionItemService.selectMdInspectionItemList(mdInspectionItem);
        ExcelUtil<MdInspectionItem> util = new ExcelUtil<MdInspectionItem>(MdInspectionItem.class);
        util.exportExcel(response, list, "检验项目数据");
    }

    /**
     * 获取检验项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionItem:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdInspectionItemService.selectMdInspectionItemById(id));
    }

    /**
     * 新增检验项目
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionItem:add')")
    @Log(title = "检验项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdInspectionItem mdInspectionItem)
    {
        mdInspectionItem.setCreateBy(getUsername());
        return toAjax(mdInspectionItemService.insertMdInspectionItem(mdInspectionItem));
    }

    /**
     * 修改检验项目
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionItem:edit')")
    @Log(title = "检验项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdInspectionItem mdInspectionItem)
    {
        mdInspectionItem.setUpdateBy(getUsername());
        return toAjax(mdInspectionItemService.updateMdInspectionItem(mdInspectionItem));
    }

    /**
     * 删除检验项目
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionItem:remove')")
    @Log(title = "检验项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdInspectionItemService.deleteMdInspectionItemByIds(ids));
    }
}
