package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesStockMapper;
import com.ruoyi.mes.domain.MesStock;
import com.ruoyi.mes.service.IMesStockService;

/**
 * 实时库存Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesStockServiceImpl implements IMesStockService
{
    @Autowired
    private MesStockMapper mesStockMapper;

    @Override
    public MesStock selectMesStockById(Long stockId)
    {
        return mesStockMapper.selectMesStockById(stockId);
    }

    @Override
    public List<MesStock> selectMesStockList(MesStock mesStock)
    {
        return mesStockMapper.selectMesStockList(mesStock);
    }

    @Override
    public int insertMesStock(MesStock mesStock)
    {
        return mesStockMapper.insertMesStock(mesStock);
    }

    @Override
    public int updateMesStock(MesStock mesStock)
    {
        return mesStockMapper.updateMesStock(mesStock);
    }

    @Override
    public int deleteMesStockById(Long stockId)
    {
        return mesStockMapper.deleteMesStockById(stockId);
    }

    @Override
    public int deleteMesStockByIds(Long[] stockIds)
    {
        return mesStockMapper.deleteMesStockByIds(stockIds);
    }
}
