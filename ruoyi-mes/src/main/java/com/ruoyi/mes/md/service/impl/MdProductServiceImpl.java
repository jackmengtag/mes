package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdProductMapper;
import com.ruoyi.mes.md.domain.MdProduct;
import com.ruoyi.mes.md.service.IMdProductService;

/**
 * 产品主数据Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdProductServiceImpl implements IMdProductService
{
    @Autowired
    private MdProductMapper mdProductMapper;

    /**
     * 查询产品主数据
     */
    @Override
    public MdProduct selectMdProductById(Long id)
    {
        return mdProductMapper.selectMdProductById(id);
    }

    /**
     * 查询产品主数据列表
     */
    @Override
    public List<MdProduct> selectMdProductList(MdProduct mdProduct)
    {
        return mdProductMapper.selectMdProductList(mdProduct);
    }

    /**
     * 新增产品主数据
     */
    @Override
    public int insertMdProduct(MdProduct mdProduct)
    {
        return mdProductMapper.insertMdProduct(mdProduct);
    }

    /**
     * 修改产品主数据
     */
    @Override
    public int updateMdProduct(MdProduct mdProduct)
    {
        return mdProductMapper.updateMdProduct(mdProduct);
    }

    /**
     * 批量删除产品主数据
     */
    @Override
    public int deleteMdProductByIds(Long[] ids)
    {
        return mdProductMapper.deleteMdProductByIds(ids);
    }

    /**
     * 删除产品主数据信息
     */
    @Override
    public int deleteMdProductById(Long id)
    {
        return mdProductMapper.deleteMdProductById(id);
    }
}
