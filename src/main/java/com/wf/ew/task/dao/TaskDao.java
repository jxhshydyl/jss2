package com.wf.ew.task.dao;

import com.wf.ew.task.model.SubmitTask;
import com.wf.ew.task.model.Task;
import com.wf.ew.task.model.TaskDetail;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;


public interface TaskDao {
    List<Task> queryTask(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue, @Param("tno") String tno);
    int deleteTask(Integer tid);
    int shareTask(Map map);
    List<SubmitTask> querySubmitedTask(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue, @Param("tno") String tno);
    List<SubmitTask> queryStudentSubmitTask(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue, @Param("cno") String cno,@Param("tid") String tid);
    int updateState(@Param("tid")String tid,@Param("state") String state);
    Integer judgeTaskName(@Param("taskName") String taskName,@Param("startTime") String startTime,@Param("endTime") String endTime);
    Task queryTaskByTid(@Param("tid") Integer tid);
    List<TaskDetail> queryTaskDetailByTid(@Param("tid") Integer tid);
}
