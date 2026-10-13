package com.ruoyi.mes.qc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.qc.mapper.QcReworkMapper;
import com.ruoyi.mes.qc.domain.QcRework;
import com.ruoyi.mes.qc.service.IQcReworkService;

/**
 * 返工返修Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class QcReworkServiceImpl implements IQcReworkService
{
    @Autowired
    private QcReworkMapper qcReworkMapper;

    /**
     * 查询返工返修
     */
    @Override
    public QcRework selectQcReworkById(Long id)
    {
        return qcReworkMapper.selectQcReworkById(id);
    }

    /**
     * 查询返工返修列表
     */
    @Override
    public List<QcRework> selectQcReworkList(QcRework qcRework)
    {
        return qcReworkMapper.selectQcReworkList(qcRework);
    }

    /**
     * 新增返工返修
     */
    @Override
    public int insertQcRework(QcRework qcRework)
    {
        return qcReworkMapper.insertQcRework(qcRework);
    }

    /**
     * 修改返工返修
     */
    @Override
    public int updateQcRework(QcRework qcRework)
    {
        return qcReworkMapper.updateQcRework(qcRework);
    }

    /**
     * 批量删除返工返修
     */
    @Override
    public int deleteQcReworkByIds(Long[] ids)
    {
        return qcReworkMapper.deleteQcReworkByIds(ids);
    }

    /**
     * 删除返工返修信息
     */
    @Override
    public int deleteQcReworkById(Long id)
    {
        return qcReworkMapper.deleteQcReworkById(id);
    }
}
