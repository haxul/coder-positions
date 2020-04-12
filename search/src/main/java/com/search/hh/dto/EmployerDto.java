package com.search.hh.dto;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Generated
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDto {
    private String employerName;
    private String employerUrl;
}
