package com.tech.exam.employees.exceptions;

public class BusinessException extends ServiceException {

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
