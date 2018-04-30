package com.wf.ew.grade.service.impl;

import com.wf.ew.core.utils.DateUtil;
import com.wf.ew.grade.dao.GradeDao;
import com.wf.ew.grade.model.ClassDeatilGrade;
import com.wf.ew.grade.model.TaskGradeStatistic;
import com.wf.ew.grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    GradeDao gradeDao;
    /**
     * 通过班级和时间获取最近的作业的班级成绩
     * @param cno
     * @param time
     * @return
     */
    @Override
    public List<TaskGradeStatistic> taskGradeStatisticByCnoAndTime(String cno, String time, String tno){
        if(time==null ||"".equals(time)){
            time=DateUtil.getCurrentDate();
        }
        Date date = DateUtil.parseMDate(time);
        String s = DateUtil.formatMDate(date);
        String semester = isSemester(s);
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
    public List<ClassDeatilGrade> taskGradeDetailByCnameAndTime(String cname, String time,String cno, String tno) {
        List<ClassDeatilGrade> list = gradeDao.taskGradeDetailByCnameAndTime(cname,time,cno,tno);
        return list;
    }
    /**
     *  通过课程查看某教师每个班级的成绩
     * @param cno
     * @param tno
     * @return
     */
    @Override
    public List<TaskGradeStatistic> courseGradeByCnoAndTime(String cno,String time, String tno) {
        if(time==null ||"".equals(time)){
            time=DateUtil.getCurrentDate();
        }
        Date date = DateUtil.parseMDate(time);
        String s = DateUtil.formatMDate(date);
        String semester = isSemester(s);
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

    public static void main(String[] args){
        Date date = DateUtil.parseMDate("2018-09-27");
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
