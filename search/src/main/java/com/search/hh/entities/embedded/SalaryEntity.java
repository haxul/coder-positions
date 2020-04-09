package com.search.hh.entities.embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class SalaryEntity {

    @Min(0)
    private int salaryFrom;
    @Min(0)
    private int salaryTo;
    @Size(max = 50)
    private String currency;
}
