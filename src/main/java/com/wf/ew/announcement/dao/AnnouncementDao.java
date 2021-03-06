package com.wf.ew.announcement.dao;

import com.wf.ew.announcement.model.Announcement;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AnnouncementDao {
    List<Announcement> queryAnnouncement(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue);
    int updateAnnouncementById(Announcement announcement);
    int deleteAnnouncement(Long announcementId);
    int addAnnouncement(Announcement announcement);
    int updatePublic(@Param("announcementId") Long announcementId,@Param("isPublish")Integer isPublish,@Param("time") String time);
}
