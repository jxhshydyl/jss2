package com.wf.ew.competition.controller;

import com.wf.ew.competition.model.CompetitionRanking;
import com.wf.ew.competition.service.CompetitionRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/competitionRanking")
public class CompetitionRankingController {
    @Autowired
    CompetitionRankingService competitionRankingService;

    @RequestMapping("/queryCompetitionRanking")
    @ResponseBody
    public List<CompetitionRanking> queryCompetitionRanking(Long competitionId){
        List<CompetitionRanking> competitionRankings = competitionRankingService.queryCompetitionRanking(competitionId);
        return competitionRankings;
    }
}
