package com.wf.ew.task.service.impl;

import com.wf.ew.task.dao.ArrangeDao;
import com.wf.ew.task.service.ArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrangeServiceImpl implements ArrangeService{
    @Autowired
    ArrangeDao arrangeDao;
    @Override
    public int queryQuestionCountByType(String type,String cno,String[] chapter){
        return arrangeDao.queryQuestionCountByType(type,cno,chapter);
    }
}
