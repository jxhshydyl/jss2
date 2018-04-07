package com.wf.ew.task.controller;

import com.wf.ew.core.ResultMap;
import com.wf.ew.task.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class PageController {
    @Autowired
    PageService pageService;
    @RequestMapping("/queryPage")
    public String queryPage(Integer tid, ModelMap model){
        Map<String, Object> map = pageService.queryPageByTid(tid);
 /*       model.put("questions", map.get("questions"));
        model.put("codes", map.get("codes"));*/
        model.put("taskDetail", map.get("taskDetail"));
        model.put("taskBasic", map.get("taskBasic"));
        return "page";
    }
    @PostMapping("/querySimilarQuestion")
    @ResponseBody
    public ResultMap querySimilarQuestion(String qtype,String cname, String qchapter, Integer tid){
        List<Object> list=pageService.querySimilarQuestion(qtype,cname,qchapter,tid);

        return ResultMap.ok().put("list",list);
    }
    @PostMapping("/replaceSilimarQuestion")
    @ResponseBody
    public ResultMap replaceSilimarQuestion(Long newQid, Long oldQid, Integer tid){
       int num=pageService.replaceSilimarQuestion(newQid,oldQid,tid);
       System.out.println(num);
       if(num!=0){
           return ResultMap.ok(200,"替换成功！");
       }
        return ResultMap.ok(0,"替换失败！");
    }
    @PostMapping("/deleteTaskQuestion")
    @ResponseBody
    public ResultMap deleteTaskQuestion(Long qid, Integer tid, String qtype){
        System.out.println(qid);
        System.out.println(tid);
        System.out.println(qtype);
        int num=pageService.deleteTaskQuestion(qid,tid,qtype);
        System.out.println(num);
        if(num!=0){
            return ResultMap.ok(200,"删除成功！");
        }
        return ResultMap.error(0,"删除失败！");
    }

    @RequestMapping("/queryStudentSubmitPage")
    public String queryStudentSubmitPage(Long id,ModelMap model){
        Map<String, Object> map = pageService.querySubmitTaskById(id);
        model.put("taskSubmitDetail", map.get("taskSubmitDetail"));
        model.put("studentBasic", map.get("studentBasic"));
        return "studentsPage";
    }
    @RequestMapping("/saveSubmitTaskDetail")
    @ResponseBody
    public ResultMap saveSubmitTaskDetail(@RequestParam("qids[]") Long[] qids, @RequestParam("scores[]") float[] scores, @RequestParam("types[]") String[] types,Long id ,float totalScore){
        int num=pageService.saveSubmitTaskDetail(qids,scores,types,id,totalScore);
        if(num!=0){
            return ResultMap.ok(200,"删除成功！");
        }
        return ResultMap.error(0,"删除失败！");
    }
}
