package com.wf.ew.competition.controller;

import com.wf.ew.competition.model.CompetitionApply;
import com.wf.ew.competition.service.CompetitionApplyService;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/competitionApply")
@Controller
public class CompetitionApplyController {
    @Autowired
    CompetitionApplyService competitionApplyService;

    @RequestMapping("/queryCompetitionApply")
    @ResponseBody
    public PageResult<CompetitionApply> queryCompetitionApply(Integer page, Integer limit, String searchKey, String searchValue){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<CompetitionApply> pageResult = competitionApplyService.queryCompetitionApply(page, limit, searchKey, searchValue);
        System.out.println(pageResult);
        return pageResult;
    }
    @RequestMapping("/queryCompetitionApplyDetail")
    @ResponseBody
    public CompetitionApply queryCompetitionApplyDetail(String competitionApplicationId){
        CompetitionApply competitionApply = competitionApplyService.queryCompetitionApplyDetail(competitionApplicationId);
        return competitionApply;
    }
    @RequestMapping("/sengEmail")
    @ResponseBody
    public ResultMap sengEmail(String email){
        competitionApplyService.sengEmail(email);
        return ResultMap.ok(200,"成功发送！");
    }

    @DeleteMapping("/cancelCompetitionAccount/{competitionApplicationId}")
    @ResponseBody
    public ResultMap cancelCompetitionAccount(@PathVariable("competitionApplicationId") String competitionApplicationId){
        int num=competitionApplyService.cancelCompetitionAccount(competitionApplicationId);
        if(num==1){
            return ResultMap.ok(200,"取消成功！");
        }
        return ResultMap.error(0,"取消失败！");
    }
}
