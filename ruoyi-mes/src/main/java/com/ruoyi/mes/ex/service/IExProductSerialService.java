package com.ruoyi.mes.ex.service;

import java.util.List;
import com.ruoyi.mes.ex.domain.ExProductSerial;

/**
 * 产品序列号Service接口
 *
 * @author ruoyi
 */
public interface IExProductSerialService
{
    /**
     * 查询产品序列号
     */
    public ExProductSerial selectExProductSerialById(Long id);

    /**
     * 查询产品序列号列表
     */
    public List<ExProductSerial> selectExProductSerialList(ExProductSerial exProductSerial);

    /**
     * 新增产品序列号
     */
    public int insertExProductSerial(ExProductSerial exProductSerial);

    /**
     * 修改产品序列号
     */
    public int updateExProductSerial(ExProductSerial exProductSerial);

    /**
     * 批量删除产品序列号
     */
    public int deleteExProductSerialByIds(Long[] ids);

    /**
     * 删除产品序列号信息
     */
    public int deleteExProductSerialById(Long id);
}
