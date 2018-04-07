package com.wf.ew.corpus.controller;

import com.wf.ew.core.ResultMap;
import com.wf.ew.corpus.model.AddQuestion;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.AddQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/jss/addQuestion")
public class AddQuestionController {
    @Autowired
    AddQuestionService addQuestionService;

    @PostMapping
    @ResponseBody
    public ResultMap addQuestion(Question question, AddQuestion addQuestion){
        System.out.println(question);
        System.out.println(addQuestion);
        int num = addQuestionService.addQuestion(question, addQuestion);
        if(num==1){
            return ResultMap.ok(200,"增加成功！");
        }
        return ResultMap.ok(0,"增加失败！");
    }

    @PostMapping
    @RequestMapping("/code")
    public String addCode(Code code,HttpServletRequest request){
        try{
            System.out.println("代码");
            System.out.println(code);
            addQuestionService.addCode(code);
            return "redirect:"+request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"index.html#!corpus/code";
        }catch (Exception e){
            return "redirect:"+request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"index.html#!corpus/code";
        }
    }
}
