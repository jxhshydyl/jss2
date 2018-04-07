package com.wf.ew.task.dao;

import com.wf.ew.clazz.model.Students;
import com.wf.ew.corpus.model.Code;
import com.wf.ew.corpus.model.Question;
import com.wf.ew.task.model.TaskBasic;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PageDao {
    List<Question> queryPageQuestionByTid(Integer tid);
    List<Code> queryPageCodeByTid(Integer tid);
    TaskBasic queryBasicPageQuestionByTid(Integer tid);
    TaskBasic queryBasicPageCodeByTid(Integer tid);
    List<Object> querySililarCode(@Param("type") String qtype,@Param("cname") String cname, @Param("qchapter")String qchapter, @Param("tid")Integer tid);
    List<Object> querySimilarQuestion(@Param("type") String qtype,@Param("cname") String cname, @Param("qchapter")String qchapter, @Param("tid")Integer tid);
    int replaceSilimarQuestion(@Param("newQid")Long newQid, @Param("oldQid")Long oldQid, @Param("tid")Integer tid);
    int deleteTaskQuestion(@Param("qid")Long qid, @Param("tid")Integer tid, @Param("qtype")int qtype);
    int updateTotalScore(Integer tid);


    List<Question> querySubmitTaskQuestionDetail(Long id);
    List<Code>  querySubmitTaskCodeDetail(Long id);
    Students queryStudentByStaskId(Long id);

    int saveSubmitTaskScore(@Param("id")Long id,@Param("totalScore")float totalScore);
    int saveSubmitTaskQuestionDetail(@Param("id")Long id,@Param("qids")Long qids,@Param("scores")float scores);
    int saveSubmitTaskCodeDetail(@Param("id")Long id,@Param("qids")Long qids,@Param("scores")float scores);
}
