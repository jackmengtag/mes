package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesVoucher;

/**
 * 存货核算凭证Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesVoucherService
{
    public MesVoucher selectMesVoucherById(Long voucherId);

    public List<MesVoucher> selectMesVoucherList(MesVoucher mesVoucher);

    public int insertMesVoucher(MesVoucher mesVoucher);

    public int updateMesVoucher(MesVoucher mesVoucher);

    public int deleteMesVoucherById(Long voucherId);

    public int deleteMesVoucherByIds(Long[] voucherIds);
}
