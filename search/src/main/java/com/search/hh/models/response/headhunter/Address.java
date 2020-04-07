package com.search.hh.models.response.headhunter;

import lombok.Data;

import java.util.List;

@Data
public class Address {
    private String city;
    private String street;
    private String building;
    private double lat;
    private double lng;
    private String raw;
    private String id;
    private Metro metro;
    private List<Metro> metro_stations;
    private String description;
}
