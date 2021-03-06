package com.wf.ew.task.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.asynchronous.service.SendMailService;
import com.wf.ew.core.PageResult;
import com.wf.ew.task.dao.CtaskDao;
import com.wf.ew.task.dao.TaskDao;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.model.Ctask;
import com.wf.ew.task.model.SubmitTask;
import com.wf.ew.task.model.Task;
import com.wf.ew.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskDao taskDao;
    @Autowired
    CtaskDao ctaskDao;
    @Autowired
    SendMailService sendMailService;
    @Override
    public PageResult<Task> queryTask(Integer page, Integer limit, String searchKey, String searchValue, String tno){
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<Task> tasks = taskDao.queryTask(searchKey, searchValue, tno);
        PageResult<Task> result = new PageResult<Task>();
        result.setData(tasks);
        result.setCount(startPage.getTotal());
        return result;
    }
    @Override
    public int deleteTask(Integer tid){
        return taskDao.deleteTask(tid);
    }

    @Override
    @Transactional
    public boolean shareTask(String tno,String tid,String cno,String time,String endTime){
        String[] cnos=cno.split(",");
        String[] times=time.split(",");
        String[] endTimes=endTime.split(",");
        int key=0;
        List<Condition> list=new ArrayList<Condition>();
        sendMailService.sendTaskMail(tno,tid,cno,time,endTime);
        for(int i=0;i<cnos.length;i++){
            if(!"".equals(cnos[i].trim())){
                Condition condition=new Condition();
                condition.setCno(cnos[i]);
                condition.setTime(times[i]);
                condition.setEndTime(endTimes[i]);
                list.add(condition);
                key++;
            }
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("condition",list);
        map.put("tid",tid);
        int num = taskDao.shareTask(map);
        taskDao.updateState(tid,"已发布");
        if(key==num){
            return true;
        }
        return false;
    }
    @Override
    public PageResult<SubmitTask> querySubmitedTask(Integer page, Integer limit, String searchKey, String searchValue, String tno){
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<SubmitTask> tasks = taskDao.querySubmitedTask(searchKey, searchValue, tno);
        PageResult<SubmitTask> result = new PageResult<SubmitTask>();
        result.setData(tasks);
        result.setCount(startPage.getTotal());
        return result;
    }
    public PageResult<SubmitTask> queryStudentSubmitTask(Integer page, Integer limit, String searchKey, String searchValue,String cno,String tid){
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<SubmitTask> tasks = taskDao.queryStudentSubmitTask(searchKey, searchValue, cno,tid);
        PageResult<SubmitTask> result = new PageResult<SubmitTask>();
        result.setData(tasks);
        result.setCount(startPage.getTotal());
        return  result;
    }
    @Override
    public List<Ctask> showSharedTask(String tid){
        List<Ctask> ctasks = ctaskDao.querySharedTask(tid);
        return ctasks;
    }
}
