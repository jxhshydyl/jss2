package com.wf.ew.announcement.controller;

import com.wf.ew.announcement.model.Announcement;
import com.wf.ew.announcement.service.AnnouncementService;
import com.wf.ew.competition.model.CompetitionApply;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/announcement")
@Controller
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    @RequestMapping("/queryAnnouncement")
    @ResponseBody
    public PageResult<Announcement> queryAnnouncement(Integer page, Integer limit, String searchKey, String searchValue){
        if(page == null) {
            page = 0;
            limit = 0;
        }
        PageResult<Announcement> pageResult = announcementService.queryAnnouncement(page, limit, searchKey, searchValue);
        System.out.println(pageResult);
        return pageResult;
    }
    @RequestMapping("/addAnnouncement")
    @ResponseBody
    public ResultMap addAnnouncement(Announcement announcement){
        System.out.println(announcement);
        int num=announcementService.addAnnouncement(announcement);
        if(num==1){
            return ResultMap.ok(200,"增加成功！");
        }
        return ResultMap.error(0,"增加失败！");
    }

    @RequestMapping("/updateAnnouncementById")
    @ResponseBody
    public ResultMap updateAnnouncementById(Announcement announcement){
        int num=announcementService.updateAnnouncementById(announcement);
        if(num==1){
            return ResultMap.ok(200,"更新成功！");
        }
        return ResultMap.error(0,"更新失败！");
    }

    @DeleteMapping("/deleteAnnouncement/{announcementId}")
    @ResponseBody
    public ResultMap deleteAnnouncement(@PathVariable("announcementId") Long announcementId){
        int num = announcementService.deleteAnnouncement(announcementId);
        if(num==1){
            return ResultMap.ok(200,"删除成功！");
        }
        return ResultMap.error(0,"删除失败！");
    }
    @RequestMapping("/updatePublic")
    @ResponseBody
    public ResultMap updatePublic(Long announcementId,Integer isPublish){
        int num = announcementService.updatePublic(announcementId,isPublish);
        if(num==1){
            return ResultMap.ok(200,"发布成功！");
        }
        return ResultMap.error(0,"已取消发布！");
    }
}
