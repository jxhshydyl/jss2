package com.wf.ew.grade.service;

import com.wf.ew.grade.model.ClassDeatilGrade;
import com.wf.ew.grade.model.TaskGradeStatistic;

import java.util.List;

public interface GradeService {
     List<TaskGradeStatistic> taskGradeStatisticByCnoAndTime(String cno, String time, String tno);
     List<ClassDeatilGrade> taskGradeDetailByCnameAndTime(String cname, String time, String cno, String tno);
     List<TaskGradeStatistic> courseGradeByCnoAndTime(String cno,String time ,String tno) ;
     List<ClassDeatilGrade> courseDetailGradeByCnameAndTaskName(String cname, String taskName,String cno, String tno);
}
