package com.wf.ew.task.service;

import com.wf.ew.core.PageResult;
import com.wf.ew.task.model.Ctask;
import com.wf.ew.task.model.SubmitTask;
import com.wf.ew.task.model.Task;

import java.util.List;

public interface TaskService {
    PageResult<Task> queryTask(Integer page, Integer limit, String searchKey, String searchValue, String tno);
    int deleteTask(Integer tid);
    boolean shareTask(String tno,String tid,String cno,String time,String endTime);
    PageResult<SubmitTask> querySubmitedTask(Integer page, Integer limit, String searchKey, String searchValue, String tno);
    PageResult<SubmitTask> queryStudentSubmitTask(Integer page, Integer limit, String searchKey, String searchValue, String cno,String tid);
    List<Ctask> showSharedTask(String tid);
}
