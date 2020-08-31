package com.tech.exam.employees.controller.definitions;

import com.tech.exam.employees.api.model.EmployeeRequest;
import com.tech.exam.employees.api.model.EmployeeResponse;
import com.tech.exam.employees.api.model.SuccessfulResponse;
import com.tech.exam.employees.exceptions.ServiceException;
import com.tech.exam.employees.repository.entity.Employee;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface EmployeeDefinition {
  @GetMapping
  ResponseEntity<List<EmployeeResponse>> findAll() throws ServiceException;

  @GetMapping("/{id}")
  ResponseEntity<EmployeeResponse> findById(@PathVariable Long id) throws ServiceException;

  @PostMapping
  ResponseEntity<SuccessfulResponse> create(@RequestBody EmployeeRequest employee) throws ServiceException;

  @PutMapping
  ResponseEntity<SuccessfulResponse> update(@RequestBody Employee employee) throws ServiceException;

  @PutMapping("/boss")
  ResponseEntity<SuccessfulResponse> setBoss(@RequestParam Long idBoss, @RequestParam Long idEmployee)
      throws ServiceException;

  @DeleteMapping("/{id}")
  ResponseEntity<SuccessfulResponse> deleteById(@PathVariable Long id) throws ServiceException;
}
