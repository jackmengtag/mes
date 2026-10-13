package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdProduct;

/**
 * 产品主数据Mapper接口
 *
 * @author ruoyi
 */
public interface MdProductMapper
{
    /**
     * 查询产品主数据
     */
    public MdProduct selectMdProductById(Long id);

    /**
     * 查询产品主数据列表
     */
    public List<MdProduct> selectMdProductList(MdProduct mdProduct);

    /**
     * 新增产品主数据
     */
    public int insertMdProduct(MdProduct mdProduct);

    /**
     * 修改产品主数据
     */
    public int updateMdProduct(MdProduct mdProduct);

    /**
     * 删除产品主数据
     */
    public int deleteMdProductById(Long id);

    /**
     * 批量删除产品主数据
     */
    public int deleteMdProductByIds(Long[] ids);
}
