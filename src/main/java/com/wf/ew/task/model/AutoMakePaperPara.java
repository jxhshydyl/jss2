package com.wf.ew.task.model;

public class AutoMakePaperPara {
    private String count;//组卷时题目的数量
    private String score;//组卷时题目的分数
    private String types;//组卷时题目的类型
    private String end_time;//组卷时选择的截止时间
    private String cno;//组卷时选择的班级编号

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    @Override
    public String toString() {
        return "AutoMakePaperPara{" +
                "count='" + count + '\'' +
                ", score='" + score + '\'' +
                ", types='" + types + '\'' +
                ", end_time='" + end_time + '\'' +
                ", cno='" + cno + '\'' +
                '}';
    }
}
