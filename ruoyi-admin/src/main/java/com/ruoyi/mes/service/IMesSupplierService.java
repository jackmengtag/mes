package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesSupplier;

/**
 * 供应商档案Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesSupplierService
{
    public MesSupplier selectMesSupplierById(Long supplierId);

    public List<MesSupplier> selectMesSupplierList(MesSupplier mesSupplier);

    public int insertMesSupplier(MesSupplier mesSupplier);

    public int updateMesSupplier(MesSupplier mesSupplier);

    public int deleteMesSupplierById(Long supplierId);

    public int deleteMesSupplierByIds(Long[] supplierIds);
}
