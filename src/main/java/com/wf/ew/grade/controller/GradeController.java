package com.wf.ew.grade.controller;

import com.wf.ew.core.ResultMap;
import com.wf.ew.grade.model.ClassDeatilGrade;
import com.wf.ew.grade.model.TaskGradeStatistic;
import com.wf.ew.grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/grade")
@Controller
public class GradeController {
    @Autowired
    GradeService gradeService;

    @RequestMapping("/taskGradeStatisticByCnoAndTime")
    @ResponseBody
    public ResultMap taskGradeStatisticByCnoAndTime(String cno, String time, String tno){
        List<TaskGradeStatistic> list = gradeService.taskGradeStatisticByCnoAndTime(cno,time,tno);
        return ResultMap.ok().put("list",list);
    }
    @RequestMapping("/taskGradeDetailByCnameAndTime")
    @ResponseBody
    public ResultMap taskGradeDetailByCnameAndTime(String cname, String time, String cno, String tno) {
        List<ClassDeatilGrade> list = gradeService.taskGradeDetailByCnameAndTime(cname,time,cno,tno);
        return ResultMap.ok().put("list",list);
    }

    @RequestMapping("/courseGradeByCnoAndTime")
    @ResponseBody
    public ResultMap courseGradeByCnoAndTime(String cno,String time, String tno) {
        List<TaskGradeStatistic> list = gradeService.courseGradeByCnoAndTime(cno,time,"1");
        return ResultMap.ok().put("list",list);
    }

    @RequestMapping("/courseDetailGradeByCnameAndTaskName")
    @ResponseBody
    public ResultMap courseDetailGradeByCnameAndTaskName(String cname, String taskName, String cno,String tno) {
        List<ClassDeatilGrade> list = gradeService.courseDetailGradeByCnameAndTaskName(cname,taskName,cno,"1");
        return ResultMap.ok().put("list",list);
    }
}
