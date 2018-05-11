package com.wf.ew.grade.model;

public class QuestionStatistic {
    private String tid;
    private String qid;
    private String score;
    private String type;
    private String qcontent;
    private String qanswer;
    private Integer totalCount;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "QuestionStatistic{" +
                "tid='" + tid + '\'' +
                ", qid='" + qid + '\'' +
                ", score='" + score + '\'' +
                ", type='" + type + '\'' +
                ", qcontent='" + qcontent + '\'' +
                ", qanswer='" + qanswer + '\'' +
                ", totalCount=" + totalCount +
                '}';
    }
}
