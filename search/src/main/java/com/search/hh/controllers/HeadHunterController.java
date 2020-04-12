package com.search.hh.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.search.hh.models.City;
import com.search.hh.models.response.headhunter.HeadhunterSearchResponse;
import com.search.hh.services.PositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class HeadHunterController {

    private final PositionService positionServiceImpl;

    public HeadHunterController(PositionService positionServiceImpl) {
        this.positionServiceImpl = positionServiceImpl;
    }

    @GetMapping
    public HeadhunterSearchResponse searchPositions(@RequestParam(required = false) City city, @RequestParam String searchedText, @RequestParam(required = false) Integer page) throws JsonProcessingException {
        int curPage = (page == null) ? 0 : page;
        return city == null ? positionServiceImpl.findPositionsInHH(searchedText, curPage) :
                positionServiceImpl.findPositionsInHH(searchedText, city.getId(), curPage);
    }
}
