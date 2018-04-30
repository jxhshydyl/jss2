package com.wf.ew.corpus.dao;

import com.wf.ew.corpus.model.MyRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SubmitRecordDao {
	List<MyRecord> querySubmitCount(@Param("qid") Long qid);
}
