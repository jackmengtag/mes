package com.ruoyi.mes.qc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.qc.mapper.QcInspectionMapper;
import com.ruoyi.mes.qc.domain.QcInspection;
import com.ruoyi.mes.qc.service.IQcInspectionService;

/**
 * 质量检验Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class QcInspectionServiceImpl implements IQcInspectionService
{
    @Autowired
    private QcInspectionMapper qcInspectionMapper;

    /**
     * 查询质量检验
     */
    @Override
    public QcInspection selectQcInspectionById(Long id)
    {
        return qcInspectionMapper.selectQcInspectionById(id);
    }

    /**
     * 查询质量检验列表
     */
    @Override
    public List<QcInspection> selectQcInspectionList(QcInspection qcInspection)
    {
        return qcInspectionMapper.selectQcInspectionList(qcInspection);
    }

    /**
     * 新增质量检验
     */
    @Override
    public int insertQcInspection(QcInspection qcInspection)
    {
        return qcInspectionMapper.insertQcInspection(qcInspection);
    }

    /**
     * 修改质量检验
     */
    @Override
    public int updateQcInspection(QcInspection qcInspection)
    {
        return qcInspectionMapper.updateQcInspection(qcInspection);
    }

    /**
     * 批量删除质量检验
     */
    @Override
    public int deleteQcInspectionByIds(Long[] ids)
    {
        return qcInspectionMapper.deleteQcInspectionByIds(ids);
    }

    /**
     * 删除质量检验信息
     */
    @Override
    public int deleteQcInspectionById(Long id)
    {
        return qcInspectionMapper.deleteQcInspectionById(id);
    }
}
