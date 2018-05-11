package com.wf.ew.task.model;

public class TaskDetail {
    private String tid;
    private String type;
    private String qid;
    private String score;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "TaskDetail{" +
                "tid='" + tid + '\'' +
                ", type='" + type + '\'' +
                ", qid='" + qid + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
