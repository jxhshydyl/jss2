package com.wf.ew.corpus.controller;

import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value="/api/code")
public class CodeController {
    @Autowired
    CodeService codeService;

    @GetMapping
    @ResponseBody
    public PageResult<Code> queryCode(Integer page, Integer limit, String searchKey, String searchValue){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<Code> pageResult=codeService.queryCode(page, limit, searchKey, searchValue);
        return pageResult;
    }
    @DeleteMapping("/{qid}")
    @ResponseBody
    public ResultMap delete(@PathVariable("qid") Long qid){
        int num = codeService.deleteCode(qid);
        if(num==1){
            return ResultMap.ok(200,"删除成功！");
        }
        return ResultMap.error(0,"删除失败！");
    }
}
