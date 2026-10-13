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
import com.ruoyi.mes.integration.domain.IntIdempotentRecord;
import com.ruoyi.mes.integration.service.IIntIdempotentRecordService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 幂等记录Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/mes/integration/idempotent")
public class IntIdempotentRecordController extends BaseController
{
    @Autowired
    private IIntIdempotentRecordService intIdempotentRecordService;

    /**
     * 查询幂等记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:idempotent:list')")
    @GetMapping("/list")
    public TableDataInfo list(IntIdempotentRecord intIdempotentRecord)
    {
        startPage();
        List<IntIdempotentRecord> list = intIdempotentRecordService.selectIntIdempotentRecordList(intIdempotentRecord);
        return getDataTable(list);
    }

    /**
     * 导出幂等记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:idempotent:export')")
    @Log(title = "幂等记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntIdempotentRecord intIdempotentRecord)
    {
        List<IntIdempotentRecord> list = intIdempotentRecordService.selectIntIdempotentRecordList(intIdempotentRecord);
        ExcelUtil<IntIdempotentRecord> util = new ExcelUtil<IntIdempotentRecord>(IntIdempotentRecord.class);
        util.exportExcel(response, list, "幂等记录数据");
    }

    /**
     * 获取幂等记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:idempotent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(intIdempotentRecordService.selectIntIdempotentRecordById(id));
    }

    /**
     * 新增幂等记录
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:idempotent:add')")
    @Log(title = "幂等记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntIdempotentRecord intIdempotentRecord)
    {
        intIdempotentRecord.setCreateBy(getUsername());
        return toAjax(intIdempotentRecordService.insertIntIdempotentRecord(intIdempotentRecord));
    }

    /**
     * 修改幂等记录
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:idempotent:edit')")
    @Log(title = "幂等记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntIdempotentRecord intIdempotentRecord)
    {
        intIdempotentRecord.setUpdateBy(getUsername());
        return toAjax(intIdempotentRecordService.updateIntIdempotentRecord(intIdempotentRecord));
    }

    /**
     * 删除幂等记录
     */
    @PreAuthorize("@ss.hasPermi('mes:integration:idempotent:remove')")
    @Log(title = "幂等记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(intIdempotentRecordService.deleteIntIdempotentRecordByIds(ids));
    }
}
