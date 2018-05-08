package com.wf.ew.competition.controller;

import com.wf.ew.asynchronous.service.SendMailService;
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
    @Autowired
    SendMailService sendMailService;

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
    @RequestMapping("/sengEmail")
    @ResponseBody
    public ResultMap sengEmail(String email){
        sendMailService.sendCompetitionApplyAccount(email);
        return ResultMap.ok(200,"成功发送！");
    }

    @RequestMapping("/updateSuspendCompetition")
    @ResponseBody
    public ResultMap updateSuspendCompetition(String competitionApplicationId,String isSuspendCompetition){
        int num=competitionApplyService.updateSuspendCompetition(competitionApplicationId,isSuspendCompetition);
        if(num==1){
            return ResultMap.ok(200,"操作成功！");
        }
        return ResultMap.error(0,"操作失败！");
    }
}
