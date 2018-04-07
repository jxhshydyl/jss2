package com.wf.ew.task.service;

import java.util.List;
import java.util.Map;

public interface PageService {
    Map<String,Object> queryPageByTid(Integer tid);
    List<Object> querySimilarQuestion(String qtype, String cname,String qchapter, Integer tid);
    int replaceSilimarQuestion(Long newQid, Long oldQid, Integer tid);
    int deleteTaskQuestion(Long qid, Integer tid, String qtype);
    Map<String,Object> querySubmitTaskById(Long id);
    int saveSubmitTaskDetail(Long[] qids, float[] scores,  String[] types, Long id , float totalScore);
}
