package com.ruoyi.mes.md.controller;

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
import com.ruoyi.mes.md.domain.MdEmployee;
import com.ruoyi.mes.md.service.IMdEmployeeService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 员工Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/employee")
public class MdEmployeeController extends BaseController
{
    @Autowired
    private IMdEmployeeService mdEmployeeService;

    /**
     * 查询员工列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdEmployee mdEmployee)
    {
        startPage();
        List<MdEmployee> list = mdEmployeeService.selectMdEmployeeList(mdEmployee);
        return getDataTable(list);
    }

    /**
     * 导出员工列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:employee:export')")
    @Log(title = "员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdEmployee mdEmployee)
    {
        List<MdEmployee> list = mdEmployeeService.selectMdEmployeeList(mdEmployee);
        ExcelUtil<MdEmployee> util = new ExcelUtil<MdEmployee>(MdEmployee.class);
        util.exportExcel(response, list, "员工数据");
    }

    /**
     * 获取员工详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:employee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdEmployeeService.selectMdEmployeeById(id));
    }

    /**
     * 新增员工
     */
    @PreAuthorize("@ss.hasPermi('mes:md:employee:add')")
    @Log(title = "员工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdEmployee mdEmployee)
    {
        mdEmployee.setCreateBy(getUsername());
        return toAjax(mdEmployeeService.insertMdEmployee(mdEmployee));
    }

    /**
     * 修改员工
     */
    @PreAuthorize("@ss.hasPermi('mes:md:employee:edit')")
    @Log(title = "员工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdEmployee mdEmployee)
    {
        mdEmployee.setUpdateBy(getUsername());
        return toAjax(mdEmployeeService.updateMdEmployee(mdEmployee));
    }

    /**
     * 删除员工
     */
    @PreAuthorize("@ss.hasPermi('mes:md:employee:remove')")
    @Log(title = "员工", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdEmployeeService.deleteMdEmployeeByIds(ids));
    }
}
