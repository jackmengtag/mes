package com.ruoyi.mes.qc.service;

import java.util.List;
import com.ruoyi.mes.qc.domain.QcInspection;

/**
 * 质量检验Service接口
 *
 * @author ruoyi
 */
public interface IQcInspectionService
{
    /**
     * 查询质量检验
     */
    public QcInspection selectQcInspectionById(Long id);

    /**
     * 查询质量检验列表
     */
    public List<QcInspection> selectQcInspectionList(QcInspection qcInspection);

    /**
     * 新增质量检验
     */
    public int insertQcInspection(QcInspection qcInspection);

    /**
     * 修改质量检验
     */
    public int updateQcInspection(QcInspection qcInspection);

    /**
     * 批量删除质量检验
     */
    public int deleteQcInspectionByIds(Long[] ids);

    /**
     * 删除质量检验信息
     */
    public int deleteQcInspectionById(Long id);
}
