package com.search.hh.entities.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Generated
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerEntity {
    @Size(max = 1000)
    private String employerName;
    @Size(max = 1000)
    private String employerUrl;
}
