package com.wf.ew.competition.model;


public class Competition {
    private Long competitionId;
    private String competitionName;
    private String competitionDescription;
    private String competitionApplyBeginTime;
    private String competitionApplyEndTime;
    private String competitionBeginTime;
    private String competitionEndTime;
    private Integer competitionPlayersCount;
    private Integer isClose;
    private Integer isPublish;
    private Integer isCanDeclare;
    private Integer isJudge;
    private String competitionProblemIds;
    private String competitionContentRootPath;

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionDescription() {
        return competitionDescription;
    }

    public void setCompetitionDescription(String competitionDescription) {
        this.competitionDescription = competitionDescription;
    }

    public String getCompetitionApplyBeginTime() {
        return competitionApplyBeginTime;
    }

    public void setCompetitionApplyBeginTime(String competitionApplyBeginTime) {
        this.competitionApplyBeginTime = competitionApplyBeginTime;
    }

    public String getCompetitionApplyEndTime() {
        return competitionApplyEndTime;
    }

    public void setCompetitionApplyEndTime(String competitionApplyEndTime) {
        this.competitionApplyEndTime = competitionApplyEndTime;
    }

    public String getCompetitionBeginTime() {
        return competitionBeginTime;
    }

    public void setCompetitionBeginTime(String competitionBeginTime) {
        this.competitionBeginTime = competitionBeginTime;
    }

    public String getCompetitionEndTime() {
        return competitionEndTime;
    }

    public void setCompetitionEndTime(String competitionEndTime) {
        this.competitionEndTime = competitionEndTime;
    }

    public Integer getCompetitionPlayersCount() {
        return competitionPlayersCount;
    }

    public void setCompetitionPlayersCount(Integer competitionPlayersCount) {
        this.competitionPlayersCount = competitionPlayersCount;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsCanDeclare() {
        return isCanDeclare;
    }

    public void setIsCanDeclare(Integer isCanDeclare) {
        this.isCanDeclare = isCanDeclare;
    }

    public Integer getIsJudge() {
        return isJudge;
    }

    public void setIsJudge(Integer isJudge) {
        this.isJudge = isJudge;
    }

    public String getCompetitionProblemIds() {
        return competitionProblemIds;
    }

    public void setCompetitionProblemIds(String competitionProblemIds) {
        this.competitionProblemIds = competitionProblemIds;
    }

    public String getCompetitionContentRootPath() {
        return competitionContentRootPath;
    }

    public void setCompetitionContentRootPath(String competitionContentRootPath) {
        this.competitionContentRootPath = competitionContentRootPath;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "competitionId=" + competitionId +
                ", competitionName='" + competitionName + '\'' +
                ", competitionDescription='" + competitionDescription + '\'' +
                ", competitionApplyBeginTime=" + competitionApplyBeginTime +
                ", competitionApplyEndTime=" + competitionApplyEndTime +
                ", competitionBeginTime=" + competitionBeginTime +
                ", competitionEndTime=" + competitionEndTime +
                ", competitionPlayersCount=" + competitionPlayersCount +
                ", isClose=" + isClose +
                ", isPublish=" + isPublish +
                ", isCanDeclare=" + isCanDeclare +
                ", isJudge=" + isJudge +
                ", competitionProblemIds='" + competitionProblemIds + '\'' +
                ", competitionContentRootPath='" + competitionContentRootPath + '\'' +
                '}';
    }
}
