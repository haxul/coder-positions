package com.search.hh.entities.embedded;

import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Generated
@Setter
@NoArgsConstructor
public class EmployerEntity {
    @Size(max = 1000)
    private String employerName;
    @Size(max = 1000)
    private String employerUrl;
}
