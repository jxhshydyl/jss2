package com.wf.ew.system.service.impl;

import java.util.Date;
import java.util.List;

import com.wf.ew.clazz.model.Course;
import com.wf.ew.util.md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangfan.endecrypt.utils.EndecryptUtils;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.core.utils.UUIDUtil;
import com.wf.ew.system.dao.UserMapper;
import com.wf.ew.system.model.User;
import com.wf.ew.system.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageResult<User> getUsers(int pageNum, int pageSize, Integer status, String searchKey, String searchValue) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<User> users = userMapper.selectUsers(status, searchKey, searchValue);
		PageResult<User> result = new PageResult<User>();
		result.setData(users);
		result.setCount(startPage.getTotal());
		System.out.println(result);
		return result;
	}

	@Override
	public boolean addUser(User user) throws BusinessException {
		if(getUserByAccount(user.getUserAccount())!=null){
			throw new BusinessException("账号已经存在");
		}
		String decryptMd5 = md5Util.md5jdk(user.getUserPassword());
		user.setUserId(UUIDUtil.randomUUID32());
		user.setUserPassword(decryptMd5);
		user.setUserStatus(0);
		user.setCreateTime(new Date());
		return userMapper.insertSelective(user)>0;
	}

	@Override
	public boolean updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public boolean updateUserStatus(String userId, int status) throws ParameterException {
		if(status!=0&&status!=1){
			throw new ParameterException();
		}
		User user = new User();
		user.setUserId(userId);
		user.setUserStatus(status);
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public User getUserByAccount(String userAccount) {
		return userMapper.selectUserByAccount(userAccount);
	}

	@Override
	public boolean updateUserPsw(String userId, String password) {
		User user = new User();
		user.setUserId(userId);
		String decryptMd5 = EndecryptUtils.encrytMd5(password, "null", 3);
		user.setUserPassword(decryptMd5);
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public User getUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public boolean deleteUser(String userId) throws BusinessException {
		try{
			return userMapper.deleteByPrimaryKey(userId)>0;
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("用户已被关联");
		}
	}
	/**
	 * 查询教师所教班级
	 */
	public List<Course> queryClassByTeacher(String tno){
		List<Course> courses = userMapper.queryClassByTeacher(tno);
		return courses;
	}

	/**
	 * 查询教师所教专业
	 */
	public List<Course> queryMajorByTeacher(String tno){
		List<Course> courses = userMapper.queryMajorByTeacher(tno);
		return courses;
	}

	/**
	 * 查询教师所有课程
	 */
	public List<Course> queryCourseByTeacher(String tno){
		List<Course> courses = userMapper.queryCourseByTeacher(tno);
		return courses;
	}
}
