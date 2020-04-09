package com.search.hh.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.hh.entities.PositionEntity;
import com.search.hh.models.City;
import com.search.hh.models.response.headhunter.HeadhunterSearchResponse;
import com.search.hh.models.response.headhunter.HhData;
import com.search.hh.repositories.PositionRepository;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    @Value("${headhunter.api.url}")
    private String BASE_URL;

    private final PositionRepository positionRepository;

    @Override
    public HeadhunterSearchResponse findPositions(String searchText, int areaId) throws JsonProcessingException {
        String responseBody = Unirest.get(BASE_URL + "vacancies/?area=" + areaId + "&text=" + searchText)
                .header("Accept", "application/json")
                .asString().getBody();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(responseBody, HeadhunterSearchResponse.class);
    }

    @Override
    public HeadhunterSearchResponse findPositions(String searchText) throws JsonProcessingException {
        return findPositions(searchText, City.SAMARA.getId());
    }

    // TO DO
    // modify hhData to postiion entity and save it
    @Override
    public int savePosition(City city, String position) throws JsonProcessingException {
        HeadhunterSearchResponse actualPositions = findPositions(position, city.getId());
        if (actualPositions.getItems().isEmpty()) return 0;
        List<PositionEntity> currentSavedPositions = positionRepository.findAll();
        actualPositions.getItems().stream().
                filter(p -> isExistInList(p, currentSavedPositions)).
                map(e -> {

                });
    }

    public boolean isExistInList(HhData hhData, List<PositionEntity> currentSavedPositions) {
        for (PositionEntity position : currentSavedPositions) {
            if (position.getId() == hhData.getId()) return true;
        }
        return false;
    }


}