package com.ruoyi.mes.qc.mapper;

import java.util.List;
import com.ruoyi.mes.qc.domain.QcNonconformity;

/**
 * 不合格品记录Mapper接口
 *
 * @author ruoyi
 */
public interface QcNonconformityMapper
{
    /**
     * 查询不合格品记录
     */
    public QcNonconformity selectQcNonconformityById(Long id);

    /**
     * 查询不合格品记录列表
     */
    public List<QcNonconformity> selectQcNonconformityList(QcNonconformity qcNonconformity);

    /**
     * 新增不合格品记录
     */
    public int insertQcNonconformity(QcNonconformity qcNonconformity);

    /**
     * 修改不合格品记录
     */
    public int updateQcNonconformity(QcNonconformity qcNonconformity);

    /**
     * 删除不合格品记录
     */
    public int deleteQcNonconformityById(Long id);

    /**
     * 批量删除不合格品记录
     */
    public int deleteQcNonconformityByIds(Long[] ids);
}
