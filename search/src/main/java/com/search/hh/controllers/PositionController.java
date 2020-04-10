package com.search.hh.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    public String saveNewPosition(@PathVariable City city, @PathVariable String position) throws JsonProcessingException {
        int updatedPosition = positionService.savePosition(city, position);
        return "Updated " + updatedPosition;
    }
}
