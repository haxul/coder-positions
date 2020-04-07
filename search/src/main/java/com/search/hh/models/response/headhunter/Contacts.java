package com.search.hh.models.response.headhunter;

import lombok.Data;

import java.util.List;

@Data
public class Contacts {
    private String name;
    private String email;
    private List<Phone> phones;
}
