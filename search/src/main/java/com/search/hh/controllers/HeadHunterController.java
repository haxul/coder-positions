package com.search.hh.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.search.hh.models.response.headhunter.HeadhunterSearchResponse;
import com.search.hh.services.HeadHunterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class HeadHunterController {

    private final HeadHunterService headHunterService;

    public HeadHunterController(HeadHunterService headHunterService) {
        this.headHunterService = headHunterService;
    }

    @GetMapping
    public HeadhunterSearchResponse searchPositions() throws JsonProcessingException {
        return headHunterService.findPositions("java", 78);
    }
}
