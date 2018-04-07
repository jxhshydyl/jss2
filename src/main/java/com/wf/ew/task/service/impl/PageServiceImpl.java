package com.wf.ew.task.service.impl;

import com.wf.ew.clazz.model.Students;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.task.dao.PageDao;
import com.wf.ew.task.model.TaskBasic;
import com.wf.ew.task.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService{
    @Autowired
    PageDao pageDao;

    @Override
    public Map<String,Object> queryPageByTid(Integer tid){
        List<Question> questions = pageDao.queryPageQuestionByTid(tid);
        List<Code> codes = pageDao.queryPageCodeByTid(tid);
        TaskBasic taskBasic = pageDao.queryBasicPageQuestionByTid(tid);
        TaskBasic taskBasic1 = pageDao.queryBasicPageCodeByTid(tid);
        taskBasic.getList().addAll(taskBasic1.getList());
        for(Question question:questions){
            if("单选题".equals(question.getQtype())||"多选题".equals(question.getQtype())){
                question.setChoice(question.getQchoice().split("[A-Z][|:|：|、|.]"));
            }
        }
        List list=new ArrayList();
        list.addAll(questions);
        list.addAll(codes);
        Map<String,Object> map=new HashMap<String,Object>();
/*        map.put("questions",questions);
        map.put("codes",codes);*/
        map.put("taskDetail",list);
        map.put("taskBasic",taskBasic);
        return map;
    }

    @Override
    public List<Object> querySimilarQuestion(String qtype,String cname, String qchapter, Integer tid){
        List<Object> list;
        if("单选题".equals(qtype) || "多选题".equals(qtype) || "判断题".equals(qtype) || "填空题".equals(qtype) || "简答题".equals(qtype)){
            list = pageDao.querySimilarQuestion(qtype,cname,qchapter,tid);
        }else{
            list = pageDao.querySililarCode(qtype,cname,qchapter,tid);
        }
        return list;
    }
    @Override
    public int replaceSilimarQuestion(Long newQid, Long oldQid, Integer tid){
        return pageDao.replaceSilimarQuestion(newQid,oldQid,tid);
    }
    @Override
    public int deleteTaskQuestion(Long qid, Integer tid, String qtype){
        int num;
        if("单选题".equals(qtype)||"多选题".equals(qtype)||"判断题".equals(qtype)
                ||"填空题".equals(qtype)||"简答题".equals(qtype)){
            num=pageDao.deleteTaskQuestion(qid,tid,0);
        }else{
            num=pageDao.deleteTaskQuestion(qid,tid,1);
        }
        pageDao.updateTotalScore(tid);
        return num;
    }
    @Override
    public Map<String,Object> querySubmitTaskById(Long id){
        List<Question> questions = pageDao.querySubmitTaskQuestionDetail(id);//学生作业详细信息
        List<Code> codes = pageDao.querySubmitTaskCodeDetail(id);//学生作业详细信息
        Students studentBasic = pageDao.queryStudentByStaskId(id);//学生作业基本信息
        for(Question question:questions){
            if("单选题".equals(question.getQtype())||"多选题".equals(question.getQtype())){
                question.setChoice(question.getQchoice().split("[A-Z][|:|：|、|.]"));
            }
        }
        List list=new ArrayList();
        list.addAll(questions);
        list.addAll(codes);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("taskSubmitDetail",list);
        map.put("studentBasic",studentBasic);
        return map;
    }

    @Override
    public int saveSubmitTaskDetail(Long[] qids, float[] scores,  String[] types, Long id , float totalScore){
        int num=pageDao.saveSubmitTaskScore(id,totalScore);
        for(int i=0;i<qids.length;i++){
            if("单选题".equals(types[i])||"多选题".equals(types[i])||"判断题".equals(types[i])
                    ||"填空题".equals(types[i])||"简答题".equals(types[i])){
                 num=pageDao.saveSubmitTaskQuestionDetail(id,qids[i],scores[i]);
                 num++;
            }else{
                 num=pageDao.saveSubmitTaskCodeDetail(id,qids[i],scores[i]);
                 num++;
            }
        }
        return num;
    }
}
