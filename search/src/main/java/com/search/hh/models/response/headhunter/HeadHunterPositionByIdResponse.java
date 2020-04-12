package com.search.hh.models.response.headhunter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HeadHunterPositionByIdResponse {
    private String id;
    @JsonIgnore
    private boolean premium;
    @JsonIgnore
    private Billing_type billing_type;
    @JsonIgnore
    private List<Object> relations;
    private String name;
    @JsonIgnore
    private String insider_interview;
    @JsonIgnore
    private boolean response_letter_required;
    @JsonIgnore
    AreaHh area;
    @JsonIgnore
    private String salary;
    @JsonIgnore
    TypeHh type;
    @JsonIgnore
    private String address;
    @JsonIgnore
    private boolean allow_messages;
    @JsonIgnore
    private Site site;
    private Experience experience;
    @JsonIgnore private Schedule schedule;
    @JsonIgnore Employment employment;
    @JsonIgnore private String department;
    @JsonIgnore private String contacts;
    private String description;
    @JsonIgnore private String branded_description;
    @JsonIgnore private String vacancy_constructor_template;
    @JsonProperty(value = "key_skills")
    private List<Skill> keySkills;
    @JsonIgnore private boolean accept_handicapped;
    @JsonIgnore private boolean accept_kids;
    @JsonIgnore private boolean archived;
    @JsonIgnore private String response_url;
    @JsonIgnore List<Object> specializations;
    @JsonIgnore private String code;
    @JsonIgnore private boolean hidden;
    @JsonIgnore private boolean quick_responses_allowed;
    @JsonIgnore List<Object> driver_license_types;
    @JsonIgnore private boolean accept_incomplete_resumes;
    @JsonIgnore EmployerHh employer;
    @JsonIgnore private String published_at;
    @JsonIgnore private String created_at;
    @JsonIgnore private String negotiations_url;
    @JsonIgnore private String suitable_resumes_url;
    @JsonIgnore private String apply_alternate_url;
    @JsonIgnore private boolean has_test;
    @JsonIgnore private String test;
    @JsonIgnore private String alternate_url;

}


@Data
class EmployerHh {
    private String id;
    private String name;
    private String url;
    private String alternate_url;
    @JsonIgnore
    Logo_urls Logo_urlsObject;
    private String vacancies_url;
    private boolean trusted;
}

@Data
class Logo_urls {
    private String original;
}

@Data
class Employment {
    private String id;
    private String name;
}

@Data
class Schedule {
    private String id;
    private String name;
}



@Data
class Site {
    private String id;
    private String name;
}

@Data
class TypeHh {
    private String id;
    private String name;
}

@Data
class AreaHh {
    private String id;
    private String name;
    private String url;
}

@Data
class Billing_type {
    private String id;
    private String name;
}