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
import com.ruoyi.mes.mt.domain.MtSupplementMaterial;
import com.ruoyi.mes.mt.service.IMtSupplementMaterialService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 补料Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/mt/supplementMaterial")
public class MtSupplementMaterialController extends BaseController
{
    @Autowired
    private IMtSupplementMaterialService mtSupplementMaterialService;

    /**
     * 查询补料列表
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:supplementMaterial:list')")
    @GetMapping("/list")
    public TableDataInfo list(MtSupplementMaterial mtSupplementMaterial)
    {
        startPage();
        List<MtSupplementMaterial> list = mtSupplementMaterialService.selectMtSupplementMaterialList(mtSupplementMaterial);
        return getDataTable(list);
    }

    /**
     * 导出补料列表
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:supplementMaterial:export')")
    @Log(title = "补料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MtSupplementMaterial mtSupplementMaterial)
    {
        List<MtSupplementMaterial> list = mtSupplementMaterialService.selectMtSupplementMaterialList(mtSupplementMaterial);
        ExcelUtil<MtSupplementMaterial> util = new ExcelUtil<MtSupplementMaterial>(MtSupplementMaterial.class);
        util.exportExcel(response, list, "补料数据");
    }

    /**
     * 获取补料详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:supplementMaterial:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mtSupplementMaterialService.selectMtSupplementMaterialById(id));
    }

    /**
     * 新增补料
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:supplementMaterial:add')")
    @Log(title = "补料", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MtSupplementMaterial mtSupplementMaterial)
    {
        mtSupplementMaterial.setCreateBy(getUsername());
        return toAjax(mtSupplementMaterialService.insertMtSupplementMaterial(mtSupplementMaterial));
    }

    /**
     * 修改补料
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:supplementMaterial:edit')")
    @Log(title = "补料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MtSupplementMaterial mtSupplementMaterial)
    {
        mtSupplementMaterial.setUpdateBy(getUsername());
        return toAjax(mtSupplementMaterialService.updateMtSupplementMaterial(mtSupplementMaterial));
    }

    /**
     * 删除补料
     */
    @PreAuthorize("@ss.hasPermi('mes:mt:supplementMaterial:remove')")
    @Log(title = "补料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mtSupplementMaterialService.deleteMtSupplementMaterialByIds(ids));
    }
}
