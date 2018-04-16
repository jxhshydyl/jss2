package com.wf.ew.announcement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.announcement.dao.AnnouncementDao;
import com.wf.ew.announcement.model.Announcement;
import com.wf.ew.announcement.service.AnnouncementService;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    AnnouncementDao announcementDao;

    @Override
    public PageResult<Announcement> queryAnnouncement(Integer page, Integer limit, String searchKey, String searchValue){
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<Announcement> competitions = announcementDao.queryAnnouncement(searchKey, searchValue);
        System.out.println(competitions);
        PageResult<Announcement> result = new PageResult<Announcement>();
        result.setData(competitions);
        result.setCount(startPage.getTotal());
        return result;
    }

    @Override
    public int updateAnnouncementById(Announcement announcement){
        int num=announcementDao.updateAnnouncementById(announcement);
        return num;
    }
    @Override
    public int deleteAnnouncement(Long announcementId){
        int num=announcementDao.deleteAnnouncement(announcementId);
        return num;
    }
    @Override
    public int addAnnouncement(Announcement announcement){
        announcement.setAnnouncementCreateTime(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm"));
        if("on".equals(announcement.getIsPublish())){
            announcement.setIsPublish("1");
            announcement.setAnnouncementPublishTime(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm"));
        }else{
            announcement.setIsPublish("0");
        }
        return announcementDao.addAnnouncement(announcement);
    }
    public int updatePublic(Long announcementId,Integer isPublish){

        return announcementDao.updatePublic(announcementId,isPublish,DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm"));
    }
}
