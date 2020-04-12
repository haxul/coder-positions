package com.search.hh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    private String phoneComment;
    private String phoneCity;
    private String phoneNumber;
    private String country;
}
