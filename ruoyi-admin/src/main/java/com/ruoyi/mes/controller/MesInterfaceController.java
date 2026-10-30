package com.ruoyi.mes.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.mes.domain.MesInterface;
import com.ruoyi.mes.service.IMesInterfaceService;

/**
 * 系统集成接口Controller
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@RestController
@RequestMapping("/mes/integration")
public class MesInterfaceController extends BaseController
{
    @Autowired
    private IMesInterfaceService mesInterfaceService;

    @PreAuthorize("@ss.hasPermi('mes:integration:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesInterface mesInterface)
    {
        startPage();
        List<MesInterface> list = mesInterfaceService.selectMesInterfaceList(mesInterface);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:integration:export')")
    @Log(title = "系统集成接口", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesInterface mesInterface)
    {
        List<MesInterface> list = mesInterfaceService.selectMesInterfaceList(mesInterface);
        ExcelUtil<MesInterface> util = new ExcelUtil<MesInterface>(MesInterface.class);
        util.exportExcel(response, list, "系统集成接口数据");
    }

    @PreAuthorize("@ss.hasPermi('mes:integration:query')")
    @GetMapping(value = "/{interfaceId}")
    public AjaxResult getInfo(@PathVariable("interfaceId") Long interfaceId)
    {
        return success(mesInterfaceService.selectMesInterfaceById(interfaceId));
    }

    @PreAuthorize("@ss.hasPermi('mes:integration:add')")
    @Log(title = "系统集成接口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesInterface mesInterface)
    {
        mesInterface.setCreateBy(getUsername());
        return toAjax(mesInterfaceService.insertMesInterface(mesInterface));
    }

    @PreAuthorize("@ss.hasPermi('mes:integration:edit')")
    @Log(title = "系统集成接口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesInterface mesInterface)
    {
        mesInterface.setUpdateBy(getUsername());
        return toAjax(mesInterfaceService.updateMesInterface(mesInterface));
    }

    @PreAuthorize("@ss.hasPermi('mes:integration:remove')")
    @Log(title = "系统集成接口", businessType = BusinessType.DELETE)
    @DeleteMapping("/{interfaceIds}")
    public AjaxResult remove(@PathVariable Long[] interfaceIds)
    {
        return toAjax(mesInterfaceService.deleteMesInterfaceByIds(interfaceIds));
    }
}
