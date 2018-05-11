package com.wf.ew.clazz.dao;

import com.wf.ew.clazz.model.Class;
import com.wf.ew.clazz.model.Students;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassDao {
    List<Class> queryClass(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue,@Param("tno") String tno);
    List<Students> queryStudentsByClass(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue, @Param("cno") String cno);
    List<Students> queryStudentsByClassName(@Param("className") String className);
}
