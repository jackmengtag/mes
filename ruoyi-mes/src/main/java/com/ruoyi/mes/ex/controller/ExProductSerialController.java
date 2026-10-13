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
import com.ruoyi.mes.ex.domain.ExProductSerial;
import com.ruoyi.mes.ex.service.IExProductSerialService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产品序列号Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/ex/serial")
public class ExProductSerialController extends BaseController
{
    @Autowired
    private IExProductSerialService exProductSerialService;

    /**
     * 查询产品序列号列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:serial:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExProductSerial exProductSerial)
    {
        startPage();
        List<ExProductSerial> list = exProductSerialService.selectExProductSerialList(exProductSerial);
        return getDataTable(list);
    }

    /**
     * 导出产品序列号列表
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:serial:export')")
    @Log(title = "产品序列号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExProductSerial exProductSerial)
    {
        List<ExProductSerial> list = exProductSerialService.selectExProductSerialList(exProductSerial);
        ExcelUtil<ExProductSerial> util = new ExcelUtil<ExProductSerial>(ExProductSerial.class);
        util.exportExcel(response, list, "产品序列号数据");
    }

    /**
     * 获取产品序列号详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:serial:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exProductSerialService.selectExProductSerialById(id));
    }

    /**
     * 新增产品序列号
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:serial:add')")
    @Log(title = "产品序列号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExProductSerial exProductSerial)
    {
        exProductSerial.setCreateBy(getUsername());
        return toAjax(exProductSerialService.insertExProductSerial(exProductSerial));
    }

    /**
     * 修改产品序列号
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:serial:edit')")
    @Log(title = "产品序列号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExProductSerial exProductSerial)
    {
        exProductSerial.setUpdateBy(getUsername());
        return toAjax(exProductSerialService.updateExProductSerial(exProductSerial));
    }

    /**
     * 删除产品序列号
     */
    @PreAuthorize("@ss.hasPermi('mes:ex:serial:remove')")
    @Log(title = "产品序列号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exProductSerialService.deleteExProductSerialByIds(ids));
    }
}
