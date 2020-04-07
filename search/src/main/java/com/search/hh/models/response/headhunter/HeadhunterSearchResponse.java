package com.search.hh.models.response.headhunter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class HeadhunterSearchResponse {
    private List<HhData> items;
    private int found;
    private int pages;
    private int per_page;
    private int page;
    @JsonIgnore
    private String clusters;
    @JsonIgnore
    private List<String> arguments;
    private String alternate_url;
}
