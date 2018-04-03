package com.wf.ew.task.dao;

import com.wf.ew.task.model.Task;
import org.apache.ibatis.annotations.Param;

public interface ArrangeDao {
    int queryQuestionCountByType(@Param("type") String type, @Param("cno") String cno, @Param("chapter") String[] chapter);
    int queryCodeCountByType(@Param("type") String type, @Param("cno") String cno,@Param("chapter") String[] chapter);
    int arrangeTask(Task task);
    int autoMakeCodePaper(@Param("tid")int tid,@Param("score")String score,@Param("count")String count, @Param("type")String type);
    int autoMakeQuestionPaper(@Param("tid")int tid,@Param("score")String score,@Param("count")String count,@Param("type")String type);
}
