package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdEmployee;

/**
 * 员工Service接口
 *
 * @author ruoyi
 */
public interface IMdEmployeeService
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
     * 批量删除员工
     */
    public int deleteMdEmployeeByIds(Long[] ids);

    /**
     * 删除员工信息
     */
    public int deleteMdEmployeeById(Long id);
}
