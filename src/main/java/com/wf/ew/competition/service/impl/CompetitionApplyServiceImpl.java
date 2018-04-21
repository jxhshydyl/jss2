package com.wf.ew.competition.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.competition.dao.CompetitionApplyDao;
import com.wf.ew.competition.model.CompetitionApply;
import com.wf.ew.competition.service.CompetitionApplyService;
import com.wf.ew.core.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionApplyServiceImpl implements CompetitionApplyService {
    @Autowired
    CompetitionApplyDao competitionApplyDao;

    public PageResult<CompetitionApply> queryCompetitionApply(Integer page, Integer limit, String searchKey, String searchValue){
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<CompetitionApply> competitionApplies = competitionApplyDao.queryCompetitionApply(searchKey, searchValue);
        System.out.println(competitionApplies);
        PageResult<CompetitionApply> result = new PageResult<CompetitionApply>();
        result.setData(competitionApplies);
        result.setCount(startPage.getTotal());
        return result;
    }

    public void sengEmail(String email){

    }

    public int updateSuspendCompetition(String competitionApplicationId,String isSuspendCompetition){
        int num=competitionApplyDao.updateSuspendCompetition(competitionApplicationId,isSuspendCompetition);
        // todo 需要把这个申请的所有比赛帐号更新状态
        //competitionAccountId = competitionApplyDao.queryCompetitionAccountId(competitionApplicationId);
        //String[] competitionAccountIds=competitionAccountId.split(",");
        //int i=competitionApplyDao.updateCompetitionAccount(competitionAccountIds);
        return num;
    }
}
