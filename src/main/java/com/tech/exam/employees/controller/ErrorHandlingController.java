package com.tech.exam.employees.controller;

import com.tech.exam.employees.api.model.ErrorResponse;
import com.tech.exam.employees.exceptions.BusinessException;
import com.tech.exam.employees.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
    return new ResponseEntity<>(new ErrorResponse("EmployeesError001", e.getMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(ServiceException e) {
    return new ResponseEntity<>(new ErrorResponse("EmployeesError998", e.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(Exception e) {
    return new ResponseEntity<>(new ErrorResponse("EmployeesError999", e.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
