package com.wf.ew.corpus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.core.PageResult;
import com.wf.ew.corpus.dao.CodeDao;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService{
    @Autowired
    CodeDao codeDao;

    public PageResult<Code> queryCode(int pageNum, int pageSize,String searchKey, String searchValue){
        Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
        List<Code> codes = codeDao.queryCode(searchKey, searchValue);
        PageResult<Code> result = new PageResult<Code>();
        result.setData(codes);
        result.setCount(startPage.getTotal());
        return result;
    }
    public int deleteCode(Long qid){
        return codeDao.deleteCode(qid);
    }
}
