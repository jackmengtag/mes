package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdDefectCodeMapper;
import com.ruoyi.mes.md.domain.MdDefectCode;
import com.ruoyi.mes.md.service.IMdDefectCodeService;

/**
 * 缺陷代码Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdDefectCodeServiceImpl implements IMdDefectCodeService
{
    @Autowired
    private MdDefectCodeMapper mdDefectCodeMapper;

    /**
     * 查询缺陷代码
     */
    @Override
    public MdDefectCode selectMdDefectCodeById(Long id)
    {
        return mdDefectCodeMapper.selectMdDefectCodeById(id);
    }

    /**
     * 查询缺陷代码列表
     */
    @Override
    public List<MdDefectCode> selectMdDefectCodeList(MdDefectCode mdDefectCode)
    {
        return mdDefectCodeMapper.selectMdDefectCodeList(mdDefectCode);
    }

    /**
     * 新增缺陷代码
     */
    @Override
    public int insertMdDefectCode(MdDefectCode mdDefectCode)
    {
        return mdDefectCodeMapper.insertMdDefectCode(mdDefectCode);
    }

    /**
     * 修改缺陷代码
     */
    @Override
    public int updateMdDefectCode(MdDefectCode mdDefectCode)
    {
        return mdDefectCodeMapper.updateMdDefectCode(mdDefectCode);
    }

    /**
     * 批量删除缺陷代码
     */
    @Override
    public int deleteMdDefectCodeByIds(Long[] ids)
    {
        return mdDefectCodeMapper.deleteMdDefectCodeByIds(ids);
    }

    /**
     * 删除缺陷代码信息
     */
    @Override
    public int deleteMdDefectCodeById(Long id)
    {
        return mdDefectCodeMapper.deleteMdDefectCodeById(id);
    }
}
