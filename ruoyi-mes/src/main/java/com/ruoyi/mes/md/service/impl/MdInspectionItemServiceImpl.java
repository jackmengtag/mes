package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdInspectionItemMapper;
import com.ruoyi.mes.md.domain.MdInspectionItem;
import com.ruoyi.mes.md.service.IMdInspectionItemService;

/**
 * 检验项目Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdInspectionItemServiceImpl implements IMdInspectionItemService
{
    @Autowired
    private MdInspectionItemMapper mdInspectionItemMapper;

    /**
     * 查询检验项目
     */
    @Override
    public MdInspectionItem selectMdInspectionItemById(Long id)
    {
        return mdInspectionItemMapper.selectMdInspectionItemById(id);
    }

    /**
     * 查询检验项目列表
     */
    @Override
    public List<MdInspectionItem> selectMdInspectionItemList(MdInspectionItem mdInspectionItem)
    {
        return mdInspectionItemMapper.selectMdInspectionItemList(mdInspectionItem);
    }

    /**
     * 新增检验项目
     */
    @Override
    public int insertMdInspectionItem(MdInspectionItem mdInspectionItem)
    {
        return mdInspectionItemMapper.insertMdInspectionItem(mdInspectionItem);
    }

    /**
     * 修改检验项目
     */
    @Override
    public int updateMdInspectionItem(MdInspectionItem mdInspectionItem)
    {
        return mdInspectionItemMapper.updateMdInspectionItem(mdInspectionItem);
    }

    /**
     * 批量删除检验项目
     */
    @Override
    public int deleteMdInspectionItemByIds(Long[] ids)
    {
        return mdInspectionItemMapper.deleteMdInspectionItemByIds(ids);
    }

    /**
     * 删除检验项目信息
     */
    @Override
    public int deleteMdInspectionItemById(Long id)
    {
        return mdInspectionItemMapper.deleteMdInspectionItemById(id);
    }
}
