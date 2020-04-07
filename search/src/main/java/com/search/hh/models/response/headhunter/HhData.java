package com.search.hh.models.response.headhunter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class HhData {
    private int id;
    private boolean premium;
    private String name;
    private Department department;
    private boolean has_test;
    private boolean response_letter_required;
    private Area area;
    private Salary salary;
    private Type type;
    private Address address;
    private String response_url;
    private String sort_point_distance;
    private Employer employer;
    private Date published_at;
    private Date created_at;
    private boolean archived;
    private String apply_alternate_url;
    private String url;
    private String alternate_url;
    private Snippet snippet;
    private Contacts contacts;
    @JsonIgnore
    private String insider_interview;
    @JsonIgnore
    private List<String> relations;
}
