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
import com.ruoyi.mes.md.domain.MdVersionApproval;
import com.ruoyi.mes.md.service.IMdVersionApprovalService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 主数据版本审批Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/versionApproval")
public class MdVersionApprovalController extends BaseController
{
    @Autowired
    private IMdVersionApprovalService mdVersionApprovalService;

    /**
     * 查询主数据版本审批列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdVersionApproval mdVersionApproval)
    {
        startPage();
        List<MdVersionApproval> list = mdVersionApprovalService.selectMdVersionApprovalList(mdVersionApproval);
        return getDataTable(list);
    }

    /**
     * 导出主数据版本审批列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:export')")
    @Log(title = "主数据版本审批", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdVersionApproval mdVersionApproval)
    {
        List<MdVersionApproval> list = mdVersionApprovalService.selectMdVersionApprovalList(mdVersionApproval);
        ExcelUtil<MdVersionApproval> util = new ExcelUtil<MdVersionApproval>(MdVersionApproval.class);
        util.exportExcel(response, list, "主数据版本审批数据");
    }

    /**
     * 获取主数据版本审批详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdVersionApprovalService.selectMdVersionApprovalById(id));
    }

    /**
     * 新增主数据版本审批
     */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:add')")
    @Log(title = "主数据版本审批", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdVersionApproval mdVersionApproval)
    {
        mdVersionApproval.setCreateBy(getUsername());
        return toAjax(mdVersionApprovalService.insertMdVersionApproval(mdVersionApproval));
    }

    /**
     * 修改主数据版本审批
     */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:edit')")
    @Log(title = "主数据版本审批", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdVersionApproval mdVersionApproval)
    {
        mdVersionApproval.setUpdateBy(getUsername());
        return toAjax(mdVersionApprovalService.updateMdVersionApproval(mdVersionApproval));
    }

    /**
     * 删除主数据版本审批
     */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:remove')")
    @Log(title = "主数据版本审批", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdVersionApprovalService.deleteMdVersionApprovalByIds(ids));
    }
}
