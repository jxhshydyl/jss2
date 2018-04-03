package com.wf.ew.task.service.impl;

import com.wf.ew.task.dao.ArrangeDao;
import com.wf.ew.task.dao.TaskDao;
import com.wf.ew.task.model.AutoMakePaperPara;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.model.Task;
import com.wf.ew.task.service.ArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArrangeServiceImpl implements ArrangeService{
    @Autowired
    ArrangeDao arrangeDao;
    @Autowired
    TaskDao taskDao;
    @Override
    public int queryQuestionCountByType(String type,String cno,String[] chapter){
        if("编程题".equals(type)){
            return arrangeDao.queryCodeCountByType(type,cno,chapter);
        }
        return arrangeDao.queryQuestionCountByType(type,cno,chapter);
    }
    @Override
    @Transactional
    public int arrangeTask(Task task, AutoMakePaperPara autoMakePaperPara, Condition condition){
        if(condition.getClassNo()!=null && "".equals(condition.getClassNo())){
            task.setTstate("未发布");
        }else{
            task.setTstate("已发布");
        }
        task.setTno("1");
        arrangeDao.arrangeTask(task);
        Map<String,Object> map=new HashMap<String,Object>();
        String time[]=condition.getTime().split(",");
        List<String> list=new ArrayList<String>();
        String cno[]=condition.getClassNo().split(",");
        for(int i=0;i<time.length;i++){
            if(!"".equals(time[i].trim())){
                list.add(time[i]);
            }
        }
        List<Object> lists=new ArrayList<Object>();
        for(int i=0;i<cno.length;i++){
            if(!"".equals(cno[i].trim())){
                Condition conditions=new Condition();
                conditions.setCno(cno[i]);
                conditions.setTime(list.get(i));
                lists.add(conditions);
            }
        }
        int tid=task.getTid();//返回的主键
        map.put("condition",lists);
        map.put("tid",tid);
        taskDao.shareTask(map);
        String[] scores=autoMakePaperPara.getScore().split(",");
        String[] counts=autoMakePaperPara.getCount().split(",");
        String[] types=autoMakePaperPara.getTypes().split(",");
        for(int i=0;i<types.length;i++){
            if("编程题".equals(types[i])){
                arrangeDao.autoMakeCodePaper(tid,scores[i],counts[i],types[i]);
            }else{
                arrangeDao.autoMakeQuestionPaper(tid,scores[i],counts[i],types[i]);
            }
        }


        return 0;
    }
}
