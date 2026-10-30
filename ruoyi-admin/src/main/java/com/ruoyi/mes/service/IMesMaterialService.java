package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesMaterial;

/**
 * 物料主数据Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesMaterialService
{
    public MesMaterial selectMesMaterialById(Long materialId);

    public List<MesMaterial> selectMesMaterialList(MesMaterial mesMaterial);

    public int insertMesMaterial(MesMaterial mesMaterial);

    public int updateMesMaterial(MesMaterial mesMaterial);

    public int deleteMesMaterialById(Long materialId);

    public int deleteMesMaterialByIds(Long[] materialIds);
}
