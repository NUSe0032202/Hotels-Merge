package com.benjaminang.project.HotelsMerge.Exceptions;

public class APIEndpointException extends RuntimeException {
  public APIEndpointException(String exception) {
    super(exception);
  }
}
