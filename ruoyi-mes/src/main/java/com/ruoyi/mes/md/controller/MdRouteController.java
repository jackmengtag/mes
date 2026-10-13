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
import com.ruoyi.mes.md.domain.MdRoute;
import com.ruoyi.mes.md.service.IMdRouteService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工艺路线Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/route")
public class MdRouteController extends BaseController
{
    @Autowired
    private IMdRouteService mdRouteService;

    /**
     * 查询工艺路线列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:route:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdRoute mdRoute)
    {
        startPage();
        List<MdRoute> list = mdRouteService.selectMdRouteList(mdRoute);
        return getDataTable(list);
    }

    /**
     * 导出工艺路线列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:route:export')")
    @Log(title = "工艺路线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdRoute mdRoute)
    {
        List<MdRoute> list = mdRouteService.selectMdRouteList(mdRoute);
        ExcelUtil<MdRoute> util = new ExcelUtil<MdRoute>(MdRoute.class);
        util.exportExcel(response, list, "工艺路线数据");
    }

    /**
     * 获取工艺路线详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:route:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdRouteService.selectMdRouteById(id));
    }

    /**
     * 新增工艺路线
     */
    @PreAuthorize("@ss.hasPermi('mes:md:route:add')")
    @Log(title = "工艺路线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdRoute mdRoute)
    {
        mdRoute.setCreateBy(getUsername());
        return toAjax(mdRouteService.insertMdRoute(mdRoute));
    }

    /**
     * 修改工艺路线
     */
    @PreAuthorize("@ss.hasPermi('mes:md:route:edit')")
    @Log(title = "工艺路线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdRoute mdRoute)
    {
        mdRoute.setUpdateBy(getUsername());
        return toAjax(mdRouteService.updateMdRoute(mdRoute));
    }

    /**
     * 删除工艺路线
     */
    @PreAuthorize("@ss.hasPermi('mes:md:route:remove')")
    @Log(title = "工艺路线", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdRouteService.deleteMdRouteByIds(ids));
    }
}
