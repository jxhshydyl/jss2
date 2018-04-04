package com.wf.ew.task.controller;

import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.model.Task;
import com.wf.ew.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    @ResponseBody
    public PageResult<Task> queryTask(Integer page, Integer limit, String searchKey, String searchValue,String tno){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<Task> taskPageResult = taskService.queryTask(page, limit, searchKey, searchValue, tno);
        return taskPageResult;
    }

    @DeleteMapping("/{tid}")
    @ResponseBody
    public ResultMap deleteTask(@PathVariable("tid") Integer tid){
        int num = taskService.deleteTask(tid);
        if(num>=1){
            return ResultMap.ok(200,"删除成功！");
        }
        return ResultMap.error(0,"删除失败！");
    }

}
