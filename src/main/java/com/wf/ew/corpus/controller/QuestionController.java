package com.wf.ew.corpus.controller;

import com.wf.ew.core.BaseController;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/api/question")
public class QuestionController{
    @Autowired
    QuestionService questionService;
    /**
     * 查询所有问题
     */
    @GetMapping()
    @ResponseBody
    public PageResult<Question> list(Integer page, Integer limit, String searchKey, String searchValue){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<Question> pageResult=questionService.getQuestion(page, limit, searchKey, searchValue);
        return pageResult;
    }
    @DeleteMapping("/{qid}")
    @ResponseBody
    public ResultMap delete(@PathVariable("qid") Long qid){
        int i = questionService.deleteQuestion(qid);
        if(i>0){
            return ResultMap.ok(200,"删除成功！");
        }else{
            return ResultMap.error(1,"删除失败！");
        }
    }
}
