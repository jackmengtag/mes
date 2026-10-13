package com.ruoyi.mes.md.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.md.mapper.MdTeamMapper;
import com.ruoyi.mes.md.domain.MdTeam;
import com.ruoyi.mes.md.service.IMdTeamService;

/**
 * 班组Service业务层处理
 *
 * @author ruoyi
 */
@Service
public class MdTeamServiceImpl implements IMdTeamService
{
    @Autowired
    private MdTeamMapper mdTeamMapper;

    /**
     * 查询班组
     */
    @Override
    public MdTeam selectMdTeamById(Long id)
    {
        return mdTeamMapper.selectMdTeamById(id);
    }

    /**
     * 查询班组列表
     */
    @Override
    public List<MdTeam> selectMdTeamList(MdTeam mdTeam)
    {
        return mdTeamMapper.selectMdTeamList(mdTeam);
    }

    /**
     * 新增班组
     */
    @Override
    public int insertMdTeam(MdTeam mdTeam)
    {
        return mdTeamMapper.insertMdTeam(mdTeam);
    }

    /**
     * 修改班组
     */
    @Override
    public int updateMdTeam(MdTeam mdTeam)
    {
        return mdTeamMapper.updateMdTeam(mdTeam);
    }

    /**
     * 批量删除班组
     */
    @Override
    public int deleteMdTeamByIds(Long[] ids)
    {
        return mdTeamMapper.deleteMdTeamByIds(ids);
    }

    /**
     * 删除班组信息
     */
    @Override
    public int deleteMdTeamById(Long id)
    {
        return mdTeamMapper.deleteMdTeamById(id);
    }
}
