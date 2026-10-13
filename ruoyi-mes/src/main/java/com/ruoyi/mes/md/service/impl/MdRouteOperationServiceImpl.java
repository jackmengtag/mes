package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdRouteOperationMapper;
import com.ruoyi.mes.md.domain.MdRouteOperation;
import com.ruoyi.mes.md.service.IMdRouteOperationService;

/**
 * 工艺工序Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdRouteOperationServiceImpl implements IMdRouteOperationService
{
    @Autowired
    private MdRouteOperationMapper mdRouteOperationMapper;

    /**
     * 查询工艺工序
     */
    @Override
    public MdRouteOperation selectMdRouteOperationById(Long id)
    {
        return mdRouteOperationMapper.selectMdRouteOperationById(id);
    }

    /**
     * 查询工艺工序列表
     */
    @Override
    public List<MdRouteOperation> selectMdRouteOperationList(MdRouteOperation mdRouteOperation)
    {
        return mdRouteOperationMapper.selectMdRouteOperationList(mdRouteOperation);
    }

    /**
     * 新增工艺工序
     */
    @Override
    public int insertMdRouteOperation(MdRouteOperation mdRouteOperation)
    {
        return mdRouteOperationMapper.insertMdRouteOperation(mdRouteOperation);
    }

    /**
     * 修改工艺工序
     */
    @Override
    public int updateMdRouteOperation(MdRouteOperation mdRouteOperation)
    {
        return mdRouteOperationMapper.updateMdRouteOperation(mdRouteOperation);
    }

    /**
     * 批量删除工艺工序
     */
    @Override
    public int deleteMdRouteOperationByIds(Long[] ids)
    {
        return mdRouteOperationMapper.deleteMdRouteOperationByIds(ids);
    }

    /**
     * 删除工艺工序信息
     */
    @Override
    public int deleteMdRouteOperationById(Long id)
    {
        return mdRouteOperationMapper.deleteMdRouteOperationById(id);
    }
}
