package com.wf.ew.corpus.service;


import com.wf.ew.core.PageResult;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;

import java.util.List;

public interface QuestionService {
    /**
     *得到所有问题
     */
    PageResult<Question> getQuestion(int pageNum, int pageSize,String searchKey, String searchValue);
    int deleteQuestion(Long qid);
}
