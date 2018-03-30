package com.wf.ew.task.model;

public class Condition {
    private String cno;
    private String time;

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

    @Override
    public String toString() {
        return "Condition{" +
                "cno='" + cno + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
