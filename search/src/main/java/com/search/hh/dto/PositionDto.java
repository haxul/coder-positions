package com.search.hh.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PositionDto {
    private int id;
    private boolean isFavorite = false;
    private String title;
    private AreaDto area;
    private SalaryDto salary;
    private boolean isOpen;
    private EmployerDto employer;
    private Date published;
    private String description;
    private ContactDto contact;
    private int headHunterId;
    private List<SkillDto> skills;
    private String experience;
}
