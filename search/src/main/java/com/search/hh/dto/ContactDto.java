package com.search.hh.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ContactDto {

    private String contactName;
    private String contactEmail;
    private PhoneDto contactPhone;

}
