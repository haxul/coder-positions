package com.search.hh.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PositionDto {
    private int id;
    private int headHunterId;
    private String title;
    private int areaId;
    private int areaName;
    private int salaryFrom;
    private int salaryTo;
    private String currency;
    private boolean isOpen;
    private String employerName;
    private String employerUrl;
    private Date published;
    private String requirement;
    private String responsibility;
    private String contactName;
    private String contactEmail;
    private String phoneComment;
    private String phoneCity;
    private String phoneNumber;
    private String country;
}
