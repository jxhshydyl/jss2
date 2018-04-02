package com.wf.ew.corpus.dao;

import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;

import java.util.List;

public interface UploadDao {
    int bulkAddCode(List<Code> codes);
    int bulkAddQuestion(List<Question> questions);
}
