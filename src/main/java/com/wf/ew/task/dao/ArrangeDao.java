package com.wf.ew.task.dao;

import com.wf.ew.task.model.Task;
import com.wf.ew.task.model.TaskDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArrangeDao {
    int queryQuestionCountByType(@Param("type") String type, @Param("cno") String cno, @Param("chapter") String[] chapter);
    int queryCodeCountByType(@Param("type") String type, @Param("cno") String cno,@Param("chapter") String[] chapter);
    int arrangeTask(Task task);
    List<Integer> queryCodeList(@Param("cno") String cno,@Param("chapter") String[] chapter,@Param("count") int count);
  //  int autoMakeCodePaper(@Param("tid") int tid,@Param("list")List<Integer> list,@Param("score")String score,@Param("type")int type);
    List<Integer> queryQuestionList(@Param("cno") String cno,@Param("chapter") String[] chapter,@Param("count") int count,@Param("type") String type);
    int autoMakeQuestionPaper(@Param("tid")int tid,@Param("list")List<Integer> list,@Param("score")String score,@Param("type")int type);
    int insertTdetail(@Param("newTid") int newTid,@Param("list")List<TaskDetail> list);
}
