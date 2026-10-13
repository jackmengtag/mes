package com.ruoyi.mes.md.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mes.md.service.MdVersionService;

/**
 * 主数据版本与审批控制器（FR-MD-010）
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/md/version")
public class MdVersionController extends BaseController
{
    @Autowired
    private MdVersionService versionService;

    /** 提交审批 */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:add')")
    @Log(title = "提交主数据审批", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody Map<String, Object> body)
    {
        return success(versionService.submitApproval(str(body, "bizType"), lng(body, "bizId"),
                str(body, "changeReason"), getUsername()));
    }

    /** 发布版本 */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:edit')")
    @Log(title = "发布主数据版本", businessType = BusinessType.UPDATE)
    @PostMapping("/publish")
    public AjaxResult publish(@RequestBody Map<String, Object> body)
    {
        return success(versionService.publish(str(body, "bizType"), lng(body, "bizId"),
                str(body, "changeReason"), getUsername()));
    }

    /** 失效版本 */
    @PreAuthorize("@ss.hasPermi('mes:md:versionApproval:edit')")
    @Log(title = "失效主数据版本", businessType = BusinessType.UPDATE)
    @PostMapping("/invalidate")
    public AjaxResult invalidate(@RequestBody Map<String, Object> body)
    {
        return success(versionService.invalidate(str(body, "bizType"), lng(body, "bizId"),
                str(body, "changeReason"), getUsername()));
    }

    private static String str(Map<String, Object> body, String key)
    {
        return body.get(key) == null ? null : String.valueOf(body.get(key));
    }

    private static Long lng(Map<String, Object> body, String key)
    {
        return body.get(key) == null ? null : Long.valueOf(String.valueOf(body.get(key)));
    }
}
