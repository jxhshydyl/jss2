package com.wf.ew.task.service.impl;

import com.wf.ew.core.utils.DateUtil;
import com.wf.ew.task.dao.ArrangeDao;
import com.wf.ew.task.dao.TaskDao;
import com.wf.ew.task.model.AutoMakePaperPara;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.model.Task;
import com.wf.ew.task.service.ArrangeService;
import org.apache.ibatis.annotations.Param;
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
        Map<String,Object> map=new HashMap<String,Object>();
        if(condition.getClassNo()!=null && !"".equals(condition.getClassNo()) && !"null".equals(condition.getClassNo())){
            task.setTstate("已发布");
        }else{
            task.setTstate("未发布");
        }
        task.setTno("1");                        //111 110 101 011 100 010 001 000
        //TODO 教师编号，名称，类型(是否开启答案，是否开启信息，是否学生题目相同)共八种类型
        task.setSubtime(DateUtil.getCurrentDate());
        arrangeDao.arrangeTask(task);
        int tid=task.getTid();//返回的主键
        if(condition.getClassNo()!=null && !"".equals(condition.getClassNo()) && !"null".equals(condition.getClassNo())){
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
            map.put("condition",lists);
            map.put("tid",tid);
            taskDao.shareTask(map);
        }
        if(autoMakePaperPara.getTypes()!=null && autoMakePaperPara.getScore()!=null){
            String[] scores=autoMakePaperPara.getScore().split(",");
            String[] counts=autoMakePaperPara.getCount().split(",");
            String[] types=autoMakePaperPara.getTypes().split(",");
            String courseNo=task.getCno();
            String[] chapter=task.getTchapter().split(",");
            for(int i=0;i<types.length;i++){
                if("编程题".equals(types[i])){
                    List<Integer> integers = arrangeDao.queryCodeList(courseNo, chapter, Integer.valueOf(counts[i]));
                    if(integers.size()!=0){
                        arrangeDao.autoMakeQuestionPaper(tid,integers,scores[i],1);
                    }
                }else{
                    List<Integer> integers = arrangeDao.queryQuestionList(courseNo, chapter, Integer.valueOf(counts[i]),types[i]);
                    if(integers.size()!=0){
                        arrangeDao.autoMakeQuestionPaper(tid,integers,scores[i],0);
                    }
                }
            }
        }
        return 0;
    }
}
