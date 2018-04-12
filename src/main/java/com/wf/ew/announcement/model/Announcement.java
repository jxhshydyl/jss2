package com.wf.ew.announcement.model;

import java.util.Date;

public class Announcement {
    private Long announcementId;
    private String announcementTitle;
    private String announcementIntroduction;
    private String announcementContent;
    private Integer announcementCreateManagerId;
    private Date announcementCreateTime;
    private Date announcementPublishTime;
    private Integer isPublish;

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

    public Date getAnnouncementCreateTime() {
        return announcementCreateTime;
    }

    public void setAnnouncementCreateTime(Date announcementCreateTime) {
        this.announcementCreateTime = announcementCreateTime;
    }

    public Date getAnnouncementPublishTime() {
        return announcementPublishTime;
    }

    public void setAnnouncementPublishTime(Date announcementPublishTime) {
        this.announcementPublishTime = announcementPublishTime;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId=" + announcementId +
                ", announcementTitle='" + announcementTitle + '\'' +
                ", announcementIntroduction='" + announcementIntroduction + '\'' +
                ", announcementContent='" + announcementContent + '\'' +
                ", announcementCreateManagerId=" + announcementCreateManagerId +
                ", announcementCreateTime=" + announcementCreateTime +
                ", announcementPublishTime=" + announcementPublishTime +
                ", isPublish=" + isPublish +
                '}';
    }
}
