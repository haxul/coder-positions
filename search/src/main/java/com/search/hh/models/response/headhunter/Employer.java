package com.search.hh.models.response.headhunter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Employer {
    private String id;
    private String name;
    private String url;
    private String alternate_url;
    @JsonIgnore
    private LogoUrls logo_urls;
    private String vacancies_url;
    private boolean trusted;
}
