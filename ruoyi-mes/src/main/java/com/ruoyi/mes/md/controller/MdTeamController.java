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
import com.ruoyi.mes.md.domain.MdTeam;
import com.ruoyi.mes.md.service.IMdTeamService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 班组Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/team")
public class MdTeamController extends BaseController
{
    @Autowired
    private IMdTeamService mdTeamService;

    /**
     * 查询班组列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:team:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdTeam mdTeam)
    {
        startPage();
        List<MdTeam> list = mdTeamService.selectMdTeamList(mdTeam);
        return getDataTable(list);
    }

    /**
     * 导出班组列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:team:export')")
    @Log(title = "班组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdTeam mdTeam)
    {
        List<MdTeam> list = mdTeamService.selectMdTeamList(mdTeam);
        ExcelUtil<MdTeam> util = new ExcelUtil<MdTeam>(MdTeam.class);
        util.exportExcel(response, list, "班组数据");
    }

    /**
     * 获取班组详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:team:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mdTeamService.selectMdTeamById(id));
    }

    /**
     * 新增班组
     */
    @PreAuthorize("@ss.hasPermi('mes:md:team:add')")
    @Log(title = "班组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdTeam mdTeam)
    {
        mdTeam.setCreateBy(getUsername());
        return toAjax(mdTeamService.insertMdTeam(mdTeam));
    }

    /**
     * 修改班组
     */
    @PreAuthorize("@ss.hasPermi('mes:md:team:edit')")
    @Log(title = "班组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdTeam mdTeam)
    {
        mdTeam.setUpdateBy(getUsername());
        return toAjax(mdTeamService.updateMdTeam(mdTeam));
    }

    /**
     * 删除班组
     */
    @PreAuthorize("@ss.hasPermi('mes:md:team:remove')")
    @Log(title = "班组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mdTeamService.deleteMdTeamByIds(ids));
    }
}
