package com.ruoyi.mes.qc.service;

import java.util.List;
import com.ruoyi.mes.qc.domain.QcRelease;

/**
 * 质量放行Service接口
 *
 * @author ruoyi
 */
public interface IQcReleaseService
{
    /**
     * 查询质量放行
     */
    public QcRelease selectQcReleaseById(Long id);

    /**
     * 查询质量放行列表
     */
    public List<QcRelease> selectQcReleaseList(QcRelease qcRelease);

    /**
     * 新增质量放行
     */
    public int insertQcRelease(QcRelease qcRelease);

    /**
     * 修改质量放行
     */
    public int updateQcRelease(QcRelease qcRelease);

    /**
     * 批量删除质量放行
     */
    public int deleteQcReleaseByIds(Long[] ids);

    /**
     * 删除质量放行信息
     */
    public int deleteQcReleaseById(Long id);
}
