package com.wf.ew.grade.model;

public class ExportGrade {
    private String className;
    private String sno;
    private String sname;
    private String tid;
    private String grade;
    private String taskName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "ExportGrade{" +
                "className='" + className + '\'' +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", tid='" + tid + '\'' +
                ", grade='" + grade + '\'' +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}
