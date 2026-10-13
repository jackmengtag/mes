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
import com.ruoyi.mes.mt.domain.MtReturnMaterial;
import com.ruoyi.mes.mt.service.IMtReturnMaterialService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 退料Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/mt/returnMaterial")
public class MtReturnMaterialController extends BaseController
{
    @Autowired
    private IMtReturnMaterialService mtReturnMaterialService;

    /**
     * 查询退料列表
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:returnMaterial:list')")
    @GetMapping("/list")
    public TableDataInfo list(MtReturnMaterial mtReturnMaterial)
    {
        startPage();
        List<MtReturnMaterial> list = mtReturnMaterialService.selectMtReturnMaterialList(mtReturnMaterial);
        return getDataTable(list);
    }

    /**
     * 导出退料列表
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:returnMaterial:export')")
    @Log(title = "退料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MtReturnMaterial mtReturnMaterial)
    {
        List<MtReturnMaterial> list = mtReturnMaterialService.selectMtReturnMaterialList(mtReturnMaterial);
        ExcelUtil<MtReturnMaterial> util = new ExcelUtil<MtReturnMaterial>(MtReturnMaterial.class);
        util.exportExcel(response, list, "退料数据");
    }

    /**
     * 获取退料详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:returnMaterial:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mtReturnMaterialService.selectMtReturnMaterialById(id));
    }

    /**
     * 新增退料
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:returnMaterial:add')")
    @Log(title = "退料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MtReturnMaterial mtReturnMaterial)
    {
        mtReturnMaterial.setCreateBy(getUsername());
        return toAjax(mtReturnMaterialService.insertMtReturnMaterial(mtReturnMaterial));
    }

    /**
     * 修改退料
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:returnMaterial:edit')")
    @Log(title = "退料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MtReturnMaterial mtReturnMaterial)
    {
        mtReturnMaterial.setUpdateBy(getUsername());
        return toAjax(mtReturnMaterialService.updateMtReturnMaterial(mtReturnMaterial));
    }

    /**
     * 删除退料
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:returnMaterial:remove')")
    @Log(title = "退料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mtReturnMaterialService.deleteMtReturnMaterialByIds(ids));
    }
}
