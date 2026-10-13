package com.ruoyi.mes.qc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.qc.mapper.QcInspectionValueMapper;
import com.ruoyi.mes.qc.domain.QcInspectionValue;
import com.ruoyi.mes.qc.service.IQcInspectionValueService;

/**
 * 检验项目值Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class QcInspectionValueServiceImpl implements IQcInspectionValueService
{
    @Autowired
    private QcInspectionValueMapper qcInspectionValueMapper;

    /**
     * 查询检验项目值
     */
    @Override
    public QcInspectionValue selectQcInspectionValueById(Long id)
    {
        return qcInspectionValueMapper.selectQcInspectionValueById(id);
    }

    /**
     * 查询检验项目值列表
     */
    @Override
    public List<QcInspectionValue> selectQcInspectionValueList(QcInspectionValue qcInspectionValue)
    {
        return qcInspectionValueMapper.selectQcInspectionValueList(qcInspectionValue);
    }

    /**
     * 新增检验项目值
     */
    @Override
    public int insertQcInspectionValue(QcInspectionValue qcInspectionValue)
    {
        return qcInspectionValueMapper.insertQcInspectionValue(qcInspectionValue);
    }

    /**
     * 修改检验项目值
     */
    @Override
    public int updateQcInspectionValue(QcInspectionValue qcInspectionValue)
    {
        return qcInspectionValueMapper.updateQcInspectionValue(qcInspectionValue);
    }

    /**
     * 批量删除检验项目值
     */
    @Override
    public int deleteQcInspectionValueByIds(Long[] ids)
    {
        return qcInspectionValueMapper.deleteQcInspectionValueByIds(ids);
    }

    /**
     * 删除检验项目值信息
     */
    @Override
    public int deleteQcInspectionValueById(Long id)
    {
        return qcInspectionValueMapper.deleteQcInspectionValueById(id);
    }
}
