package com.wf.ew.competition.service.impl;

import com.wf.ew.competition.dao.CompetitionDao;
import com.wf.ew.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionServiceImpl implements CompetitionService{
    @Autowired
    CompetitionDao competitionDao;
}
