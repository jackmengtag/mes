package com.ruoyi.mes.md.service;

import java.util.List;
import com.ruoyi.mes.md.domain.MdTeam;

/**
 * 班组Service接口
 *
 * @author ruoyi
 */
public interface IMdTeamService
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
     * 批量删除班组
     */
    public int deleteMdTeamByIds(Long[] ids);

    /**
     * 删除班组信息
     */
    public int deleteMdTeamById(Long id);
}
