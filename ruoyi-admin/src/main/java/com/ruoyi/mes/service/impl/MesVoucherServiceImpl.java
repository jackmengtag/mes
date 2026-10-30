package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesVoucherMapper;
import com.ruoyi.mes.domain.MesVoucher;
import com.ruoyi.mes.service.IMesVoucherService;

/**
 * 存货核算凭证Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesVoucherServiceImpl implements IMesVoucherService
{
    @Autowired
    private MesVoucherMapper mesVoucherMapper;

    @Override
    public MesVoucher selectMesVoucherById(Long voucherId)
    {
        return mesVoucherMapper.selectMesVoucherById(voucherId);
    }

    @Override
    public List<MesVoucher> selectMesVoucherList(MesVoucher mesVoucher)
    {
        return mesVoucherMapper.selectMesVoucherList(mesVoucher);
    }

    @Override
    public int insertMesVoucher(MesVoucher mesVoucher)
    {
        return mesVoucherMapper.insertMesVoucher(mesVoucher);
    }

    @Override
    public int updateMesVoucher(MesVoucher mesVoucher)
    {
        return mesVoucherMapper.updateMesVoucher(mesVoucher);
    }

    @Override
    public int deleteMesVoucherById(Long voucherId)
    {
        return mesVoucherMapper.deleteMesVoucherById(voucherId);
    }

    @Override
    public int deleteMesVoucherByIds(Long[] voucherIds)
    {
        return mesVoucherMapper.deleteMesVoucherByIds(voucherIds);
    }
}
