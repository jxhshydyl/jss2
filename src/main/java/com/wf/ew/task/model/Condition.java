package com.wf.ew.task.model;

public class Condition {
    private String cno;
    private String time;
    private String endTime;
    private String classNo;

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "cno='" + cno + '\'' +
                ", time='" + time + '\'' +
                ", endTime='" + endTime + '\'' +
                ", classNo='" + classNo + '\'' +
                '}';
    }
}
