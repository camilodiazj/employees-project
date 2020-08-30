package com.tech.exam.employees.api.model;

import lombok.Data;

@Data
public class ErrorResponse {

  private Boolean error;
  private String code;
  private String description;

  public ErrorResponse(String code, String description) {
    this.error = true;
    this.code = code;
    this.description = description;
  }
}
