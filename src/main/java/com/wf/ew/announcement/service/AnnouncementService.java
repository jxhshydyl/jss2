package com.wf.ew.announcement.service;

import com.wf.ew.announcement.model.Announcement;
import com.wf.ew.core.PageResult;

public interface AnnouncementService {
    PageResult<Announcement> queryAnnouncement(Integer page, Integer limit, String searchKey, String searchValue);
    int updateAnnouncementById(Announcement announcement);
    int deleteAnnouncement(Long announcementId);
}
