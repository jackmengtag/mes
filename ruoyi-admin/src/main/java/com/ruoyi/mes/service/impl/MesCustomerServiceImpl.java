package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesCustomerMapper;
import com.ruoyi.mes.domain.MesCustomer;
import com.ruoyi.mes.service.IMesCustomerService;

/**
 * 客户档案Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesCustomerServiceImpl implements IMesCustomerService
{
    @Autowired
    private MesCustomerMapper mesCustomerMapper;

    @Override
    public MesCustomer selectMesCustomerById(Long customerId)
    {
        return mesCustomerMapper.selectMesCustomerById(customerId);
    }

    @Override
    public List<MesCustomer> selectMesCustomerList(MesCustomer mesCustomer)
    {
        return mesCustomerMapper.selectMesCustomerList(mesCustomer);
    }

    @Override
    public int insertMesCustomer(MesCustomer mesCustomer)
    {
        return mesCustomerMapper.insertMesCustomer(mesCustomer);
    }

    @Override
    public int updateMesCustomer(MesCustomer mesCustomer)
    {
        return mesCustomerMapper.updateMesCustomer(mesCustomer);
    }

    @Override
    public int deleteMesCustomerById(Long customerId)
    {
        return mesCustomerMapper.deleteMesCustomerById(customerId);
    }

    @Override
    public int deleteMesCustomerByIds(Long[] customerIds)
    {
        return mesCustomerMapper.deleteMesCustomerByIds(customerIds);
    }
}
