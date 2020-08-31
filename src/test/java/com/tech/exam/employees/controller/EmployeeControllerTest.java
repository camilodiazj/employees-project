package com.tech.exam.employees.controller;

import com.tech.exam.employees.api.model.EmployeeRequest;
import com.tech.exam.employees.api.model.EmployeeResponse;
import com.tech.exam.employees.api.model.SuccessfulResponse;
import com.tech.exam.employees.exceptions.ServiceException;
import com.tech.exam.employees.repository.entity.Employee;
import com.tech.exam.employees.service.IEmployeeService;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

  @InjectMocks
  EmployeeController employeeController;
  @Mock
  IEmployeeService employeeService;

  @Before
  public void init() throws NoSuchFieldException {
    FieldSetter.setField(
        employeeController,
        EmployeeController.class.getDeclaredField("employeeService"),
        employeeService
    );
  }

  @Test
  public void shouldFindAll() throws ServiceException {

    Mockito.when(employeeService.findAll()).thenReturn(
        Collections.singletonList(new EmployeeResponse()));

    ResponseEntity<List<EmployeeResponse>> result = employeeController
        .findAll();

    Assert.assertNotNull(result);
    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  public void shouldFindById() throws ServiceException {

    EmployeeResponse employee = new EmployeeResponse();
    employee.setFullName("camilo diaz jaimes");

    Mockito.when(employeeService.findById(Mockito.anyLong())).thenReturn(employee);

    ResponseEntity<EmployeeResponse> result = employeeController.findById(1L);

    Assert.assertEquals(employee.getFullName(), result.getBody().getFullName());
  }

  @Test
  public void shouldCreate() throws ServiceException {

    ResponseEntity<SuccessfulResponse> result = employeeController
        .create(Mockito.any(EmployeeRequest.class));

    Assert.assertNotNull(result);
    Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
  }

  @Test
  public void shouldUpdate() throws ServiceException {

    ResponseEntity<SuccessfulResponse> result = employeeController.update(Mockito.any(Employee.class));

    Assert.assertNotNull(result);
    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  public void shouldSetBoss() throws ServiceException {

    ResponseEntity<SuccessfulResponse> result = employeeController.setBoss(Mockito.anyLong(),Mockito.anyLong());

    Assert.assertNotNull(result);
    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
  }

  @Test
  public void shouldDeleteById() throws ServiceException {

    ResponseEntity<SuccessfulResponse> result = employeeController.deleteById(Mockito.anyLong());

    Assert.assertNotNull(result);
    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
  }

}
