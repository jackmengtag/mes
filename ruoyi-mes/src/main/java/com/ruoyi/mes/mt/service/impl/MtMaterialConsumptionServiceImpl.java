package com.ruoyi.mes.mt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mt.mapper.MtMaterialConsumptionMapper;
import com.ruoyi.mes.mt.domain.MtMaterialConsumption;
import com.ruoyi.mes.mt.service.IMtMaterialConsumptionService;

/**
 * 物料批次消耗Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MtMaterialConsumptionServiceImpl implements IMtMaterialConsumptionService
{
    @Autowired
    private MtMaterialConsumptionMapper mtMaterialConsumptionMapper;

    /**
     * 查询物料批次消耗
     */
    @Override
    public MtMaterialConsumption selectMtMaterialConsumptionById(Long id)
    {
        return mtMaterialConsumptionMapper.selectMtMaterialConsumptionById(id);
    }

    /**
     * 查询物料批次消耗列表
     */
    @Override
    public List<MtMaterialConsumption> selectMtMaterialConsumptionList(MtMaterialConsumption mtMaterialConsumption)
    {
        return mtMaterialConsumptionMapper.selectMtMaterialConsumptionList(mtMaterialConsumption);
    }

    /**
     * 新增物料批次消耗
     */
    @Override
    public int insertMtMaterialConsumption(MtMaterialConsumption mtMaterialConsumption)
    {
        return mtMaterialConsumptionMapper.insertMtMaterialConsumption(mtMaterialConsumption);
    }

    /**
     * 修改物料批次消耗
     */
    @Override
    public int updateMtMaterialConsumption(MtMaterialConsumption mtMaterialConsumption)
    {
        return mtMaterialConsumptionMapper.updateMtMaterialConsumption(mtMaterialConsumption);
    }

    /**
     * 批量删除物料批次消耗
     */
    @Override
    public int deleteMtMaterialConsumptionByIds(Long[] ids)
    {
        return mtMaterialConsumptionMapper.deleteMtMaterialConsumptionByIds(ids);
    }

    /**
     * 删除物料批次消耗信息
     */
    @Override
    public int deleteMtMaterialConsumptionById(Long id)
    {
        return mtMaterialConsumptionMapper.deleteMtMaterialConsumptionById(id);
    }
}
