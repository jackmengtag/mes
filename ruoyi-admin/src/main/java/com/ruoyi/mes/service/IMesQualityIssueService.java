package com.ruoyi.mes.service;

import java.util.List;
import com.ruoyi.mes.domain.MesQualityIssue;

/**
 * 质量问题Service接口
 *
 * @author ruoyi
 * @date 2026-10-30
 */
public interface IMesQualityIssueService
{
    public MesQualityIssue selectMesQualityIssueById(Long issueId);

    public List<MesQualityIssue> selectMesQualityIssueList(MesQualityIssue mesQualityIssue);

    public int insertMesQualityIssue(MesQualityIssue mesQualityIssue);

    public int updateMesQualityIssue(MesQualityIssue mesQualityIssue);

    public int deleteMesQualityIssueById(Long issueId);

    public int deleteMesQualityIssueByIds(Long[] issueIds);
}
