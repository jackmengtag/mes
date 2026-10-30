package com.ruoyi.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mes.mapper.MesQualityIssueMapper;
import com.ruoyi.mes.domain.MesQualityIssue;
import com.ruoyi.mes.service.IMesQualityIssueService;

/**
 * 质量问题Service业务层处理
 *
 * @author ruoyi
 * @date 2026-10-30
 */
@Service
public class MesQualityIssueServiceImpl implements IMesQualityIssueService
{
    @Autowired
    private MesQualityIssueMapper mesQualityIssueMapper;

    @Override
    public MesQualityIssue selectMesQualityIssueById(Long issueId)
    {
        return mesQualityIssueMapper.selectMesQualityIssueById(issueId);
    }

    @Override
    public List<MesQualityIssue> selectMesQualityIssueList(MesQualityIssue mesQualityIssue)
    {
        return mesQualityIssueMapper.selectMesQualityIssueList(mesQualityIssue);
    }

    @Override
    public int insertMesQualityIssue(MesQualityIssue mesQualityIssue)
    {
        return mesQualityIssueMapper.insertMesQualityIssue(mesQualityIssue);
    }

    @Override
    public int updateMesQualityIssue(MesQualityIssue mesQualityIssue)
    {
        return mesQualityIssueMapper.updateMesQualityIssue(mesQualityIssue);
    }

    @Override
    public int deleteMesQualityIssueById(Long issueId)
    {
        return mesQualityIssueMapper.deleteMesQualityIssueById(issueId);
    }

    @Override
    public int deleteMesQualityIssueByIds(Long[] issueIds)
    {
        return mesQualityIssueMapper.deleteMesQualityIssueByIds(issueIds);
    }
}
