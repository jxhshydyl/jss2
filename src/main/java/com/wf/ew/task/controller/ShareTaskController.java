package com.wf.ew.task.controller;

import com.wf.ew.core.ResultMap;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jss/task")
public class ShareTaskController {
    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/shareTask",method = RequestMethod.POST)
    @ResponseBody
    public ResultMap shareTask(String tno,String tid,String cno,String time){
        System.out.println(tno);
        System.out.println(tid);
        System.out.println(cno);
        System.out.println(time);
        boolean isSuccess = taskService.shareTask(tno, tid, cno, time);
        if(isSuccess){
            return ResultMap.ok(200,"发布成功！");
        }
        return ResultMap.error(0,"发布失败！");
    }
}
