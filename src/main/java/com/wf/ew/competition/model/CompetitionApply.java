package com.wf.ew.competition.model;

public class CompetitionApply {
    private String competitionApplicationId;
    private String email;
    private String phone;
    private Integer applicationPeopleCount;
    private String applicationSummary;
    private String applicationContent;
    private Integer competitionId;
    private Integer level;
    private Integer isHaveSendEmail;
    private Integer isHaveHandle;
    private Integer competitionAccountId;

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

    public Integer getCompetitionAccountId() {
        return competitionAccountId;
    }

    public void setCompetitionAccountId(Integer competitionAccountId) {
        this.competitionAccountId = competitionAccountId;
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
