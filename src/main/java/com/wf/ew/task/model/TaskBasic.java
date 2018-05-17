package com.wf.ew.task.model;

import java.util.List;

public class TaskBasic {
    private Integer tid;
    private String taskName;
    private String tstate;
    private String score;
    private String cname;
    private String cno;
    private String chapter;
    private String tappendixes;
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

    public String getTappendixes() {
        return tappendixes;
    }

    public void setTappendixes(String tappendixes) {
        this.tappendixes = tappendixes;
    }

    public String getTstate() {
        return tstate;
    }

    public void setTstate(String tstate) {
        this.tstate = tstate;
    }

    @Override
    public String toString() {
        return "TaskBasic{" +
                "tid=" + tid +
                ", taskName='" + taskName + '\'' +
                ", tstate='" + tstate + '\'' +
                ", score='" + score + '\'' +
                ", cname='" + cname + '\'' +
                ", cno='" + cno + '\'' +
                ", chapter='" + chapter + '\'' +
                ", tappendixes='" + tappendixes + '\'' +
                ", list=" + list +
                '}';
    }
}
