package com.wf.ew.corpus.dao;

import com.wf.ew.corpus.model.Code;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CodeDao {
    List<Code> queryCode(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue,@Param("qid") Long qid);
    int deleteCode(Long qid);
}
