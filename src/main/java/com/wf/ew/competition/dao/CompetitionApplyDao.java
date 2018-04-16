package com.wf.ew.competition.dao;

import com.wf.ew.competition.model.CompetitionApply;
import com.wf.ew.core.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompetitionApplyDao {
    List<CompetitionApply> queryCompetitionApply(@Param("searchKey") String searchKey,@Param("searchValue") String searchValue);
    CompetitionApply queryCompetitionApplyDetail(String competitionApplicationId);
    int cancelCompetitionAccount(String competitionApplicationId);
}
