package com.ruoyi.mes.integration.controller;

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
import com.ruoyi.mes.integration.domain.IntDeadLetter;
import com.ruoyi.mes.integration.service.IIntDeadLetterService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 集成死信Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/integration/deadLetter")
public class IntDeadLetterController extends BaseController
{
    @Autowired
    private IIntDeadLetterService intDeadLetterService;

    /**
     * 查询集成死信列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:deadLetter:list')")
    @GetMapping("/list")
    public TableDataInfo list(IntDeadLetter intDeadLetter)
    {
        startPage();
        List<IntDeadLetter> list = intDeadLetterService.selectIntDeadLetterList(intDeadLetter);
        return getDataTable(list);
    }

    /**
     * 导出集成死信列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:deadLetter:export')")
    @Log(title = "集成死信", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntDeadLetter intDeadLetter)
    {
        List<IntDeadLetter> list = intDeadLetterService.selectIntDeadLetterList(intDeadLetter);
        ExcelUtil<IntDeadLetter> util = new ExcelUtil<IntDeadLetter>(IntDeadLetter.class);
        util.exportExcel(response, list, "集成死信数据");
    }

    /**
     * 获取集成死信详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:deadLetter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(intDeadLetterService.selectIntDeadLetterById(id));
    }

    /**
     * 新增集成死信
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:deadLetter:add')")
    @Log(title = "集成死信", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntDeadLetter intDeadLetter)
    {
        intDeadLetter.setCreateBy(getUsername());
        return toAjax(intDeadLetterService.insertIntDeadLetter(intDeadLetter));
    }

    /**
     * 修改集成死信
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:deadLetter:edit')")
    @Log(title = "集成死信", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntDeadLetter intDeadLetter)
    {
        intDeadLetter.setUpdateBy(getUsername());
        return toAjax(intDeadLetterService.updateIntDeadLetter(intDeadLetter));
    }

    /**
     * 删除集成死信
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:deadLetter:remove')")
    @Log(title = "集成死信", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(intDeadLetterService.deleteIntDeadLetterByIds(ids));
    }
}
