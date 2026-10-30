package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesSupplierMapper;
import com.ruoyi.mes.domain.MesSupplier;
import com.ruoyi.mes.service.IMesSupplierService;

/**
 * 供应商档案Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesSupplierServiceImpl implements IMesSupplierService
{
    @Autowired
    private MesSupplierMapper mesSupplierMapper;

    @Override
    public MesSupplier selectMesSupplierById(Long supplierId)
    {
        return mesSupplierMapper.selectMesSupplierById(supplierId);
    }

    @Override
    public List<MesSupplier> selectMesSupplierList(MesSupplier mesSupplier)
    {
        return mesSupplierMapper.selectMesSupplierList(mesSupplier);
    }

    @Override
    public int insertMesSupplier(MesSupplier mesSupplier)
    {
        return mesSupplierMapper.insertMesSupplier(mesSupplier);
    }

    @Override
    public int updateMesSupplier(MesSupplier mesSupplier)
    {
        return mesSupplierMapper.updateMesSupplier(mesSupplier);
    }

    @Override
    public int deleteMesSupplierById(Long supplierId)
    {
        return mesSupplierMapper.deleteMesSupplierById(supplierId);
    }

    @Override
    public int deleteMesSupplierByIds(Long[] supplierIds)
    {
        return mesSupplierMapper.deleteMesSupplierByIds(supplierIds);
    }
}
