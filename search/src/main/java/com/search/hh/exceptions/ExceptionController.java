package com.search.hh.exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = {UserAlreadyExistsException.class, IncorrectUserPhoneToRegister.class, ConstraintViolationException.class})
//    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
//        String text = ex == null ? ex.toString() : ex.getLocalizedMessage();
//        ErrorMessage errorMessage = new ErrorMessage(text, new Date());
//        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }
//public class UserAlreadyExistsException extends RuntimeException {
//
//    public UserAlreadyExistsException(String message) {
//        super(message);
//    }
//}
}
