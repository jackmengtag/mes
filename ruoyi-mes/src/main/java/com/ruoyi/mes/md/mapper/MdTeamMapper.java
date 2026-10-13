package com.ruoyi.mes.md.mapper;

import java.util.List;
import com.ruoyi.mes.md.domain.MdTeam;

/**
 * 班组Mapper接口
 *
 * @author ruoyi
 */
public interface MdTeamMapper
{
    /**
     * 查询班组
     */
    public MdTeam selectMdTeamById(Long id);

    /**
     * 查询班组列表
     */
    public List<MdTeam> selectMdTeamList(MdTeam mdTeam);

    /**
     * 新增班组
     */
    public int insertMdTeam(MdTeam mdTeam);

    /**
     * 修改班组
     */
    public int updateMdTeam(MdTeam mdTeam);

    /**
     * 删除班组
     */
    public int deleteMdTeamById(Long id);

    /**
     * 批量删除班组
     */
    public int deleteMdTeamByIds(Long[] ids);
}
