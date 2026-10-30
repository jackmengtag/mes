package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesStockTxn;

/**
 * 出入库流水Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesStockTxnMapper
{
    public MesStockTxn selectMesStockTxnById(Long txnId);

    public List<MesStockTxn> selectMesStockTxnList(MesStockTxn mesStockTxn);

    public int insertMesStockTxn(MesStockTxn mesStockTxn);

    public int updateMesStockTxn(MesStockTxn mesStockTxn);

    public int deleteMesStockTxnById(Long txnId);

    public int deleteMesStockTxnByIds(Long[] txnIds);
}
