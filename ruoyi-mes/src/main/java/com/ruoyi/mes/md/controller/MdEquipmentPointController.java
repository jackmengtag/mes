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
import com.ruoyi.mes.md.domain.MdEquipmentPoint;
import com.ruoyi.mes.md.service.IMdEquipmentPointService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备采集点位Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/equipmentPoint")
public class MdEquipmentPointController extends BaseController
{
    @Autowired
    private IMdEquipmentPointService mdEquipmentPointService;

    /**
     * 查询设备采集点位列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipmentPoint:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdEquipmentPoint mdEquipmentPoint)
    {
        startPage();
        List<MdEquipmentPoint> list = mdEquipmentPointService.selectMdEquipmentPointList(mdEquipmentPoint);
        return getDataTable(list);
    }

    /**
     * 导出设备采集点位列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipmentPoint:export')")
    @Log(title = "设备采集点位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdEquipmentPoint mdEquipmentPoint)
    {
        List<MdEquipmentPoint> list = mdEquipmentPointService.selectMdEquipmentPointList(mdEquipmentPoint);
        ExcelUtil<MdEquipmentPoint> util = new ExcelUtil<MdEquipmentPoint>(MdEquipmentPoint.class);
        util.exportExcel(response, list, "设备采集点位数据");
    }

    /**
     * 获取设备采集点位详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipmentPoint:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdEquipmentPointService.selectMdEquipmentPointById(id));
    }

    /**
     * 新增设备采集点位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipmentPoint:add')")
    @Log(title = "设备采集点位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdEquipmentPoint mdEquipmentPoint)
    {
        mdEquipmentPoint.setCreateBy(getUsername());
        return toAjax(mdEquipmentPointService.insertMdEquipmentPoint(mdEquipmentPoint));
    }

    /**
     * 修改设备采集点位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipmentPoint:edit')")
    @Log(title = "设备采集点位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdEquipmentPoint mdEquipmentPoint)
    {
        mdEquipmentPoint.setUpdateBy(getUsername());
        return toAjax(mdEquipmentPointService.updateMdEquipmentPoint(mdEquipmentPoint));
    }

    /**
     * 删除设备采集点位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipmentPoint:remove')")
    @Log(title = "设备采集点位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdEquipmentPointService.deleteMdEquipmentPointByIds(ids));
    }
}
