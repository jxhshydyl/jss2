package com.wf.ew.competition.model;

public class CompetitionApply {
    private String competitionApplicationId;
    private String email;
    private String phone;
    private Integer applicationPeopleCount;
    private String applicationSummary;
    private String applicationContent;
    private Integer competitionId;
    private String competitionName;
    private Integer level;
    private Integer isHaveSendEmail;
    private Integer isHaveHandle;
    private String competitionAccountId;
    private String isSuspendCompetition;

    public String getCompetitionApplicationId() {
        return competitionApplicationId;
    }

    public void setCompetitionApplicationId(String competitionApplicationId) {
        this.competitionApplicationId = competitionApplicationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getApplicationPeopleCount() {
        return applicationPeopleCount;
    }

    public void setApplicationPeopleCount(Integer applicationPeopleCount) {
        this.applicationPeopleCount = applicationPeopleCount;
    }

    public String getApplicationSummary() {
        return applicationSummary;
    }

    public void setApplicationSummary(String applicationSummary) {
        this.applicationSummary = applicationSummary;
    }

    public String getApplicationContent() {
        return applicationContent;
    }

    public void setApplicationContent(String applicationContent) {
        this.applicationContent = applicationContent;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsHaveSendEmail() {
        return isHaveSendEmail;
    }

    public void setIsHaveSendEmail(Integer isHaveSendEmail) {
        this.isHaveSendEmail = isHaveSendEmail;
    }

    public Integer getIsHaveHandle() {
        return isHaveHandle;
    }

    public void setIsHaveHandle(Integer isHaveHandle) {
        this.isHaveHandle = isHaveHandle;
    }

    public String getCompetitionAccountId() {
        return competitionAccountId;
    }

    public void setCompetitionAccountId(String competitionAccountId) {
        this.competitionAccountId = competitionAccountId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getIsSuspendCompetition() {
        return isSuspendCompetition;
    }

    public void setIsSuspendCompetition(String isSuspendCompetition) {
        this.isSuspendCompetition = isSuspendCompetition;
    }

    @Override
    public String toString() {
        return "CompetitionApply{" +
                "competitionApplicationId='" + competitionApplicationId + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", applicationPeopleCount=" + applicationPeopleCount +
                ", applicationSummary='" + applicationSummary + '\'' +
                ", applicationContent='" + applicationContent + '\'' +
                ", competitionId=" + competitionId +
                ", level=" + level +
                ", isHaveSendEmail=" + isHaveSendEmail +
                ", isHaveHandle=" + isHaveHandle +
                ", competitionAccountId=" + competitionAccountId +
                '}';
    }
}
