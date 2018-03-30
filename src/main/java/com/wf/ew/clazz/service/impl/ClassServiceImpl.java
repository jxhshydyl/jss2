package com.wf.ew.clazz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.clazz.dao.ClassDao;
import com.wf.ew.clazz.model.Class;
import com.wf.ew.clazz.model.Students;
import com.wf.ew.clazz.service.ClassService;
import com.wf.ew.core.PageResult;
import com.wf.ew.corpus.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classService")
public class ClassServiceImpl implements ClassService{
    @Autowired
    ClassDao classDao;

    @Override
    public PageResult<Class> queryClass(Integer pageNum, Integer pageSize, String searchKey, String searchValue, String tno){
        Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
        List<Class> clazz = classDao.queryClass(searchKey, searchValue,tno);
        System.out.println(clazz);
        PageResult<Class> result = new PageResult<Class>();
        result.setData(clazz);
        result.setCount(startPage.getTotal());
        return result;
   }
   @Override
    public PageResult<Students> queryStudentsByClass(Integer pageNum, Integer pageSize, String searchKey, String searchValue, String cno){
       Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
       List<Students> students = classDao.queryStudentsByClass(searchKey, searchValue, cno);
       System.out.println(students);
       PageResult<Students> result = new PageResult<Students>();
       result.setData(students);
       result.setCount(startPage.getTotal());
       return result;
   }
}
