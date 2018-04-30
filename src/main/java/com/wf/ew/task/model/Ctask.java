package com.wf.ew.task.model;

public class Ctask {
    private Long Tid;
    private String Cno;
    private String startTime;
    private String endTime;

    public Long getTid() {
        return Tid;
    }

    public void setTid(Long tid) {
        Tid = tid;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Ctask{" +
                "Tid=" + Tid +
                ", Cno='" + Cno + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
