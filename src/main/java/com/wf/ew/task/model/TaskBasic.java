package com.wf.ew.task.model;

import java.util.List;

public class TaskBasic {
    private Integer tid;
    private String taskName;
    private String score;
    private String cname;
    private String cno;
    private String chapter;
    private List<AutoMakePaperPara> list;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public List<AutoMakePaperPara> getList() {
        return list;
    }

    public void setList(List<AutoMakePaperPara> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TaskBasic{" +
                "tid=" + tid +
                ", TaskName='" + taskName + '\'' +
                ", score='" + score + '\'' +
                ", cname='" + cname + '\'' +
                ", cno='" + cno + '\'' +
                ", chapter='" + chapter + '\'' +
                ", list=" + list +
                '}';
    }
}
