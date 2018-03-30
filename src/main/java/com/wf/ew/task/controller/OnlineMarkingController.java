package com.wf.ew.task.controller;

import com.wf.ew.core.PageResult;
import com.wf.ew.task.model.SubmitTask;
import com.wf.ew.task.model.Task;
import com.wf.ew.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/onlineMarking")
public class OnlineMarkingController {
    @Autowired
    TaskService taskService;

    @GetMapping
    @ResponseBody
    public PageResult<SubmitTask> querySubmitedTask(Integer page, Integer limit, String searchKey, String searchValue, String tno){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<SubmitTask> submitTaskPageResult = taskService.querySubmitedTask(page, limit, searchKey, searchValue, tno);
        return submitTaskPageResult;
    }

    @RequestMapping(value="/studentTask",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<SubmitTask> queryStudentSubmitTask(Integer page, Integer limit, String searchKeys, String searchValues, String cno,String tid){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        System.out.println("条件");
        System.out.println(tid);
        System.out.println(cno);
        PageResult<SubmitTask> submitTaskPageResult = taskService.queryStudentSubmitTask(page, limit, searchKeys, searchValues, cno, tid);
        return submitTaskPageResult;
    }
}
