package com.wf.ew.task.service;

import com.wf.ew.task.model.AutoMakePaperPara;
import com.wf.ew.task.model.Condition;
import com.wf.ew.task.model.Task;

public interface ArrangeService {
    int queryQuestionCountByType(String type,String cno,String[] chapter);
    int arrangeTask(Task task, AutoMakePaperPara autoMakePaperPara, Condition condition);
    int judgeTaskName(String taskName);
}
