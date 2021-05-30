package com.benjaminang.project.HotelsMerge.Controller;

import com.benjaminang.project.HotelsMerge.Exceptions.APIEndpointException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(APIEndpointException.class)
  public ResponseEntity<Object> handleAPIEndpointException(Exception ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(Exception ex) {
    return new ResponseEntity<>("Unable to process your request", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
