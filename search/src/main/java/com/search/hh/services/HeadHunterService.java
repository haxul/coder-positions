package com.search.hh.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.hh.models.response.headhunter.HeadhunterSearchResponse;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

@Service
public class HeadHunterService {
    private static final String BASE_URL = "https://api.hh.ru/";

    public HeadhunterSearchResponse findPositions (String searchText, int areaId) throws JsonProcessingException {
        String responseBody = Unirest.get( BASE_URL + "vacancies/?area=" + areaId +"&text=" + searchText)
                .header("Accept", "application/json")
                .header("Cache-Control", "no-cache")
                .header("cache-control", "no-cache")
                .asString().getBody();
        ObjectMapper mapper = new ObjectMapper();

        HeadhunterSearchResponse response = mapper.readValue(responseBody, HeadhunterSearchResponse.class);
        return response;
    }

}
