package com.ruoyi.mes.om.controller;

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
import com.ruoyi.mes.om.domain.OmDemand;
import com.ruoyi.mes.om.service.IOmDemandService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 生产需求Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/om/demand")
public class OmDemandController extends BaseController
{
    @Autowired
    private IOmDemandService omDemandService;

    /**
     * 查询生产需求列表
     */
    @PreAuthorize("@ss.hasPermi('mes:om:demand:list')")
    @GetMapping("/list")
    public TableDataInfo list(OmDemand omDemand)
    {
        startPage();
        List<OmDemand> list = omDemandService.selectOmDemandList(omDemand);
        return getDataTable(list);
    }

    /**
     * 导出生产需求列表
     */
    @PreAuthorize("@ss.hasPermi('mes:om:demand:export')")
    @Log(title = "生产需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OmDemand omDemand)
    {
        List<OmDemand> list = omDemandService.selectOmDemandList(omDemand);
        ExcelUtil<OmDemand> util = new ExcelUtil<OmDemand>(OmDemand.class);
        util.exportExcel(response, list, "生产需求数据");
    }

    /**
     * 获取生产需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:om:demand:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(omDemandService.selectOmDemandById(id));
    }

    /**
     * 新增生产需求
     */
    @PreAuthorize("@ss.hasPermi('mes:om:demand:add')")
    @Log(title = "生产需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmDemand omDemand)
    {
        omDemand.setCreateBy(getUsername());
        return toAjax(omDemandService.insertOmDemand(omDemand));
    }

    /**
     * 修改生产需求
     */
    @PreAuthorize("@ss.hasPermi('mes:om:demand:edit')")
    @Log(title = "生产需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmDemand omDemand)
    {
        omDemand.setUpdateBy(getUsername());
        return toAjax(omDemandService.updateOmDemand(omDemand));
    }

    /**
     * 删除生产需求
     */
    @PreAuthorize("@ss.hasPermi('mes:om:demand:remove')")
    @Log(title = "生产需求", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(omDemandService.deleteOmDemandByIds(ids));
    }
}
