package com.wf.ew.clazz.controller;

import com.wf.ew.clazz.model.Class;
import com.wf.ew.clazz.model.Students;
import com.wf.ew.clazz.service.ClassService;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import com.wf.ew.corpus.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @GetMapping
    @ResponseBody
    public PageResult<Class> queryClass(Integer page, Integer limit, String searchKey, String searchValue,String tno){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<Class> pageResult = classService.queryClass(page, limit, searchKey, searchValue, tno);
        System.out.println(pageResult);
        return pageResult;
    }
    @RequestMapping(value="/students",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<Students>  queryStudentsByClass(Integer page, Integer limit, String searchKeys, String searchValues,String cno){
        System.out.println(cno);
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<Students> pageResult = classService.queryStudentsByClass(page, limit, searchKeys, searchValues, cno);
        System.out.println(pageResult);
        return pageResult;
    }
}
