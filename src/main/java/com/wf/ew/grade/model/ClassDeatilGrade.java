package com.wf.ew.grade.model;

public class ClassDeatilGrade {
    private String sno;//学号
    private String sname;//姓名
    private String sclass;//班级
    private Integer tid;//作业id
    private String taskName;//作业名称
    private Float grade;//成绩
    private String cno;//课程号
    private String cname;//课程名

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

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

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
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

    @Override
    public String toString() {
        return "ClassDeatilGrade{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", sclass='" + sclass + '\'' +
                ", tid=" + tid +
                ", taskName='" + taskName + '\'' +
                ", grade=" + grade +
                ", cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
