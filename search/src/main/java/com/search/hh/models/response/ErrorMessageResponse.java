package com.search.hh.models.response;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessageResponse {

    private String status;
    private String message;
    private Date date;
}
