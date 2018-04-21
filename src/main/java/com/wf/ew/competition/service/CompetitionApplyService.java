package com.wf.ew.competition.service;

import com.wf.ew.competition.model.CompetitionApply;
import com.wf.ew.core.PageResult;

public interface CompetitionApplyService {
    PageResult<CompetitionApply> queryCompetitionApply(Integer page, Integer limit, String searchKey, String searchValue);
    void sengEmail(String email);
    int updateSuspendCompetition(String competitionApplicationId,String isSuspendCompetition);
}
