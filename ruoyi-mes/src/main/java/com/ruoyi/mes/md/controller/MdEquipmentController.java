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
import com.ruoyi.mes.md.domain.MdEquipment;
import com.ruoyi.mes.md.service.IMdEquipmentService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备档案Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/equipment")
public class MdEquipmentController extends BaseController
{
    @Autowired
    private IMdEquipmentService mdEquipmentService;

    /**
     * 查询设备档案列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdEquipment mdEquipment)
    {
        startPage();
        List<MdEquipment> list = mdEquipmentService.selectMdEquipmentList(mdEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备档案列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipment:export')")
    @Log(title = "设备档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdEquipment mdEquipment)
    {
        List<MdEquipment> list = mdEquipmentService.selectMdEquipmentList(mdEquipment);
        ExcelUtil<MdEquipment> util = new ExcelUtil<MdEquipment>(MdEquipment.class);
        util.exportExcel(response, list, "设备档案数据");
    }

    /**
     * 获取设备档案详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdEquipmentService.selectMdEquipmentById(id));
    }

    /**
     * 新增设备档案
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipment:add')")
    @Log(title = "设备档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdEquipment mdEquipment)
    {
        mdEquipment.setCreateBy(getUsername());
        return toAjax(mdEquipmentService.insertMdEquipment(mdEquipment));
    }

    /**
     * 修改设备档案
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipment:edit')")
    @Log(title = "设备档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdEquipment mdEquipment)
    {
        mdEquipment.setUpdateBy(getUsername());
        return toAjax(mdEquipmentService.updateMdEquipment(mdEquipment));
    }

    /**
     * 删除设备档案
     */
    @PreAuthorize("@ss.hasPermi('mes:md:equipment:remove')")
    @Log(title = "设备档案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdEquipmentService.deleteMdEquipmentByIds(ids));
    }
}
