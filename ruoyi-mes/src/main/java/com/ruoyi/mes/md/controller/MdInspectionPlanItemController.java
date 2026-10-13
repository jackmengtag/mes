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
import com.ruoyi.mes.md.domain.MdInspectionPlanItem;
import com.ruoyi.mes.md.service.IMdInspectionPlanItemService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 检验方案明细Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/inspectionPlanItem")
public class MdInspectionPlanItemController extends BaseController
{
    @Autowired
    private IMdInspectionPlanItemService mdInspectionPlanItemService;

    /**
     * 查询检验方案明细列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlanItem:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdInspectionPlanItem mdInspectionPlanItem)
    {
        startPage();
        List<MdInspectionPlanItem> list = mdInspectionPlanItemService.selectMdInspectionPlanItemList(mdInspectionPlanItem);
        return getDataTable(list);
    }

    /**
     * 导出检验方案明细列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlanItem:export')")
    @Log(title = "检验方案明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdInspectionPlanItem mdInspectionPlanItem)
    {
        List<MdInspectionPlanItem> list = mdInspectionPlanItemService.selectMdInspectionPlanItemList(mdInspectionPlanItem);
        ExcelUtil<MdInspectionPlanItem> util = new ExcelUtil<MdInspectionPlanItem>(MdInspectionPlanItem.class);
        util.exportExcel(response, list, "检验方案明细数据");
    }

    /**
     * 获取检验方案明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlanItem:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdInspectionPlanItemService.selectMdInspectionPlanItemById(id));
    }

    /**
     * 新增检验方案明细
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlanItem:add')")
    @Log(title = "检验方案明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdInspectionPlanItem mdInspectionPlanItem)
    {
        mdInspectionPlanItem.setCreateBy(getUsername());
        return toAjax(mdInspectionPlanItemService.insertMdInspectionPlanItem(mdInspectionPlanItem));
    }

    /**
     * 修改检验方案明细
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlanItem:edit')")
    @Log(title = "检验方案明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdInspectionPlanItem mdInspectionPlanItem)
    {
        mdInspectionPlanItem.setUpdateBy(getUsername());
        return toAjax(mdInspectionPlanItemService.updateMdInspectionPlanItem(mdInspectionPlanItem));
    }

    /**
     * 删除检验方案明细
     */
    @PreAuthorize("@ss.hasPermi('mes:md:inspectionPlanItem:remove')")
    @Log(title = "检验方案明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdInspectionPlanItemService.deleteMdInspectionPlanItemByIds(ids));
    }
}
