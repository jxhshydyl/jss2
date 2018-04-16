package com.wf.ew.competition.service;

import com.wf.ew.competition.model.CompetitionApply;
import com.wf.ew.core.PageResult;

public interface CompetitionApplyService {
    PageResult<CompetitionApply> queryCompetitionApply(Integer page, Integer limit, String searchKey, String searchValue);
    CompetitionApply queryCompetitionApplyDetail(String competitionApplicationId);
    void sengEmail(String email);
    int cancelCompetitionAccount(String competitionApplicationId);
}
