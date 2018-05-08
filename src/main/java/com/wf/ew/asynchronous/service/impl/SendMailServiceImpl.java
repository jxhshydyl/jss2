package com.wf.ew.asynchronous.service.impl;

import com.wf.ew.asynchronous.service.SendMailService;
import com.wf.ew.clazz.dao.ClassDao;
import com.wf.ew.clazz.model.Students;
import com.wf.ew.util.GetVerifyCode;
import com.wf.ew.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendMailServiceImpl implements SendMailService{
    @Autowired
    ClassDao classDao;

    /**
     * 发送班级作业通知
     * @param tno 教师编号
     * @param tid 作业id
     * @param cno 班级编号
     * @param time 作业开始时间
     * @param endTime 作业结束时间
     */
    @Override
    @Async
    public void sendTaskMail(String tno,String tid,String cno,String time,String endTime){
        String content="请查看班级作业，作业提交截止时间为："+endTime+"。（系统发送的提醒，无需回复）";
        SendEmail se = new SendEmail();
        List<Students> students = classDao.queryStudentsByClass(null, null, cno);
        if(students!=null){
            List<String> recipients=new ArrayList<String>();
            for(Students student : students){
                if(student.getEmail()!=null && student.getEmail().length()>0){
                    recipients.add(student.getEmail());
                }
            }
            try {
                se.send(recipients,"班级作业",content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送比赛帐号密码
     * @param email
     */
    public void sendCompetitionApplyAccount(String email){
        SendEmail se = new SendEmail();
        String account="";
        String password="";
        if(email != null && !"".equals(email)){
            List<String> recipients=new ArrayList<String>();
            recipients.add(email);
            try {
                se.send(recipients,"比赛帐号","比赛帐号："+account+";密码："+password+"");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送注册码
     * @param email
     */
    public void sendRegisterCode(String email){
        SendEmail se = new SendEmail();
        String code = GetVerifyCode.verifyCode();
        if(email != null && !"".equals(email)){
            List<String> recipients=new ArrayList<String>();
            recipients.add(email);
            try {
                se.send(recipients,"邮箱注册","注册验证码："+code+",为保障您的帐号安全，请勿泄漏！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
