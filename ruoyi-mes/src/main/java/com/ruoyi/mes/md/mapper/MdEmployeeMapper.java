package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdEmployee;

/**
 * 员工Mapper接口
 *
 * @author ruoyi
 */
public interface MdEmployeeMapper
{
    /**
     * 查询员工
     */
    public MdEmployee selectMdEmployeeById(Long id);

    /**
     * 查询员工列表
     */
    public List<MdEmployee> selectMdEmployeeList(MdEmployee mdEmployee);

    /**
     * 新增员工
     */
    public int insertMdEmployee(MdEmployee mdEmployee);

    /**
     * 修改员工
     */
    public int updateMdEmployee(MdEmployee mdEmployee);

    /**
     * 删除员工
     */
    public int deleteMdEmployeeById(Long id);

    /**
     * 批量删除员工
     */
    public int deleteMdEmployeeByIds(Long[] ids);
}
