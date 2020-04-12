package com.search.hh.exceptions;


import com.search.hh.models.response.ErrorMessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PositionNotFound.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        ErrorMessageResponse response = new ErrorMessageResponse();
        response.setDate(new Date());
        response.setStatus("Bad Request");
        response.setMessage("User is not found.");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
