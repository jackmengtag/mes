package com.ruoyi.mes.mt.controller;

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
import com.ruoyi.mes.mt.domain.MtMaterialConsumption;
import com.ruoyi.mes.mt.service.IMtMaterialConsumptionService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 物料批次消耗Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/mt/consumption")
public class MtMaterialConsumptionController extends BaseController
{
    @Autowired
    private IMtMaterialConsumptionService mtMaterialConsumptionService;

    /**
     * 查询物料批次消耗列表
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:consumption:list')")
    @GetMapping("/list")
    public TableDataInfo list(MtMaterialConsumption mtMaterialConsumption)
    {
        startPage();
        List<MtMaterialConsumption> list = mtMaterialConsumptionService.selectMtMaterialConsumptionList(mtMaterialConsumption);
        return getDataTable(list);
    }

    /**
     * 导出物料批次消耗列表
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:consumption:export')")
    @Log(title = "物料批次消耗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MtMaterialConsumption mtMaterialConsumption)
    {
        List<MtMaterialConsumption> list = mtMaterialConsumptionService.selectMtMaterialConsumptionList(mtMaterialConsumption);
        ExcelUtil<MtMaterialConsumption> util = new ExcelUtil<MtMaterialConsumption>(MtMaterialConsumption.class);
        util.exportExcel(response, list, "物料批次消耗数据");
    }

    /**
     * 获取物料批次消耗详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:consumption:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mtMaterialConsumptionService.selectMtMaterialConsumptionById(id));
    }

    /**
     * 新增物料批次消耗
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:consumption:add')")
    @Log(title = "物料批次消耗", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MtMaterialConsumption mtMaterialConsumption)
    {
        mtMaterialConsumption.setCreateBy(getUsername());
        return toAjax(mtMaterialConsumptionService.insertMtMaterialConsumption(mtMaterialConsumption));
    }

    /**
     * 修改物料批次消耗
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:consumption:edit')")
    @Log(title = "物料批次消耗", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MtMaterialConsumption mtMaterialConsumption)
    {
        mtMaterialConsumption.setUpdateBy(getUsername());
        return toAjax(mtMaterialConsumptionService.updateMtMaterialConsumption(mtMaterialConsumption));
    }

    /**
     * 删除物料批次消耗
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:consumption:remove')")
    @Log(title = "物料批次消耗", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mtMaterialConsumptionService.deleteMtMaterialConsumptionByIds(ids));
    }
}
