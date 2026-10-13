package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdEmployeeMapper;
import com.ruoyi.mes.md.domain.MdEmployee;
import com.ruoyi.mes.md.service.IMdEmployeeService;

/**
 * 员工Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdEmployeeServiceImpl implements IMdEmployeeService
{
    @Autowired
    private MdEmployeeMapper mdEmployeeMapper;

    /**
     * 查询员工
     */
    @Override
    public MdEmployee selectMdEmployeeById(Long id)
    {
        return mdEmployeeMapper.selectMdEmployeeById(id);
    }

    /**
     * 查询员工列表
     */
    @Override
    public List<MdEmployee> selectMdEmployeeList(MdEmployee mdEmployee)
    {
        return mdEmployeeMapper.selectMdEmployeeList(mdEmployee);
    }

    /**
     * 新增员工
     */
    @Override
    public int insertMdEmployee(MdEmployee mdEmployee)
    {
        return mdEmployeeMapper.insertMdEmployee(mdEmployee);
    }

    /**
     * 修改员工
     */
    @Override
    public int updateMdEmployee(MdEmployee mdEmployee)
    {
        return mdEmployeeMapper.updateMdEmployee(mdEmployee);
    }

    /**
     * 批量删除员工
     */
    @Override
    public int deleteMdEmployeeByIds(Long[] ids)
    {
        return mdEmployeeMapper.deleteMdEmployeeByIds(ids);
    }

    /**
     * 删除员工信息
     */
    @Override
    public int deleteMdEmployeeById(Long id)
    {
        return mdEmployeeMapper.deleteMdEmployeeById(id);
    }
}
