package com.wf.ew.competition.controller;

import com.wf.ew.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/competition")
@Controller
public class CompetitionController {
    @Autowired
    CompetitionService competitionService;
}
