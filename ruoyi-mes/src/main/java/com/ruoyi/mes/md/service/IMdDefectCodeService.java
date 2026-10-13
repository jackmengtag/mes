package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdDefectCode;

/**
 * 缺陷代码Service接口
 *
 * @author ruoyi
 */
public interface IMdDefectCodeService
{
    /**
     * 查询缺陷代码
     */
    public MdDefectCode selectMdDefectCodeById(Long id);

    /**
     * 查询缺陷代码列表
     */
    public List<MdDefectCode> selectMdDefectCodeList(MdDefectCode mdDefectCode);

    /**
     * 新增缺陷代码
     */
    public int insertMdDefectCode(MdDefectCode mdDefectCode);

    /**
     * 修改缺陷代码
     */
    public int updateMdDefectCode(MdDefectCode mdDefectCode);

    /**
     * 批量删除缺陷代码
     */
    public int deleteMdDefectCodeByIds(Long[] ids);

    /**
     * 删除缺陷代码信息
     */
    public int deleteMdDefectCodeById(Long id);
}
