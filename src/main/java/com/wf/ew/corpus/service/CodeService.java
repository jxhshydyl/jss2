package com.wf.ew.corpus.service;


import com.wf.ew.core.PageResult;
import com.wf.ew.corpus.model.Code;

public interface CodeService {
    PageResult<Code> queryCode(int pageNum, int pageSize,String searchKey, String searchValue);
    int deleteCode(Long qid);
}
