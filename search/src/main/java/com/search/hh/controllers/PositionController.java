package com.search.hh.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.search.hh.dto.PositionDto;
import com.search.hh.models.City;
import com.search.hh.services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping(value = "/all/{city}/{position}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String saveNewPosition(@PathVariable City city, @PathVariable(value = "position") String searchPosition) throws JsonProcessingException {
        final int INIT_PAGE_IN_HEAD_HUNTER = 0;
        int updatedPosition = positionService.savePositions(city, searchPosition, INIT_PAGE_IN_HEAD_HUNTER);
        return "Updated " + updatedPosition;
    }

    @GetMapping("/{id}")
    public PositionDto findPositionById(@PathVariable int id) throws JsonProcessingException {
        return positionService.findPositionById(id);
    }
}
