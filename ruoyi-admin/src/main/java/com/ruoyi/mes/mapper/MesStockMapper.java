package com.ruoyi.mes.mapper;

import java.util.List;
import com.ruoyi.mes.domain.MesStock;

/**
 * 实时库存Mapper接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface MesStockMapper
{
    public MesStock selectMesStockById(Long stockId);

    public List<MesStock> selectMesStockList(MesStock mesStock);

    public int insertMesStock(MesStock mesStock);

    public int updateMesStock(MesStock mesStock);

    public int deleteMesStockById(Long stockId);

    public int deleteMesStockByIds(Long[] stockIds);
}
