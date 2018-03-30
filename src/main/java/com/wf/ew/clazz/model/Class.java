package com.wf.ew.clazz.model;

public class Class {
    private String cno;//班级编号
    private String cname;//班级名称
    private int cnum;//班级人数
    private String academy;//所属学院
    private String major;//所属专业
    private String ctime_start;//开始年份
    private String ctime_end;//结束年份

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

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCtime_start() {
        return ctime_start;
    }

    public void setCtime_start(String ctime_start) {
        this.ctime_start = ctime_start;
    }

    public String getCtime_end() {
        return ctime_end;
    }

    public void setCtime_end(String ctime_end) {
        this.ctime_end = ctime_end;
    }

    @Override
    public String toString() {
        return "Class{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", cnum=" + cnum +
                ", academy='" + academy + '\'' +
                ", major='" + major + '\'' +
                ", ctime_start='" + ctime_start + '\'' +
                ", ctime_end='" + ctime_end + '\'' +
                '}';
    }
}
