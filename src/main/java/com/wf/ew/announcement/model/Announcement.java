package com.wf.ew.announcement.model;

public class Announcement {
    private Long announcementId;
    private String announcementTitle;
    private String announcementIntroduction;
    private String announcementContent;
    private Integer announcementCreateManagerId;
    private String announcementCreateTime;
    private String announcementPublishTime;
    private String isPublish;
    private String roleId;

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public String getAnnouncementIntroduction() {
        return announcementIntroduction;
    }

    public void setAnnouncementIntroduction(String announcementIntroduction) {
        this.announcementIntroduction = announcementIntroduction;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public Integer getAnnouncementCreateManagerId() {
        return announcementCreateManagerId;
    }

    public void setAnnouncementCreateManagerId(Integer announcementCreateManagerId) {
        this.announcementCreateManagerId = announcementCreateManagerId;
    }

    public String getAnnouncementCreateTime() {
        return announcementCreateTime;
    }

    public void setAnnouncementCreateTime(String announcementCreateTime) {
        this.announcementCreateTime = announcementCreateTime;
    }

    public String getAnnouncementPublishTime() {
        return announcementPublishTime;
    }

    public void setAnnouncementPublishTime(String announcementPublishTime) {
        this.announcementPublishTime = announcementPublishTime;
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId=" + announcementId +
                ", announcementTitle='" + announcementTitle + '\'' +
                ", announcementIntroduction='" + announcementIntroduction + '\'' +
                ", announcementContent='" + announcementContent + '\'' +
                ", announcementCreateManagerId=" + announcementCreateManagerId +
                ", announcementCreateTime='" + announcementCreateTime + '\'' +
                ", announcementPublishTime='" + announcementPublishTime + '\'' +
                ", isPublish='" + isPublish + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
