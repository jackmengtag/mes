package com.ruoyi.mes.ex.controller;

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
import com.ruoyi.mes.ex.domain.ExProcessParam;
import com.ruoyi.mes.ex.service.IExProcessParamService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工艺参数采集Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ex/processParam")
public class ExProcessParamController extends BaseController
{
    @Autowired
    private IExProcessParamService exProcessParamService;

    /**
     * 查询工艺参数采集列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:processParam:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExProcessParam exProcessParam)
    {
        startPage();
        List<ExProcessParam> list = exProcessParamService.selectExProcessParamList(exProcessParam);
        return getDataTable(list);
    }

    /**
     * 导出工艺参数采集列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:processParam:export')")
    @Log(title = "工艺参数采集", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExProcessParam exProcessParam)
    {
        List<ExProcessParam> list = exProcessParamService.selectExProcessParamList(exProcessParam);
        ExcelUtil<ExProcessParam> util = new ExcelUtil<ExProcessParam>(ExProcessParam.class);
        util.exportExcel(response, list, "工艺参数采集数据");
    }

    /**
     * 获取工艺参数采集详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:processParam:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exProcessParamService.selectExProcessParamById(id));
    }

    /**
     * 新增工艺参数采集
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:processParam:add')")
    @Log(title = "工艺参数采集", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExProcessParam exProcessParam)
    {
        exProcessParam.setCreateBy(getUsername());
        return toAjax(exProcessParamService.insertExProcessParam(exProcessParam));
    }

    /**
     * 修改工艺参数采集
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:processParam:edit')")
    @Log(title = "工艺参数采集", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExProcessParam exProcessParam)
    {
        exProcessParam.setUpdateBy(getUsername());
        return toAjax(exProcessParamService.updateExProcessParam(exProcessParam));
    }

    /**
     * 删除工艺参数采集
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:processParam:remove')")
    @Log(title = "工艺参数采集", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exProcessParamService.deleteExProcessParamByIds(ids));
    }
}
