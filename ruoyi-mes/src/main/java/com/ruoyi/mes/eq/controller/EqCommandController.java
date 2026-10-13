package com.ruoyi.mes.eq.controller;

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
import com.ruoyi.mes.eq.domain.EqCommand;
import com.ruoyi.mes.eq.service.IEqCommandService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 设备命令Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/eq/command")
public class EqCommandController extends BaseController
{
    @Autowired
    private IEqCommandService eqCommandService;

    /**
     * 查询设备命令列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:command:list')")
    @GetMapping("/list")
    public TableDataInfo list(EqCommand eqCommand)
    {
        startPage();
        List<EqCommand> list = eqCommandService.selectEqCommandList(eqCommand);
        return getDataTable(list);
    }

    /**
     * 导出设备命令列表
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:command:export')")
    @Log(title = "设备命令", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EqCommand eqCommand)
    {
        List<EqCommand> list = eqCommandService.selectEqCommandList(eqCommand);
        ExcelUtil<EqCommand> util = new ExcelUtil<EqCommand>(EqCommand.class);
        util.exportExcel(response, list, "设备命令数据");
    }

    /**
     * 获取设备命令详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:command:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eqCommandService.selectEqCommandById(id));
    }

    /**
     * 新增设备命令
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:command:add')")
    @Log(title = "设备命令", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EqCommand eqCommand)
    {
        eqCommand.setCreateBy(getUsername());
        return toAjax(eqCommandService.insertEqCommand(eqCommand));
    }

    /**
     * 修改设备命令
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:command:edit')")
    @Log(title = "设备命令", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EqCommand eqCommand)
    {
        eqCommand.setUpdateBy(getUsername());
        return toAjax(eqCommandService.updateEqCommand(eqCommand));
    }

    /**
     * 删除设备命令
     */
    @PreAuthorize("@ss.hasPermi('mes:eq:command:remove')")
    @Log(title = "设备命令", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eqCommandService.deleteEqCommandByIds(ids));
    }
}
