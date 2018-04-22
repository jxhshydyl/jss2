package com.wf.ew.competition.controller;

import com.wf.ew.competition.model.Competition;
import com.wf.ew.competition.service.CompetitionService;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/competition")
@Controller
public class CompetitionController {
    @Autowired
    CompetitionService competitionService;

    @GetMapping("/queryCompetition")
    @ResponseBody
    public PageResult<Competition> queryCompetition(Integer page, Integer limit, String searchKey, String searchValue){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<Competition> pageResult = competitionService.queryCompetition(page, limit, searchKey, searchValue);
        System.out.println(pageResult);
        return pageResult;
    }

    @RequestMapping("/updateClose")
    @ResponseBody
    public ResultMap updateClose(Integer competitionId, Integer isClose){
        int num=competitionService.updateClose(competitionId,isClose);
        if(num==1){
            return ResultMap.ok(200,"成功设置！");
        }
        return ResultMap.error("操作错误！");
    }

    @RequestMapping("/updatePublic")
    @ResponseBody
    public ResultMap updatePublic(Integer competitionId, Integer isPublish){
        int num = competitionService.updatePublic(competitionId, isPublish);
        if(num==1){
            return ResultMap.ok(200,"成功设置！");
        }
        return ResultMap.error("操作错误！");
    }

    @DeleteMapping("/deleteCompetition/{competitionId}")
    @ResponseBody
    public ResultMap deleteCompetition(@PathVariable("competitionId") Integer competitionId){
        System.out.println("删除比赛");
        System.out.println(competitionId);
        int num = competitionService.deleteCompetition(competitionId);
        if(num==1){
            return ResultMap.ok(200,"删除成功！");
        }
        return ResultMap.error("删除失败！");
    }

    @DeleteMapping("/downloadCompetitionReport/{competitionId}")
    @ResponseBody
    public ResultMap downloadCompetitionReport(@PathVariable("competitionId") Integer competitionId){
        System.out.println("下载报告");
        System.out.println(competitionId);
        int num = competitionService.downloadCompetitionReport(competitionId);
        if(num==1){
            return ResultMap.ok(200,"删除成功！");
        }
        return ResultMap.error("删除失败！");
    }

    @RequestMapping("/addCompetition")
    @ResponseBody
    public ResultMap addCompetition(Competition competition){
        int num = competitionService.addCompetition(competition);
        if(num==1){
            return ResultMap.ok(200,"增加成功！");
        }
        return ResultMap.error("增加失败！");
    }

    @RequestMapping("/queryEndedCompetition")
    @ResponseBody
    public ResultMap queryEndedCompetition(){
        List<Competition> result = competitionService.queryEndedCompetition();
        return ResultMap.ok().put("competition",result);
    }

}
