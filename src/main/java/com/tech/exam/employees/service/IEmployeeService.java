package com.tech.exam.employees.service;

import com.tech.exam.employees.api.model.EmployeeRequest;
import com.tech.exam.employees.api.model.EmployeeResponse;
import com.tech.exam.employees.exceptions.ServiceException;
import com.tech.exam.employees.repository.entity.Employee;
import java.util.List;

public interface IEmployeeService {
  void create(EmployeeRequest employee) throws ServiceException;

  EmployeeResponse findById(Long id) throws ServiceException;

  List<EmployeeResponse> findAll() throws ServiceException;

  void update(Employee employee) throws ServiceException;

  void delete(Long id) throws ServiceException;

  void setBoss(Long idBoss, Long idEmployee) throws ServiceException;
}
