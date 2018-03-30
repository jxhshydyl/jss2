package com.wf.ew.corpus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.core.PageResult;
import com.wf.ew.corpus.dao.QuestionDao;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.corpus.service.QuestionService;
import com.wf.ew.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;

    @Override
    public PageResult<Question> getQuestion(int pageNum, int pageSize, String searchKey, String searchValue) {
        Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionDao.queryQuestion( searchKey, searchValue);
        PageResult<Question> result = new PageResult<Question>();
        result.setData(questions);
        result.setCount(startPage.getTotal());
        return result;
    }
    @Override
    public int deleteQuestion(Long qid) {
        return questionDao.deleteQuestion(qid);
    }
}
