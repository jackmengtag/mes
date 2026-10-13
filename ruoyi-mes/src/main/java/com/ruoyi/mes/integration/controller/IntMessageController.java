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
import com.ruoyi.mes.integration.domain.IntMessage;
import com.ruoyi.mes.integration.service.IIntMessageService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 集成消息Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/integration/message")
public class IntMessageController extends BaseController
{
    @Autowired
    private IIntMessageService intMessageService;

    /**
     * 查询集成消息列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(IntMessage intMessage)
    {
        startPage();
        List<IntMessage> list = intMessageService.selectIntMessageList(intMessage);
        return getDataTable(list);
    }

    /**
     * 导出集成消息列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:message:export')")
    @Log(title = "集成消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntMessage intMessage)
    {
        List<IntMessage> list = intMessageService.selectIntMessageList(intMessage);
        ExcelUtil<IntMessage> util = new ExcelUtil<IntMessage>(IntMessage.class);
        util.exportExcel(response, list, "集成消息数据");
    }

    /**
     * 获取集成消息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(intMessageService.selectIntMessageById(id));
    }

    /**
     * 新增集成消息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:message:add')")
    @Log(title = "集成消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntMessage intMessage)
    {
        intMessage.setCreateBy(getUsername());
        return toAjax(intMessageService.insertIntMessage(intMessage));
    }

    /**
     * 修改集成消息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:message:edit')")
    @Log(title = "集成消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntMessage intMessage)
    {
        intMessage.setUpdateBy(getUsername());
        return toAjax(intMessageService.updateIntMessage(intMessage));
    }

    /**
     * 删除集成消息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:message:remove')")
    @Log(title = "集成消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(intMessageService.deleteIntMessageByIds(ids));
    }
}
