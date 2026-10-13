package com.ruoyi.mes.eq.mapper;

import java.util.List;
import com.ruoyi.mes.eq.domain.EqOee;

/**
 * OEEMapper接口
 *
 * @author ruoyi
 */
public interface EqOeeMapper
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
     * 删除OEE
     */
    public int deleteEqOeeById(Long id);

    /**
     * 批量删除OEE
     */
    public int deleteEqOeeByIds(Long[] ids);
}
