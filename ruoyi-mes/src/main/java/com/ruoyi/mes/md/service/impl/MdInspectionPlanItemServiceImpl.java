package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdInspectionPlanItemMapper;
import com.ruoyi.mes.md.domain.MdInspectionPlanItem;
import com.ruoyi.mes.md.service.IMdInspectionPlanItemService;

/**
 * 检验方案明细Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdInspectionPlanItemServiceImpl implements IMdInspectionPlanItemService
{
    @Autowired
    private MdInspectionPlanItemMapper mdInspectionPlanItemMapper;

    /**
     * 查询检验方案明细
     */
    @Override
    public MdInspectionPlanItem selectMdInspectionPlanItemById(Long id)
    {
        return mdInspectionPlanItemMapper.selectMdInspectionPlanItemById(id);
    }

    /**
     * 查询检验方案明细列表
     */
    @Override
    public List<MdInspectionPlanItem> selectMdInspectionPlanItemList(MdInspectionPlanItem mdInspectionPlanItem)
    {
        return mdInspectionPlanItemMapper.selectMdInspectionPlanItemList(mdInspectionPlanItem);
    }

    /**
     * 新增检验方案明细
     */
    @Override
    public int insertMdInspectionPlanItem(MdInspectionPlanItem mdInspectionPlanItem)
    {
        return mdInspectionPlanItemMapper.insertMdInspectionPlanItem(mdInspectionPlanItem);
    }

    /**
     * 修改检验方案明细
     */
    @Override
    public int updateMdInspectionPlanItem(MdInspectionPlanItem mdInspectionPlanItem)
    {
        return mdInspectionPlanItemMapper.updateMdInspectionPlanItem(mdInspectionPlanItem);
    }

    /**
     * 批量删除检验方案明细
     */
    @Override
    public int deleteMdInspectionPlanItemByIds(Long[] ids)
    {
        return mdInspectionPlanItemMapper.deleteMdInspectionPlanItemByIds(ids);
    }

    /**
     * 删除检验方案明细信息
     */
    @Override
    public int deleteMdInspectionPlanItemById(Long id)
    {
        return mdInspectionPlanItemMapper.deleteMdInspectionPlanItemById(id);
    }
}
