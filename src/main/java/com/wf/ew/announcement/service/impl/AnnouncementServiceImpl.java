package com.wf.ew.announcement.service.impl;

import com.wf.ew.announcement.dao.AnnouncementDao;
import com.wf.ew.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    AnnouncementDao announcementDao;
}
