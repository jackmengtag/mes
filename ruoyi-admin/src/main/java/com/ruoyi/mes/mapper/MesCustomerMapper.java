package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesCustomer;

/**
 * 客户档案Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesCustomerMapper
{
    public MesCustomer selectMesCustomerById(Long customerId);

    public List<MesCustomer> selectMesCustomerList(MesCustomer mesCustomer);

    public int insertMesCustomer(MesCustomer mesCustomer);

    public int updateMesCustomer(MesCustomer mesCustomer);

    public int deleteMesCustomerById(Long customerId);

    public int deleteMesCustomerByIds(Long[] customerIds);
}
