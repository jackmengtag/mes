package com.ruoyi.mes.qc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.qc.mapper.QcNonconformityMapper;
import com.ruoyi.mes.qc.domain.QcNonconformity;
import com.ruoyi.mes.qc.service.IQcNonconformityService;

/**
 * 不合格品记录Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class QcNonconformityServiceImpl implements IQcNonconformityService
{
    @Autowired
    private QcNonconformityMapper qcNonconformityMapper;

    /**
     * 查询不合格品记录
     */
    @Override
    public QcNonconformity selectQcNonconformityById(Long id)
    {
        return qcNonconformityMapper.selectQcNonconformityById(id);
    }

    /**
     * 查询不合格品记录列表
     */
    @Override
    public List<QcNonconformity> selectQcNonconformityList(QcNonconformity qcNonconformity)
    {
        return qcNonconformityMapper.selectQcNonconformityList(qcNonconformity);
    }

    /**
     * 新增不合格品记录
     */
    @Override
    public int insertQcNonconformity(QcNonconformity qcNonconformity)
    {
        return qcNonconformityMapper.insertQcNonconformity(qcNonconformity);
    }

    /**
     * 修改不合格品记录
     */
    @Override
    public int updateQcNonconformity(QcNonconformity qcNonconformity)
    {
        return qcNonconformityMapper.updateQcNonconformity(qcNonconformity);
    }

    /**
     * 批量删除不合格品记录
     */
    @Override
    public int deleteQcNonconformityByIds(Long[] ids)
    {
        return qcNonconformityMapper.deleteQcNonconformityByIds(ids);
    }

    /**
     * 删除不合格品记录信息
     */
    @Override
    public int deleteQcNonconformityById(Long id)
    {
        return qcNonconformityMapper.deleteQcNonconformityById(id);
    }
}
