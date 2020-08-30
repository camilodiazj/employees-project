package com.tech.exam.employees.controller;

import com.tech.exam.employees.api.model.ErrorResponse;
import com.tech.exam.employees.exceptions.BusinessException;
import com.tech.exam.employees.exceptions.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ErrorHandlingControllerTest {

  @InjectMocks ErrorHandlingController errorHandlingController;

  @Test
  public void shouldHandleBusinessException(){
    ResponseEntity<ErrorResponse> result = errorHandlingController
        .handleBusinessException(new BusinessException("Fail to get employee"));

    Assert.assertEquals("Fail to get employee",result.getBody().getDescription());
  }

  @Test
  public void shouldHandleServiceException(){
    ResponseEntity<ErrorResponse> result = errorHandlingController
        .handleBusinessException(new ServiceException("Fail Service::createEmployee"));

    Assert.assertEquals("Fail Service::createEmployee",result.getBody().getDescription());
  }

  @Test
  public void shouldHandleException(){
    ResponseEntity<ErrorResponse> result = errorHandlingController
        .handleBusinessException(new Exception("Array usual exception"));

    Assert.assertEquals("Array usua exception",result.getBody().getDescription());
  }
}
