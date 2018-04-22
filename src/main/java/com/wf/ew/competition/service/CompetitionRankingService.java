package com.wf.ew.competition.service;

import com.wf.ew.competition.model.CompetitionRanking;

import java.util.List;

public interface CompetitionRankingService {
    List<CompetitionRanking> queryCompetitionRanking(Long competitionId);
}
