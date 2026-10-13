package com.ruoyi.mes.mt.service;

import java.util.List;
import com.ruoyi.mes.mt.domain.MtMaterialConsumption;

/**
 * 物料批次消耗Service接口
 *
 * @author ruoyi
 */
public interface IMtMaterialConsumptionService
{
    /**
     * 查询物料批次消耗
     */
    public MtMaterialConsumption selectMtMaterialConsumptionById(Long id);

    /**
     * 查询物料批次消耗列表
     */
    public List<MtMaterialConsumption> selectMtMaterialConsumptionList(MtMaterialConsumption mtMaterialConsumption);

    /**
     * 新增物料批次消耗
     */
    public int insertMtMaterialConsumption(MtMaterialConsumption mtMaterialConsumption);

    /**
     * 修改物料批次消耗
     */
    public int updateMtMaterialConsumption(MtMaterialConsumption mtMaterialConsumption);

    /**
     * 批量删除物料批次消耗
     */
    public int deleteMtMaterialConsumptionByIds(Long[] ids);

    /**
     * 删除物料批次消耗信息
     */
    public int deleteMtMaterialConsumptionById(Long id);
}
