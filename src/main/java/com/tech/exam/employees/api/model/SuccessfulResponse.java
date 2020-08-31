package com.tech.exam.employees.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class SuccessfulResponse {

  private Boolean ok;
  private HttpStatus status;
  private String message;

  public static SuccessfulResponse okResponse(String message) {
    return new SuccessfulResponse(true,HttpStatus.OK, message);
  }

  public static SuccessfulResponse createdResponse(String message) {
    return new SuccessfulResponse(true,HttpStatus.CREATED, message);
  }
}