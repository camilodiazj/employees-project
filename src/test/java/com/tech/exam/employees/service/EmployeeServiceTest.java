package com.tech.exam.employees.service;

import com.tech.exam.employees.api.mappers.IEmployeeMapper;
import com.tech.exam.employees.api.model.EmployeeRequest;
import com.tech.exam.employees.api.model.EmployeeResponse;
import com.tech.exam.employees.exceptions.BusinessException;
import com.tech.exam.employees.exceptions.ServiceException;
import com.tech.exam.employees.repository.daos.IEmployeeDao;
import com.tech.exam.employees.repository.entity.Employee;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

  @InjectMocks
  EmployeeServiceImpl employeeService;

  @Mock
  IEmployeeDao iEmployeeDao;

  @Mock
  IEmployeeMapper employeeMapper;

  @Before
  public void init() throws NoSuchFieldException {
    FieldSetter.setField(
        employeeService,
        EmployeeServiceImpl.class.getDeclaredField("iEmployeeDao"),
        iEmployeeDao
    );
    FieldSetter.setField(
        employeeService,
        EmployeeServiceImpl.class.getDeclaredField("employeeMapper"),
        employeeMapper
    );
  }

  @Test
  public void shouldCreate() throws ServiceException {

    Mockito
        .when(employeeMapper.employeeRequestToEmployeeEntity(Mockito.any(EmployeeRequest.class)))
        .thenReturn(new Employee());

    employeeService.create(new EmployeeRequest("camilo diaz jaimes", "developer"));

    Mockito.verify(employeeMapper)
        .employeeRequestToEmployeeEntity(Mockito.any(EmployeeRequest.class));
    Mockito.verify(iEmployeeDao).save(Mockito.any(Employee.class));
  }

  @Test
  public void shouldFindById() throws ServiceException {

    Mockito.when(iEmployeeDao.findById(1L)).thenReturn(Optional.of(new Employee()));
    Mockito.when(employeeMapper.employeeEntityToEmployeeResponse(Mockito.any(Employee.class)))
        .thenReturn(EmployeeResponse.builder().fullName("camilo diaz jaimes").build());

    EmployeeResponse result = employeeService.findById(1L);

    Assert.assertEquals("camilo diaz jaimes", result.getFullName());
  }

  @Test(expected = BusinessException.class)
  public void shouldFindByIdThrowBusinessException() throws ServiceException {
    Mockito.when(iEmployeeDao.findById(1L)).thenReturn(Optional.empty());
    employeeService.findById(1L);
  }

  @Test(expected = ServiceException.class)
  public void shouldFindByIdThrowServiceException() throws ServiceException {
    Mockito.when(iEmployeeDao.findById(1L)).thenReturn(null);
    employeeService.findById(1L);
  }

  @Test
  public void shouldFindAll() throws ServiceException {
    Mockito.when(iEmployeeDao.findAll()).thenReturn(Collections.singletonList(new Employee()));
    Mockito.when(employeeMapper.employeeEntityToEmployeeResponse(Mockito.any(Employee.class)))
        .thenReturn(EmployeeResponse.builder().fullName("camilo diaz jaimes").build());

    List<EmployeeResponse> result = employeeService.findAll();
    Assert.assertNotNull(result);
  }

  @Test
  public void shouldSetBoss() throws ServiceException {
    Mockito.when(iEmployeeDao.findById(1L)).thenReturn(Optional.of(new Employee()));
    Mockito.when(iEmployeeDao.findById(2L)).thenReturn(Optional.of(new Employee()));

    employeeService.setBoss(1L, 2L);

    Mockito.verify(iEmployeeDao).findById(1L);
  }

  @Test(expected = BusinessException.class)
  public void shouldSetBossThrowBusinessException() throws ServiceException {
    Mockito.when(iEmployeeDao.findById(1L)).thenReturn(Optional.empty());
    Mockito.when(iEmployeeDao.findById(2L)).thenReturn(Optional.of(new Employee()));

    employeeService.setBoss(1L, 2L);

    Mockito.verify(iEmployeeDao).findById(1L);
  }

  @Test(expected = ServiceException.class)
  public void shouldSetBossThrowServiceException() throws ServiceException {
    Mockito.when(iEmployeeDao.findById(1L)).thenReturn(null);
    Mockito.when(iEmployeeDao.findById(2L)).thenReturn(Optional.of(new Employee()));

    employeeService.setBoss(1L, 2L);

    Mockito.verify(iEmployeeDao).findById(1L);
  }

}
