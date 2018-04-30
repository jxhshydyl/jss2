package com.wf.ew.corpus.model;

import java.util.Arrays;

public class Question {
    private Long qid;//问题id
    private String qtype;//问题类型
    private String qcontent;//问题内容
    private String qchoice;//问题选项
    private String[] choice;//
    private String qanswer;//问题答案
    private int qdegree;//问题难易程度
    private String cname;//课程名称
    private String qchapter;//课程章
    private String qparagraph;//课程节
    private String qdescribe;
    private Integer totalSubmitCount;//课程节
    private Integer totalRightCount;

    private Float score;//题目分数
    private String answer;//学生答案
    private Float grade;//学生得到的分数


    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }

    public String getQchoice() {
        return qchoice;
    }

    public void setQchoice(String qchoice) {
        this.qchoice = qchoice;
    }

    public String[] getChoice() {
        return choice;
    }

    public void setChoice(String[] choice) {
        this.choice = choice;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
    }

    public int getQdegree() {
        return qdegree;
    }

    public void setQdegree(int qdegree) {
        this.qdegree = qdegree;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getQchapter() {
        return qchapter;
    }

    public void setQchapter(String qchapter) {
        this.qchapter = qchapter;
    }

    public String getQparagraph() {
        return qparagraph;
    }

    public void setQparagraph(String qparagraph) {
        this.qparagraph = qparagraph;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getQdescribe() {
        return qdescribe;
    }

    public void setQdescribe(String qdescribe) {
        this.qdescribe = qdescribe;
    }

    public Integer getTotalSubmitCount() {
        return totalSubmitCount;
    }

    public void setTotalSubmitCount(Integer totalSubmitCount) {
        this.totalSubmitCount = totalSubmitCount;
    }

    public Integer getTotalRightCount() {
        return totalRightCount;
    }

    public void setTotalRightCount(Integer totalRightCount) {
        this.totalRightCount = totalRightCount;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qid=" + qid +
                ", qtype='" + qtype + '\'' +
                ", qcontent='" + qcontent + '\'' +
                ", qchoice='" + qchoice + '\'' +
                ", choice=" + Arrays.toString(choice) +
                ", qanswer='" + qanswer + '\'' +
                ", qdegree=" + qdegree +
                ", cname='" + cname + '\'' +
                ", cchapter='" + qchapter + '\'' +
                ", cparagraph='" + qparagraph + '\'' +
                ", score=" + score +
                ", answer='" + answer + '\'' +
                ", grade=" + grade +
                '}';
    }
}
