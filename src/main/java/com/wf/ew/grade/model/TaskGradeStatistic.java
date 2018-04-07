package com.wf.ew.grade.model;

public class TaskGradeStatistic {
    private String cno;//班级编号
    private String cname;//班级名称
    private String startTime;//作业发布时间
    private String endTime;//作业应提交时间
    private Integer tid;//作业id
    private String cid;//课程id
    private String courseName;//课程名称
    private String tchapter;
    private String taskName;//作业名称
    private Float totalGrade;//班级总成绩
    private Float classAvgGrade;//班级平均成绩
    private Float taskAvgGrade;//班级所交作业平均成绩

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

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTchapter() {
        return tchapter;
    }

    public void setTchapter(String tchapter) {
        this.tchapter = tchapter;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Float getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(Float totalGrade) {
        this.totalGrade = totalGrade;
    }

    public Float getClassAvgGrade() {
        return classAvgGrade;
    }

    public void setClassAvgGrade(Float classAvgGrade) {
        this.classAvgGrade = classAvgGrade;
    }

    public Float getTaskAvgGrade() {
        return taskAvgGrade;
    }

    public void setTaskAvgGrade(Float taskAvgGrade) {
        this.taskAvgGrade = taskAvgGrade;
    }

    @Override
    public String toString() {
        return "TaskGradeStatistic{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", tid=" + tid +
                ", cid='" + cid + '\'' +
                ", courseName='" + courseName + '\'' +
                ", tchapter='" + tchapter + '\'' +
                ", taskName='" + taskName + '\'' +
                ", totalGrade=" + totalGrade +
                ", classAvgGrade=" + classAvgGrade +
                ", taskAvgGrade=" + taskAvgGrade +
                '}';
    }
}
