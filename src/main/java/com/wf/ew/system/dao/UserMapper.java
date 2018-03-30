package com.wf.ew.system.dao;

import com.wf.ew.clazz.model.Course;
import com.wf.ew.system.model.User;
import com.wf.ew.system.model.UserExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    public List<User> selectUsers(@Param("status") Integer status, @Param("searchKey") String searchKey, @Param("searchValue") String searchValue);
    
    public User selectUserByAccount(String userAccount);

    /**
     * 查询教师所教班级
     */
    public List<Course> queryClassByTeacher(String Tno);

    /**
     * 查询教师所教专业
     */
    public List<Course> queryMajorByTeacher(String tno);

    /**
     * 查询教师所有课程
     */
    public List<Course> queryCourseByTeacher(String tno);
}