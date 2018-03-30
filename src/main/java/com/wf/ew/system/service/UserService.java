package com.wf.ew.system.service;

import com.wf.ew.clazz.model.Class;
import com.wf.ew.clazz.model.Course;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.system.model.User;

import java.util.List;

/**
 * UserService
 * 
 * @author wangfan
 * @date 2017-3-24 下午4:12:31
 */
public interface UserService {

	/**
	 * 查询所有用户
	 */
	public PageResult<User> getUsers(int pageNum, int pageSize, Integer status, String searchKey, String searchValue);

	/**
	 * 根据账号查询用户
	 */
	public User getUserByAccount(String userAccount);

	/**
	 * 根据id查询用户
	 */
	public User getUserById(String userId);

	/**
	 * 添加用户
	 */
	public boolean addUser(User user) throws BusinessException;

	/**
	 * 修改用户
	 */
	public boolean updateUser(User user);

	/**
	 * 修改用户状态
	 */
	public boolean updateUserStatus(String userId, int status) throws ParameterException;

	/**
	 * 修改密码
	 */
	public boolean updateUserPsw(String userId, String newPassword);
	
	/**
	 * 删除用户
	 */
	public boolean deleteUser(String userId) throws BusinessException;

	/**
	 * 查询教师所教班级
	 */
 	List<Course> queryClassByTeacher(String Tno);
	/**
	 * 查询教师所教专业
	 */
	List<Course> queryMajorByTeacher(String tno);
	/**
	 * 查询教师所有课程
	 */
	List<Course> queryCourseByTeacher(String tno);
}
