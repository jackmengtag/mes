package com.ruoyi.mes.qc.mapper;

import java.util.List;
import com.ruoyi.mes.qc.domain.QcInspectionValue;

/**
 * 检验项目值Mapper接口
 *
 * @author ruoyi
 */
public interface QcInspectionValueMapper
{
    /**
     * 查询检验项目值
     */
    public QcInspectionValue selectQcInspectionValueById(Long id);

    /**
     * 查询检验项目值列表
     */
    public List<QcInspectionValue> selectQcInspectionValueList(QcInspectionValue qcInspectionValue);

    /**
     * 新增检验项目值
     */
    public int insertQcInspectionValue(QcInspectionValue qcInspectionValue);

    /**
     * 修改检验项目值
     */
    public int updateQcInspectionValue(QcInspectionValue qcInspectionValue);

    /**
     * 删除检验项目值
     */
    public int deleteQcInspectionValueById(Long id);

    /**
     * 批量删除检验项目值
     */
    public int deleteQcInspectionValueByIds(Long[] ids);
}
