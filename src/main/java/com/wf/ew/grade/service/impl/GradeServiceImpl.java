package com.wf.ew.grade.service.impl;

import com.wf.ew.grade.dao.GradeDao;
import com.wf.ew.grade.model.ClassDeatilGrade;
import com.wf.ew.grade.model.TaskGradeStatistic;
import com.wf.ew.grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        List<TaskGradeStatistic> list = gradeDao.taskGradeStatisticByCnoAndTime(cno,time,tno);
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
        List<TaskGradeStatistic> list = gradeDao.courseGradeByCnoAndTime(cno,time,tno);
        return list;
    }
    @Override
    public List<ClassDeatilGrade> courseDetailGradeByCnameAndTaskName(String cname, String taskName,String cno, String tno) {
        List<ClassDeatilGrade> list = gradeDao.courseDetailGradeByCnameAndTaskName(cname,taskName,cno,tno);
        return list;
    }
}
