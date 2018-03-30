package com.wf.ew.corpus.service;

import com.wf.ew.corpus.model.AddQuestion;
import com.wf.ew.corpus.model.Question;

public interface AddQuestionService {
    int addQuestion(Question question, AddQuestion addQuestion);
}
