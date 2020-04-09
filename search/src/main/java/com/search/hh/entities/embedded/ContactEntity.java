package com.search.hh.entities.embedded;

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
public class ContactEntity {

    @Size(max = 300)
    private String contactName;
    @Email
    private String contactEmail;

    @Embedded
    private PhoneEntity contactPhone;

}
