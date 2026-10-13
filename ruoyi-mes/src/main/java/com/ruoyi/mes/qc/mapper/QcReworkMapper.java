package com.ruoyi.mes.qc.mapper;

import java.util.List;
import com.ruoyi.mes.qc.domain.QcRework;

/**
 * 返工返修Mapper接口
 *
 * @author ruoyi
 */
public interface QcReworkMapper
{
    /**
     * 查询返工返修
     */
    public QcRework selectQcReworkById(Long id);

    /**
     * 查询返工返修列表
     */
    public List<QcRework> selectQcReworkList(QcRework qcRework);

    /**
     * 新增返工返修
     */
    public int insertQcRework(QcRework qcRework);

    /**
     * 修改返工返修
     */
    public int updateQcRework(QcRework qcRework);

    /**
     * 删除返工返修
     */
    public int deleteQcReworkById(Long id);

    /**
     * 批量删除返工返修
     */
    public int deleteQcReworkByIds(Long[] ids);
}
