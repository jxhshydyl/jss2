package com.wf.ew.competition.dao;

import com.wf.ew.competition.model.Competition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompetitionDao {
    List<Competition> queryCompetition(@Param("searchKey") String searchKey,@Param("searchValue") String searchValue);
    int updateClose(@Param("competitionId")Integer competitionId, @Param("isClose") Integer isClose);
    int updatePublic(@Param("competitionId")Integer competitionId, @Param("isPublish") Integer isPublish);
    int deleteCompetition(@Param("competitionId")Integer competitionId);
    int addCompetition(Competition competition);
}
