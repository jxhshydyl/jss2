package com.wf.ew.competition.dao;

import com.wf.ew.competition.model.CompetitionRanking;

import java.util.List;

public interface CompetitionRankingDao {
    List<CompetitionRanking> queryCompetitionRanking(Long competitionId);

}
