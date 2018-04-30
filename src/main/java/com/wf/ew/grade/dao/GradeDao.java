package com.wf.ew.grade.dao;

import com.wf.ew.grade.model.ClassDeatilGrade;
import com.wf.ew.grade.model.TaskGradeStatistic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeDao {
    List<TaskGradeStatistic> courseGradeByCnoAndTime(@Param("cno") String cno,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("tno") String tno);
    List<ClassDeatilGrade> courseDetailGradeByCnameAndTaskName(@Param("cname")String cname, @Param("taskName")String taskName,@Param("cno") String cno,@Param("tno") String tno);
    List<TaskGradeStatistic> taskGradeStatisticByCnoAndTime(@Param("cno")String cno,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("tno")String tno);
    List<ClassDeatilGrade> taskGradeDetailByCnameAndTime(@Param("cname")String cname,@Param("time") String time,@Param("cno") String cno,@Param("tno")String tno);
}
