package com.ruoyi.mes.ex.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.ex.mapper.ExProductSerialMapper;
import com.ruoyi.mes.ex.domain.ExProductSerial;
import com.ruoyi.mes.ex.service.IExProductSerialService;

/**
 * 产品序列号Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class ExProductSerialServiceImpl implements IExProductSerialService
{
    @Autowired
    private ExProductSerialMapper exProductSerialMapper;

    /**
     * 查询产品序列号
     */
    @Override
    public ExProductSerial selectExProductSerialById(Long id)
    {
        return exProductSerialMapper.selectExProductSerialById(id);
    }

    /**
     * 查询产品序列号列表
     */
    @Override
    public List<ExProductSerial> selectExProductSerialList(ExProductSerial exProductSerial)
    {
        return exProductSerialMapper.selectExProductSerialList(exProductSerial);
    }

    /**
     * 新增产品序列号
     */
    @Override
    public int insertExProductSerial(ExProductSerial exProductSerial)
    {
        return exProductSerialMapper.insertExProductSerial(exProductSerial);
    }

    /**
     * 修改产品序列号
     */
    @Override
    public int updateExProductSerial(ExProductSerial exProductSerial)
    {
        return exProductSerialMapper.updateExProductSerial(exProductSerial);
    }

    /**
     * 批量删除产品序列号
     */
    @Override
    public int deleteExProductSerialByIds(Long[] ids)
    {
        return exProductSerialMapper.deleteExProductSerialByIds(ids);
    }

    /**
     * 删除产品序列号信息
     */
    @Override
    public int deleteExProductSerialById(Long id)
    {
        return exProductSerialMapper.deleteExProductSerialById(id);
    }
}
