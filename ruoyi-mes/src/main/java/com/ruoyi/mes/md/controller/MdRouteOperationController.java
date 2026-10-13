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
import com.ruoyi.mes.md.domain.MdRouteOperation;
import com.ruoyi.mes.md.service.IMdRouteOperationService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工艺工序Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/routeOperation")
public class MdRouteOperationController extends BaseController
{
    @Autowired
    private IMdRouteOperationService mdRouteOperationService;

    /**
     * 查询工艺工序列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:routeOperation:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdRouteOperation mdRouteOperation)
    {
        startPage();
        List<MdRouteOperation> list = mdRouteOperationService.selectMdRouteOperationList(mdRouteOperation);
        return getDataTable(list);
    }

    /**
     * 导出工艺工序列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:routeOperation:export')")
    @Log(title = "工艺工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdRouteOperation mdRouteOperation)
    {
        List<MdRouteOperation> list = mdRouteOperationService.selectMdRouteOperationList(mdRouteOperation);
        ExcelUtil<MdRouteOperation> util = new ExcelUtil<MdRouteOperation>(MdRouteOperation.class);
        util.exportExcel(response, list, "工艺工序数据");
    }

    /**
     * 获取工艺工序详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:routeOperation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdRouteOperationService.selectMdRouteOperationById(id));
    }

    /**
     * 新增工艺工序
     */
    @PreAuthorize("@ss.hasPermi('mes:md:routeOperation:add')")
    @Log(title = "工艺工序", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdRouteOperation mdRouteOperation)
    {
        mdRouteOperation.setCreateBy(getUsername());
        return toAjax(mdRouteOperationService.insertMdRouteOperation(mdRouteOperation));
    }

    /**
     * 修改工艺工序
     */
    @PreAuthorize("@ss.hasPermi('mes:md:routeOperation:edit')")
    @Log(title = "工艺工序", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdRouteOperation mdRouteOperation)
    {
        mdRouteOperation.setUpdateBy(getUsername());
        return toAjax(mdRouteOperationService.updateMdRouteOperation(mdRouteOperation));
    }

    /**
     * 删除工艺工序
     */
    @PreAuthorize("@ss.hasPermi('mes:md:routeOperation:remove')")
    @Log(title = "工艺工序", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdRouteOperationService.deleteMdRouteOperationByIds(ids));
    }
}
