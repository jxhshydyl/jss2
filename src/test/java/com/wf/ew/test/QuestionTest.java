package com.wf.ew.test;

import com.wf.ew.clazz.dao.ClassDao;
import com.wf.ew.clazz.model.Class;
import com.wf.ew.clazz.model.Students;
import com.wf.ew.clazz.service.ClassService;
import com.wf.ew.core.PageResult;
import com.wf.ew.corpus.dao.CodeDao;
import com.wf.ew.corpus.dao.QuestionDao;
import com.wf.ew.corpus.dao.UploadDao;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.QuestionService;
import com.wf.ew.task.dao.TaskDao;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.model.SubmitTask;
import com.wf.ew.task.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
public class QuestionTest {
    @Autowired
    QuestionService questionService;
    @Autowired
    ClassService classService;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    ClassDao classDao;
    @Autowired
    CodeDao codeDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    UploadDao uploadDao;
    @Test
    public void test(){
        PageResult<Question> question = questionService.getQuestion(1, 10, null, null);
        System.out.println(question);
    }
    @Test
    public void test1(){
        PageResult<Class> classPageResult = classService.queryClass(1, 10, null, null, "1");
        System.out.println(classPageResult);
    }
    @Test
    public void test3(){
        List<Students> classPageResult = classDao.queryStudentsByClass(null, null,"c-2014");
        System.out.println(classPageResult);
    }
    @Test
    public void test4(){
        List<Code> codes = codeDao.queryCode(null, null);
        System.out.println(codes);
    }
    @Test
    public void test5(){
        List<Task> tasks = taskDao.queryTask(null,null,"1");
        System.out.println(tasks);
    }
    @Test
    public void test6(){
        Map<String,Object> map=new HashMap<String,Object>();
        List<Condition> list=new ArrayList<Condition>();
        for(int i=0;i<3;i++){
            Condition condition=new Condition();
            condition.setCno("23");
            condition.setTime("21=52");
            list.add(condition);
        }
        map.put("condition",list);
        map.put("tid","12");
        int num = taskDao.shareTask( map);
        System.out.println(num);
    }
    @Test
    public void test7(){
        List<SubmitTask> submitTasks = taskDao.querySubmitedTask(null, null, "1");
        System.out.println(submitTasks);
    }
    @Test
    public void test8(){
        List<SubmitTask> submitTasks = taskDao.queryStudentSubmitTask(null, null, "c-2014","1");
        System.out.println(submitTasks);
    }
    @Test
    public void test9(){
        List<Question> questions = new ArrayList<Question>();
        Question question=new Question();
        question.setAnswer("");
        question.setQtype("ds");
        question.setQcontent("ds");
        questions.add(question);
        Question question1=new Question();
        question1.setAnswer("");
        question1.setQtype("ds");
        question1.setQcontent("ds");
        questions.add(question1);
        uploadDao.bulkAddQuestion(questions);
    }
}
