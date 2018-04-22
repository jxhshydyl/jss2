package com.wf.ew.competition.service.impl;

import com.wf.ew.competition.dao.CompetitionRankingDao;
import com.wf.ew.competition.model.CompetitionRanking;
import com.wf.ew.competition.model.ProblemSubmitInfo;
import com.wf.ew.competition.service.CompetitionRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionRankingServiceImpl implements CompetitionRankingService {
    @Autowired
    CompetitionRankingDao competitionRankingDao;
    @Override
    public List<CompetitionRanking> queryCompetitionRanking(Long competitionId){
        List<CompetitionRanking> competitionRankings = competitionRankingDao.queryCompetitionRanking(competitionId);
       System.out.println(competitionRankings);
        for(CompetitionRanking competitionRanking:competitionRankings){
            System.out.println(competitionRanking);
            List<ProblemSubmitInfo> problemSubmitInfos=competitionRanking.getProblemSubmitInfos();
            if(problemSubmitInfos!=null){
                competitionRanking.setTotalCount(problemSubmitInfos.size());
                int totalTime=0;
                for(ProblemSubmitInfo problemSubmitInfo:problemSubmitInfos){
                    System.out.println(problemSubmitInfo);
                    if(problemSubmitInfo!=null){
                        totalTime+=problemSubmitInfo.getAcceptedTime()+(problemSubmitInfo.getSubmitCount()-1)*20;
                    }
                }
                competitionRanking.setTotalTime(totalTime);
            }else{
                competitionRanking.setTotalCount(0);
            }
        }
        //todo 进行排序
        return competitionRankings;
    }
}
