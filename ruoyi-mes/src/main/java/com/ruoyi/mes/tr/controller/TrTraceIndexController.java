package com.ruoyi.mes.tr.controller;

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
import com.ruoyi.mes.tr.domain.TrTraceIndex;
import com.ruoyi.mes.tr.service.ITrTraceIndexService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 追溯索引Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/tr/index")
public class TrTraceIndexController extends BaseController
{
    @Autowired
    private ITrTraceIndexService trTraceIndexService;

    /**
     * 查询追溯索引列表
     */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrTraceIndex trTraceIndex)
    {
        startPage();
        List<TrTraceIndex> list = trTraceIndexService.selectTrTraceIndexList(trTraceIndex);
        return getDataTable(list);
    }

    /**
     * 导出追溯索引列表
     */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:export')")
    @Log(title = "追溯索引", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrTraceIndex trTraceIndex)
    {
        List<TrTraceIndex> list = trTraceIndexService.selectTrTraceIndexList(trTraceIndex);
        ExcelUtil<TrTraceIndex> util = new ExcelUtil<TrTraceIndex>(TrTraceIndex.class);
        util.exportExcel(response, list, "追溯索引数据");
    }

    /**
     * 获取追溯索引详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(trTraceIndexService.selectTrTraceIndexById(id));
    }

    /**
     * 新增追溯索引
     */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:add')")
    @Log(title = "追溯索引", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrTraceIndex trTraceIndex)
    {
        trTraceIndex.setCreateBy(getUsername());
        return toAjax(trTraceIndexService.insertTrTraceIndex(trTraceIndex));
    }

    /**
     * 修改追溯索引
     */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:edit')")
    @Log(title = "追溯索引", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrTraceIndex trTraceIndex)
    {
        trTraceIndex.setUpdateBy(getUsername());
        return toAjax(trTraceIndexService.updateTrTraceIndex(trTraceIndex));
    }

    /**
     * 删除追溯索引
     */
    @PreAuthorize("@ss.hasPermi('mes:tr:index:remove')")
    @Log(title = "追溯索引", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(trTraceIndexService.deleteTrTraceIndexByIds(ids));
    }
}
