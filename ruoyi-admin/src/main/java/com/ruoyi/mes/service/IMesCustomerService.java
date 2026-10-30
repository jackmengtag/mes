package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesCustomer;

/**
 * 客户档案Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesCustomerService
{
    public MesCustomer selectMesCustomerById(Long customerId);

    public List<MesCustomer> selectMesCustomerList(MesCustomer mesCustomer);

    public int insertMesCustomer(MesCustomer mesCustomer);

    public int updateMesCustomer(MesCustomer mesCustomer);

    public int deleteMesCustomerById(Long customerId);

    public int deleteMesCustomerByIds(Long[] customerIds);
}
