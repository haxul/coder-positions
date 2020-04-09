package com.search.hh.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.search.hh.models.City;
import com.search.hh.models.response.headhunter.HeadhunterSearchResponse;

public interface PositionService {
    HeadhunterSearchResponse findPositions(String searchText, int areaId) throws JsonProcessingException;
    HeadhunterSearchResponse findPositions (String searchText) throws JsonProcessingException;
    int savePosition(City city, String position) throws JsonProcessingException;
}
