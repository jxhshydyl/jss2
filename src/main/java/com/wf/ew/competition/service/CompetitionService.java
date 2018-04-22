package com.wf.ew.competition.service;

import com.wf.ew.competition.model.Competition;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface CompetitionService {
    PageResult<Competition> queryCompetition(Integer pageNum, Integer pageSize, String searchKey, String searchValue);
    int updateClose(Integer competitionId, Integer isClose);
    int updatePublic(Integer competitionId, Integer isPublish);
    int deleteCompetition(Integer competitionId);
    int downloadCompetitionReport(Integer competitionId);
    int addCompetition(Competition competition);
    List<Competition> queryEndedCompetition();
}
