package com.wf.ew.task.dao;

import com.wf.ew.task.model.SubmitTask;
import com.wf.ew.task.model.Task;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;


public interface TaskDao {
    List<Task> queryTask(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue, @Param("tno") String tno);
    int deleteTask(Integer tid);
    int shareTask(Map map);
    List<SubmitTask> querySubmitedTask(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue, @Param("tno") String tno);
    List<SubmitTask> queryStudentSubmitTask(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue, @Param("cno") String cno,@Param("tid") String tid);
}
