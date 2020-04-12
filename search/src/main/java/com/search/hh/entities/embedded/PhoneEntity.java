package com.search.hh.entities.embedded;

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
public class PhoneEntity {

    @Size(max = 10)
    private String phoneComment;
    @Size(max = 20)
    private String phoneCity;
    @Size(max = 50)
    private String phoneNumber;
    @Size(max = 5)
    private String country;
}
