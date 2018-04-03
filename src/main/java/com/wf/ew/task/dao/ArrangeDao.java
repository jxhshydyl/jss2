package com.wf.ew.task.dao;

import org.apache.ibatis.annotations.Param;

public interface ArrangeDao {
    int queryQuestionCountByType(@Param("type") String type, @Param("cno") String cno, @Param("chapter") String[] chapter);
}
