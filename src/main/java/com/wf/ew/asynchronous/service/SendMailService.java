package com.wf.ew.asynchronous.service;

import java.util.List;

public interface SendMailService {
    void sendTaskMail(String tno,String tid,String cno,String time,String endTime);
    void sendCompetitionApplyAccount(String email);
}
