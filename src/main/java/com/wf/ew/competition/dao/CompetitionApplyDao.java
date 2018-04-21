package com.wf.ew.competition.dao;

import com.wf.ew.competition.model.CompetitionApply;
import com.wf.ew.core.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompetitionApplyDao {
    List<CompetitionApply> queryCompetitionApply(@Param("searchKey") String searchKey,@Param("searchValue") String searchValue);
    int updateSuspendCompetition(@Param("competitionApplicationId")String competitionApplicationId,@Param("isSuspendCompetition")String isSuspendCompetition);
    String queryCompetitionAccountId(@Param("competitionApplicationId")String competitionApplicationId);
    int updateCompetitionAccount(String[] competitionAccountIds);
}
