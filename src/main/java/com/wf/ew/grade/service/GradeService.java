package com.wf.ew.grade.service;

import com.wf.ew.grade.model.ClassDeatilGrade;
import com.wf.ew.grade.model.TaskGradeStatistic;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface GradeService {
     List<TaskGradeStatistic> taskGradeStatisticByCnoAndTime(String cno, String time, String tno);
     Map<String,Object> taskGradeDetailByCnameAndTime(String cname, String time, String cno, String tno);
     List<TaskGradeStatistic> courseGradeByCnoAndTime(String cno,String time ,String tno) ;
     List<ClassDeatilGrade> courseDetailGradeByCnameAndTaskName(String cname, String taskName,String cno, String tno);
     InputStream exportGrade(String className, String tno);
}
