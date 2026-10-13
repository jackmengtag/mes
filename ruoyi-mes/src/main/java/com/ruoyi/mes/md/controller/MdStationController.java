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
import com.ruoyi.mes.md.domain.MdStation;
import com.ruoyi.mes.md.service.IMdStationService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工位Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/station")
public class MdStationController extends BaseController
{
    @Autowired
    private IMdStationService mdStationService;

    /**
     * 查询工位列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:station:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdStation mdStation)
    {
        startPage();
        List<MdStation> list = mdStationService.selectMdStationList(mdStation);
        return getDataTable(list);
    }

    /**
     * 导出工位列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:station:export')")
    @Log(title = "工位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdStation mdStation)
    {
        List<MdStation> list = mdStationService.selectMdStationList(mdStation);
        ExcelUtil<MdStation> util = new ExcelUtil<MdStation>(MdStation.class);
        util.exportExcel(response, list, "工位数据");
    }

    /**
     * 获取工位详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:station:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdStationService.selectMdStationById(id));
    }

    /**
     * 新增工位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:station:add')")
    @Log(title = "工位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdStation mdStation)
    {
        mdStation.setCreateBy(getUsername());
        return toAjax(mdStationService.insertMdStation(mdStation));
    }

    /**
     * 修改工位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:station:edit')")
    @Log(title = "工位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdStation mdStation)
    {
        mdStation.setUpdateBy(getUsername());
        return toAjax(mdStationService.updateMdStation(mdStation));
    }

    /**
     * 删除工位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:station:remove')")
    @Log(title = "工位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdStationService.deleteMdStationByIds(ids));
    }
}
