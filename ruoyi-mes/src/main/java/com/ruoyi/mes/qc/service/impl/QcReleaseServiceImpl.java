package com.ruoyi.mes.qc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.qc.mapper.QcReleaseMapper;
import com.ruoyi.mes.qc.domain.QcRelease;
import com.ruoyi.mes.qc.service.IQcReleaseService;

/**
 * 质量放行Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class QcReleaseServiceImpl implements IQcReleaseService
{
    @Autowired
    private QcReleaseMapper qcReleaseMapper;

    /**
     * 查询质量放行
     */
    @Override
    public QcRelease selectQcReleaseById(Long id)
    {
        return qcReleaseMapper.selectQcReleaseById(id);
    }

    /**
     * 查询质量放行列表
     */
    @Override
    public List<QcRelease> selectQcReleaseList(QcRelease qcRelease)
    {
        return qcReleaseMapper.selectQcReleaseList(qcRelease);
    }

    /**
     * 新增质量放行
     */
    @Override
    public int insertQcRelease(QcRelease qcRelease)
    {
        return qcReleaseMapper.insertQcRelease(qcRelease);
    }

    /**
     * 修改质量放行
     */
    @Override
    public int updateQcRelease(QcRelease qcRelease)
    {
        return qcReleaseMapper.updateQcRelease(qcRelease);
    }

    /**
     * 批量删除质量放行
     */
    @Override
    public int deleteQcReleaseByIds(Long[] ids)
    {
        return qcReleaseMapper.deleteQcReleaseByIds(ids);
    }

    /**
     * 删除质量放行信息
     */
    @Override
    public int deleteQcReleaseById(Long id)
    {
        return qcReleaseMapper.deleteQcReleaseById(id);
    }
}
