package com.wf.ew.task.controller;

import com.wf.ew.core.ResultMap;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.model.Ctask;
import com.wf.ew.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jss/task")
public class ShareTaskController {
    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/shareTask",method = RequestMethod.POST)
    @ResponseBody
    public ResultMap shareTask(String tno,String tid,String cno,String time,String endTime){
        boolean isSuccess = taskService.shareTask(tno, tid, cno, time,endTime);
        if(isSuccess){
            return ResultMap.ok(200,"发布成功！");
        }
        return ResultMap.error(0,"发布失败！");
    }

    @RequestMapping(value = "/showSharedTask",method = RequestMethod.POST)
    @ResponseBody
    public List<Ctask> showSharedTask(String tid){
        System.out.println(tid);
        List<Ctask> ctasks = taskService.showSharedTask(tid);
        return ctasks;
    }

}
