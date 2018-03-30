package com.wf.ew.corpus.controller;

import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import com.wf.ew.corpus.model.AddQuestion;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.AddQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;

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
        addQuestionService.addQuestion(question,addQuestion);
        return ResultMap.ok(200,"增加成功！");
    }
}
