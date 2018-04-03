package com.wf.ew.task.controller;

import com.wf.ew.core.ResultMap;
import com.wf.ew.task.service.ArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jss/arrangeTask")
public class ArrangeTaskController {
    @Autowired
    ArrangeService arrangeService;

    @PostMapping()
    @ResponseBody
    public ResultMap queryQuestionCountByType(String type,String cno,@RequestParam(value = "chapter[]",defaultValue = "") String[] chapter){
        System.out.println(type);
        System.out.println(cno);
        System.out.println(chapter.length);
        int num = arrangeService.queryQuestionCountByType(type,cno,chapter);
        return ResultMap.ok(0,"查询成功！").put("value",num);
    }
}
