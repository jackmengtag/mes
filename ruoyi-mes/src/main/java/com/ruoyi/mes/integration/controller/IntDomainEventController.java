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
import com.ruoyi.mes.integration.domain.IntDomainEvent;
import com.ruoyi.mes.integration.service.IIntDomainEventService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 领域事件Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/integration/event")
public class IntDomainEventController extends BaseController
{
    @Autowired
    private IIntDomainEventService intDomainEventService;

    /**
     * 查询领域事件列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:event:list')")
    @GetMapping("/list")
    public TableDataInfo list(IntDomainEvent intDomainEvent)
    {
        startPage();
        List<IntDomainEvent> list = intDomainEventService.selectIntDomainEventList(intDomainEvent);
        return getDataTable(list);
    }

    /**
     * 导出领域事件列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:event:export')")
    @Log(title = "领域事件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntDomainEvent intDomainEvent)
    {
        List<IntDomainEvent> list = intDomainEventService.selectIntDomainEventList(intDomainEvent);
        ExcelUtil<IntDomainEvent> util = new ExcelUtil<IntDomainEvent>(IntDomainEvent.class);
        util.exportExcel(response, list, "领域事件数据");
    }

    /**
     * 获取领域事件详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:event:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(intDomainEventService.selectIntDomainEventById(id));
    }

    /**
     * 新增领域事件
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:event:add')")
    @Log(title = "领域事件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntDomainEvent intDomainEvent)
    {
        intDomainEvent.setCreateBy(getUsername());
        return toAjax(intDomainEventService.insertIntDomainEvent(intDomainEvent));
    }

    /**
     * 修改领域事件
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:event:edit')")
    @Log(title = "领域事件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntDomainEvent intDomainEvent)
    {
        intDomainEvent.setUpdateBy(getUsername());
        return toAjax(intDomainEventService.updateIntDomainEvent(intDomainEvent));
    }

    /**
     * 删除领域事件
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:event:remove')")
    @Log(title = "领域事件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(intDomainEventService.deleteIntDomainEventByIds(ids));
    }
}
