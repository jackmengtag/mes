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
import com.ruoyi.mes.md.domain.MdDefectCode;
import com.ruoyi.mes.md.service.IMdDefectCodeService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 缺陷代码Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/defectCode")
public class MdDefectCodeController extends BaseController
{
    @Autowired
    private IMdDefectCodeService mdDefectCodeService;

    /**
     * 查询缺陷代码列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:defectCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdDefectCode mdDefectCode)
    {
        startPage();
        List<MdDefectCode> list = mdDefectCodeService.selectMdDefectCodeList(mdDefectCode);
        return getDataTable(list);
    }

    /**
     * 导出缺陷代码列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:defectCode:export')")
    @Log(title = "缺陷代码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdDefectCode mdDefectCode)
    {
        List<MdDefectCode> list = mdDefectCodeService.selectMdDefectCodeList(mdDefectCode);
        ExcelUtil<MdDefectCode> util = new ExcelUtil<MdDefectCode>(MdDefectCode.class);
        util.exportExcel(response, list, "缺陷代码数据");
    }

    /**
     * 获取缺陷代码详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:defectCode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdDefectCodeService.selectMdDefectCodeById(id));
    }

    /**
     * 新增缺陷代码
     */
    @PreAuthorize("@ss.hasPermi('mes:md:defectCode:add')")
    @Log(title = "缺陷代码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdDefectCode mdDefectCode)
    {
        mdDefectCode.setCreateBy(getUsername());
        return toAjax(mdDefectCodeService.insertMdDefectCode(mdDefectCode));
    }

    /**
     * 修改缺陷代码
     */
    @PreAuthorize("@ss.hasPermi('mes:md:defectCode:edit')")
    @Log(title = "缺陷代码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdDefectCode mdDefectCode)
    {
        mdDefectCode.setUpdateBy(getUsername());
        return toAjax(mdDefectCodeService.updateMdDefectCode(mdDefectCode));
    }

    /**
     * 删除缺陷代码
     */
    @PreAuthorize("@ss.hasPermi('mes:md:defectCode:remove')")
    @Log(title = "缺陷代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdDefectCodeService.deleteMdDefectCodeByIds(ids));
    }
}
