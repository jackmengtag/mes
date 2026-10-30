package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesStockTxnMapper;
import com.ruoyi.mes.domain.MesStockTxn;
import com.ruoyi.mes.service.IMesStockTxnService;

/**
 * 出入库流水Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesStockTxnServiceImpl implements IMesStockTxnService
{
    @Autowired
    private MesStockTxnMapper mesStockTxnMapper;

    @Override
    public MesStockTxn selectMesStockTxnById(Long txnId)
    {
        return mesStockTxnMapper.selectMesStockTxnById(txnId);
    }

    @Override
    public List<MesStockTxn> selectMesStockTxnList(MesStockTxn mesStockTxn)
    {
        return mesStockTxnMapper.selectMesStockTxnList(mesStockTxn);
    }

    @Override
    public int insertMesStockTxn(MesStockTxn mesStockTxn)
    {
        return mesStockTxnMapper.insertMesStockTxn(mesStockTxn);
    }

    @Override
    public int updateMesStockTxn(MesStockTxn mesStockTxn)
    {
        return mesStockTxnMapper.updateMesStockTxn(mesStockTxn);
    }

    @Override
    public int deleteMesStockTxnById(Long txnId)
    {
        return mesStockTxnMapper.deleteMesStockTxnById(txnId);
    }

    @Override
    public int deleteMesStockTxnByIds(Long[] txnIds)
    {
        return mesStockTxnMapper.deleteMesStockTxnByIds(txnIds);
    }
}
