package com.search.hh.dto;

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
public class SalaryDto {

    private Integer salaryFrom;
    private Integer salaryTo;
    private String currency;
}
