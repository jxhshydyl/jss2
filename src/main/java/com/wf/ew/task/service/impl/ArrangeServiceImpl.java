package com.wf.ew.task.service.impl;

import com.wf.ew.clazz.dao.ClassDao;
import com.wf.ew.clazz.model.Students;
import com.wf.ew.core.utils.DateUtil;
import com.wf.ew.grade.service.impl.GradeServiceImpl;
import com.wf.ew.task.dao.ArrangeDao;
import com.wf.ew.task.dao.TaskDao;
import com.wf.ew.task.model.*;
import com.wf.ew.task.service.ArrangeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ArrangeServiceImpl implements ArrangeService{
    @Autowired
    ArrangeDao arrangeDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    ClassDao classDao;
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
        try{
            Map<String,Object> map=new HashMap<String,Object>();
            if(condition.getClassNo()!=null && !"".equals(condition.getClassNo()) && !"null".equals(condition.getClassNo())){
                task.setTstate("已发布");
            }else{
                task.setTstate("未发布");
            }
            //task.setTno("1");                        //111 110 101 011 100 010 001 000
            //TODO 教师编号，名称，类型(是否开启答案，是否开启信息，是否学生题目相同)共八种类型
            arrangeDao.arrangeTask(task);
            int tid=task.getTid();//返回的主键
            if(condition.getClassNo()!=null && !"".equals(condition.getClassNo()) && !"null".equals(condition.getClassNo())){
                String time[]=condition.getTime().split(",");
                String endTime[]=condition.getEndTime().split(",");
                List<String> list=new ArrayList<String>();
                List<String> endList=new ArrayList<String>();
                String cno[]=condition.getClassNo().split(",");
                for(int i=0;i<time.length;i++){
                    if(!"".equals(time[i].trim())){
                        list.add(time[i]);
                        endList.add(endTime[i]);
                    }
                }
                List<Object> lists=new ArrayList<Object>();
                for(int i=0;i<cno.length;i++){
                    if(!"".equals(cno[i].trim())){
                        Condition conditions=new Condition();
                        conditions.setCno(cno[i]);
                        conditions.setTime(list.get(i));
                        conditions.setEndTime(endList.get(i));
                        lists.add(conditions);
                    }
                }
                map.put("condition",lists);
                map.put("tid",tid);
                taskDao.shareTask(map);
                Stask stask=new Stask();
                stask.setTstate("未提交");
                stask.setTid(tid);
                List<Students> students = classDao.queryStudentByClassCno(lists);
                taskDao.insertStask(students, stask);
            }
            if(autoMakePaperPara.getTypes()!=null && autoMakePaperPara.getScore()!=null){
                String[] scores=autoMakePaperPara.getScore().split(",");
                String[] counts=autoMakePaperPara.getCount().split(",");
                String[] types=autoMakePaperPara.getTypes().split(",");
                String courseNo=task.getCno();
                String[] chapter=null;
                if(task.getTchapter() != null){
                    chapter=task.getTchapter().split(",");
                }
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
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    @Override
    @Transactional
    public int arrangeCodeTask(Task task, AutoMakePaperPara autoMakePaperPara, Condition condition){
        try{
            Map<String,Object> map=new HashMap<String,Object>();
            if(condition.getClassNo()!=null && !"".equals(condition.getClassNo()) && !"null".equals(condition.getClassNo())){
                task.setTstate("已发布");
            }else{
                task.setTstate("未发布");
            }
            //task.setTno("1");                        //111 110 101 011 100 010 001 000
            //TODO 教师编号，名称，类型(是否开启答案，是否开启信息，是否学生题目相同)共八种类型
            arrangeDao.arrangeTask(task);
            int tid=task.getTid();//返回的主键
            if(condition.getClassNo()!=null && !"".equals(condition.getClassNo()) && !"null".equals(condition.getClassNo())){
                String time[]=condition.getTime().split(",");
                String endTime[]=condition.getEndTime().split(",");
                List<String> list=new ArrayList<String>();
                List<String> endList=new ArrayList<String>();
                String cno[]=condition.getClassNo().split(",");
                for(int i=0;i<time.length;i++){
                    if(!"".equals(time[i].trim())){
                        list.add(time[i]);
                        endList.add(endTime[i]);
                    }
                }
                List<Object> lists=new ArrayList<Object>();
                for(int i=0;i<cno.length;i++){
                    if(!"".equals(cno[i].trim())){
                        Condition conditions=new Condition();
                        conditions.setCno(cno[i]);
                        conditions.setTime(list.get(i));
                        conditions.setEndTime(endList.get(i));
                        lists.add(conditions);
                    }
                }
                map.put("condition",lists);
                map.put("tid",tid);
                taskDao.shareTask(map);
            }
            if(autoMakePaperPara.getCount() != null){
                String counts=autoMakePaperPara.getCount();
                String courseNo=task.getCno();
                String[] chapter=null;
                if(task.getTchapter() != null){
                    chapter=task.getTchapter().split(",");
                }
                List<Integer> integers = arrangeDao.queryCodeListByType(courseNo, chapter, Integer.valueOf(counts));
                if(integers.size()!=0){
                    arrangeDao.autoMakeQuestionPaper(tid,integers,autoMakePaperPara.getScore(),1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }











    @Override
    public int judgeTaskName(String taskName){
        String s = DateUtil.formatMDate(new Date());
        String semester = GradeServiceImpl.isSemester(s);
        String startTime=null;
        String endTime=null;
        String year = DateUtil.getCurrentYear();
        String nextYear =String.valueOf (Integer.valueOf(year)+1);
        if("2".equals(semester)||"3".equals(semester)||"4".equals(semester)||"5".equals(semester)||"6".equals(semester)||"7".equals(semester)||"8".equals(semester)){
            startTime=year+"-"+"02";
            endTime=year+"-"+"09";
        }else{
            startTime=year+"-"+"09";
            endTime=nextYear+"-"+"02";
        }
        return taskDao.judgeTaskName(taskName,startTime,endTime);
    }
    @Transactional
   public int arrangeNewTask(Integer tid,String newTaskName){
       Task task = taskDao.queryTaskByTid(tid);
       task.setTaskName(newTaskName);
       task.setTstate("未发布");
       task.setSubtime(DateUtil.getCurrentDate());
       arrangeDao.arrangeTask(task);
       int newTid=tid;
       newTid=task.getTid();//返回的主键
       List<TaskDetail> taskDetails = taskDao.queryTaskDetailByTid(tid);
       int i = arrangeDao.insertTdetail(newTid, taskDetails);
       return newTid;
    }
}
