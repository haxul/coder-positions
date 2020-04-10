package com.search.hh.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryEntity {

    private Integer salaryFrom;
    private Integer salaryTo;
    @Size(max = 50)
    private String currency;
}
