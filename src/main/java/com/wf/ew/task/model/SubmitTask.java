package com.wf.ew.task.model;

public class SubmitTask {
    /**
     * 提交的班级作业信息
     */
    private Long id;
    private String classNo;//班级编号
    private String className;//班级名称
    private String courseName;//所属课程
    private String tid;//作业编号
    private String taskName;//作业名称
    private String tchapter;//所属章节
    private String endTime;//截止时间
    private String unsubmittedNumber;//提交人数
    /**
     * 提交的班级中各学生的信息
     */
    private String sno;//学号
    private String sname;//姓名
    private String submitTime;//提交时间
    private String status;//状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTchapter() {
        return tchapter;
    }

    public void setTchapter(String tchapter) {
        this.tchapter = tchapter;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUnsubmittedNumber() {
        return unsubmittedNumber;
    }

    public void setUnsubmittedNumber(String unsubmittedNumber) {
        this.unsubmittedNumber = unsubmittedNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "SubmitTask{" +
                "classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", courseName='" + courseName + '\'' +
                ", tid='" + tid + '\'' +
                ", taskName='" + taskName + '\'' +
                ", tchapter='" + tchapter + '\'' +
                ", endTime='" + endTime + '\'' +
                ", unsubmittedNumber='" + unsubmittedNumber + '\'' +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", submitTime='" + submitTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
