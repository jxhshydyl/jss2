package com.wf.ew.competition.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.competition.dao.CompetitionDao;
import com.wf.ew.competition.model.Competition;
import com.wf.ew.competition.service.CompetitionService;
import com.wf.ew.core.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService{
    @Autowired
    CompetitionDao competitionDao;

    @Override
    public PageResult<Competition> queryCompetition(Integer pageNum, Integer pageSize, String searchKey, String searchValue){
        Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
        List<Competition> competitions = competitionDao.queryCompetition(searchKey, searchValue);
        System.out.println(competitions);
        PageResult<Competition> result = new PageResult<Competition>();
        result.setData(competitions);
        result.setCount(startPage.getTotal());
        return result;
    }
    @Override
    public int updateClose(Integer competitionId, Integer isClose){
        int num = competitionDao.updateClose(competitionId, isClose);
        return num;
    }

    @Override
    public int updatePublic(Integer competitionId, Integer isPublish){
        int num = competitionDao.updatePublic(competitionId,isPublish);
        return num;
    }

    @Override
    public int deleteCompetition(Integer competitionId){
        int num = competitionDao.deleteCompetition(competitionId);
        return num;
    }

    @Override
    public int downloadCompetitionReport(Integer competitionId){
        int num = competitionDao.deleteCompetition(competitionId);
        return num;
    }
    @Override
    public int addCompetition(Competition competition){
        int num = competitionDao.addCompetition(competition);
        return num;
    }
}
