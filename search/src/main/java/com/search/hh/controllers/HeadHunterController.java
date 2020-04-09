package com.search.hh.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.search.hh.models.City;
import com.search.hh.models.response.headhunter.HeadhunterSearchResponse;
import com.search.hh.services.PositionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class HeadHunterController {

    private final PositionService positionServiceImpl;

    public HeadHunterController(PositionService positionServiceImpl) {
        this.positionServiceImpl = positionServiceImpl;
    }

    @GetMapping
    public HeadhunterSearchResponse searchPositions(@RequestParam(required = false) City city, @RequestParam String searchedText) throws JsonProcessingException {
        return city == null ? positionServiceImpl.findPositions(searchedText) : positionServiceImpl.findPositions(searchedText, city.getId());
    }
}
