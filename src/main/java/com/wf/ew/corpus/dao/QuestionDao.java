package com.wf.ew.corpus.dao;

import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionDao {
    List<Question> queryQuestion(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue);
    int deleteQuestion(Long qid);
    int addQuestion(Question question);
    int addCode(Code code);
    int updateCode(Code code);
    int modifyQuestion(Question question);
}
