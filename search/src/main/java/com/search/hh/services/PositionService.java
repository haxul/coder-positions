package com.search.hh.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.search.hh.dto.PositionDto;
import com.search.hh.models.City;
import com.search.hh.models.response.headhunter.HeadhunterSearchResponse;

public interface PositionService {
    HeadhunterSearchResponse findPositionsInHH(String searchText, int areaId, int page) throws JsonProcessingException;
    HeadhunterSearchResponse findPositionsInHH(String searchText, int page) throws JsonProcessingException;
    int savePositions(City city, String position, int page) throws JsonProcessingException;
    PositionDto findPositionById(int id) throws JsonProcessingException;
}
