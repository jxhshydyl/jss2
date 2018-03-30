package com.wf.ew.task.model;

import java.util.Objects;

public class Task {
    private int tid;//任务id
    private String taskName;//任务名称
    private String tappendixes;//任务附件
    private float tscore;//任务分数
    private String tno;//教师编号
    private String tname;//教师名称
    private String subtime;//创建时间
    private String ttype;//作业类型
    private String tstate;//作业状态
    private String cno;//课程编号
    private String cname;//课程名称
    private String tchapter;//所属章节

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTappendixes() {
        return tappendixes;
    }

    public void setTappendixes(String tappendixes) {
        this.tappendixes = tappendixes;
    }

    public float getTscore() {
        return tscore;
    }

    public void setTscore(float tscore) {
        this.tscore = tscore;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getSubtime() {
        return subtime;
    }

    public void setSubtime(String subtime) {
        this.subtime = subtime;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getTstate() {
        return tstate;
    }

    public void setTstate(String tstate) {
        this.tstate = tstate;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTchapter() {
        return tchapter;
    }

    public void setTchapter(String tchapter) {
        this.tchapter = tchapter;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tid=" + tid +
                ", task_name='" + taskName + '\'' +
                ", tappendixes='" + tappendixes + '\'' +
                ", tscore=" + tscore +
                ", tno='" + tno + '\'' +
                ", tname='" + tname + '\'' +
                ", subtime='" + subtime + '\'' +
                ", ttype='" + ttype + '\'' +
                ", tstate='" + tstate + '\'' +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", tchapter='" + tchapter + '\'' +
                '}';
    }
}
