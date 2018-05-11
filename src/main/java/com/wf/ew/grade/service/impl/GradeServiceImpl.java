package com.wf.ew.grade.service.impl;

import com.wf.ew.clazz.dao.ClassDao;
import com.wf.ew.clazz.model.Students;
import com.wf.ew.core.utils.DateUtil;
import com.wf.ew.grade.dao.GradeDao;
import com.wf.ew.grade.model.ClassDeatilGrade;
import com.wf.ew.grade.model.ExportGrade;
import com.wf.ew.grade.model.QuestionStatistic;
import com.wf.ew.grade.model.TaskGradeStatistic;
import com.wf.ew.grade.service.GradeService;
import com.wf.ew.task.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    GradeDao gradeDao;
    @Autowired
    ClassDao classDao;
    /**
     * 通过班级和时间获取最近的作业的班级成绩
     * @param cno
     * @param time
     * @return
     */
    @Override
    public List<TaskGradeStatistic> taskGradeStatisticByCnoAndTime(String cno, String time, String tno){
        String year;
        if(time==null ||"".equals(time)){
            time=DateUtil.getCurrentDate();
            year = DateUtil.getCurrentYear();
        }else{
            year=time.split("-")[0];
        }
        Date date = DateUtil.parseMDate(time);
        String s = DateUtil.formatMDate(date);
        String semester = isSemester(s);
        String startTime=null;
        String endTime=null;
        //String year = DateUtil.getCurrentYear();
        String nextYear =String.valueOf (Integer.valueOf(year)+1);
        if("2".equals(semester)||"3".equals(semester)||"4".equals(semester)||"5".equals(semester)||"6".equals(semester)||"7".equals(semester)||"8".equals(semester)){
            startTime=year+"-"+"02";
            endTime=year+"-"+"09";
        }else{
            startTime=year+"-"+"09";
            endTime=nextYear+"-"+"02";
        }
        List<TaskGradeStatistic> list = gradeDao.taskGradeStatisticByCnoAndTime(cno,startTime,endTime,tno);
        return list;
    }
    /**
     * 通过课程名与发布时间确定某一次作业。查看某班级,某次作业的详细情况
     * @param cname
     * @param time
     * @param tno
     * @return
     */
    @Override
    public Map<String,Object> taskGradeDetailByCnameAndTime(String cname, String time,String cno, String tno) {
        List<ClassDeatilGrade> list = gradeDao.taskGradeDetailByCnameAndTime(cname,time,cno,tno);
        List<QuestionStatistic> questionStatistics = gradeDao.queryQuestionStatistic(cname,time,cno,tno);
        System.out.println(list);
        System.out.println(questionStatistics);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("list",list);
        map.put("questionStatistics",questionStatistics);
        return map;
    }
    /**
     *  通过课程查看某教师每个班级的成绩
     * @param cno
     * @param tno
     * @return
     */
    @Override
    public List<TaskGradeStatistic> courseGradeByCnoAndTime(String cno,String time, String tno) {
        String year;
        if(time==null ||"".equals(time)){
            time=DateUtil.getCurrentDate();
            year = DateUtil.getCurrentYear();
        }else{
            year=time.split("-")[0];
        }
        Date date = DateUtil.parseMDate(time);
        String s = DateUtil.formatMDate(date);
        String semester = isSemester(s);
        String startTime=null;
        String endTime=null;
        String nextYear =String.valueOf (Integer.valueOf(year)+1);
        if("2".equals(semester)||"3".equals(semester)||"4".equals(semester)||"5".equals(semester)||"6".equals(semester)||"7".equals(semester)||"8".equals(semester)){
            startTime=year+"-"+"02";
            endTime=year+"-"+"09";
        }else{
            startTime=year+"-"+"09";
            endTime=nextYear+"-"+"02";
        }
        List<TaskGradeStatistic> list = gradeDao.courseGradeByCnoAndTime(cno,startTime,endTime,tno);
        return list;
    }
    @Override
    public List<ClassDeatilGrade> courseDetailGradeByCnameAndTaskName(String cname, String taskName,String cno, String tno) {
        List<ClassDeatilGrade> list = gradeDao.courseDetailGradeByCnameAndTaskName(cname,taskName,cno,tno);
        return list;
    }


    //判断当前时间日期是否在最近一星期内(是 返回true，否 返回false)
    public static String isSemester(String now){
        String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
        for(int i=0;i<month.length;i++){
            if(month[i].equals(now)){
                return month[i];
            }
        }
        return null;
    }
    public InputStream exportGrade(String className, String tno){
        List<ExportGrade> exportGrades = gradeDao.exportGrade(className, tno);
        ArrayList<Object> lists=new ArrayList<>();
        ArrayList<ArrayList<Object>> result=new ArrayList<>();
        lists.add("学号");
        lists.add("姓名");
        List<String> strings = gradeDao.queryTaskName(className, tno);
        lists.addAll(strings);
        result.add(lists);
        List<Students> students = classDao.queryStudentsByClassName(className);
        for(Students student:students){
            ArrayList<Object> list=new ArrayList<>();
            list.add(student.getSno());
            list.add(student.getSname());
            for(String string:strings){//初始化list
                list.add("");
            }
            for(ExportGrade exportGrade:exportGrades){
                for(int i=0;i<lists.size();i++){
                    if(exportGrade.getSno().equals(student.getSno())){
                        if(((String)lists.get(i)).equals(exportGrade.getTaskName())){
                            list.add(i,exportGrade.getGrade());
                        }
                    }
                }
            }
            result.add(list);
        }
        ByteArrayInputStream byteArrayInputStream = ExcelUtil.writeExcelWeb(result);
        return byteArrayInputStream;
    }

/*    public List<QuestionStatistic> queryQuestionStatistic(){
        return gradeDao.queryQuestionStatistic();
    }*/
    public static void main(String[] args){
        Date date = DateUtil.parseMDate("2017-09-27");
        String s = DateUtil.formatMDate(date);
        System.out.println(s);
        String semester = isSemester(s);
        System.out.println(semester);
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
        System.out.println(startTime);
        System.out.println(endTime);
    }
}
