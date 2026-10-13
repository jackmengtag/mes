package com.ruoyi.mes.eq.service;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqOee;

/**
 * OEEService接口
 *
 * @author ruoyi
 */
public interface IEqOeeService
{
    /**
     * 查询OEE
     */
    public EqOee selectEqOeeById(Long id);

    /**
     * 查询OEE列表
     */
    public List<EqOee> selectEqOeeList(EqOee eqOee);

    /**
     * 新增OEE
     */
    public int insertEqOee(EqOee eqOee);

    /**
     * 修改OEE
     */
    public int updateEqOee(EqOee eqOee);

    /**
     * 批量删除OEE
     */
    public int deleteEqOeeByIds(Long[] ids);

    /**
     * 删除OEE信息
     */
    public int deleteEqOeeById(Long id);
}
