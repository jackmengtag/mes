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
import com.ruoyi.mes.md.domain.MdWorkCenter;
import com.ruoyi.mes.md.service.IMdWorkCenterService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工作中心Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/workCenter")
public class MdWorkCenterController extends BaseController
{
    @Autowired
    private IMdWorkCenterService mdWorkCenterService;

    /**
     * 查询工作中心列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workCenter:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdWorkCenter mdWorkCenter)
    {
        startPage();
        List<MdWorkCenter> list = mdWorkCenterService.selectMdWorkCenterList(mdWorkCenter);
        return getDataTable(list);
    }

    /**
     * 导出工作中心列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workCenter:export')")
    @Log(title = "工作中心", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdWorkCenter mdWorkCenter)
    {
        List<MdWorkCenter> list = mdWorkCenterService.selectMdWorkCenterList(mdWorkCenter);
        ExcelUtil<MdWorkCenter> util = new ExcelUtil<MdWorkCenter>(MdWorkCenter.class);
        util.exportExcel(response, list, "工作中心数据");
    }

    /**
     * 获取工作中心详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workCenter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdWorkCenterService.selectMdWorkCenterById(id));
    }

    /**
     * 新增工作中心
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workCenter:add')")
    @Log(title = "工作中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdWorkCenter mdWorkCenter)
    {
        mdWorkCenter.setCreateBy(getUsername());
        return toAjax(mdWorkCenterService.insertMdWorkCenter(mdWorkCenter));
    }

    /**
     * 修改工作中心
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workCenter:edit')")
    @Log(title = "工作中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdWorkCenter mdWorkCenter)
    {
        mdWorkCenter.setUpdateBy(getUsername());
        return toAjax(mdWorkCenterService.updateMdWorkCenter(mdWorkCenter));
    }

    /**
     * 删除工作中心
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workCenter:remove')")
    @Log(title = "工作中心", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdWorkCenterService.deleteMdWorkCenterByIds(ids));
    }
}
