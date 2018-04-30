package com.wf.ew.corpus.service;

import com.wf.ew.corpus.model.AddQuestion;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;

import java.math.BigInteger;
import java.util.Map;

public interface AddQuestionService {
    int addQuestion(Question question, AddQuestion addQuestion);
    int addCode(Code code);
    Map<String,Object> evaluateStatistics(Long qid);
}
