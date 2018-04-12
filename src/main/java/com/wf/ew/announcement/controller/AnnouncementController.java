package com.wf.ew.announcement.controller;

import com.wf.ew.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/announcement")
@Controller
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;
}
