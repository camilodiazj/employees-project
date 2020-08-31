package com.tech.exam.employees.repository.daos;

import com.tech.exam.employees.repository.entity.Employee;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeDao extends CrudRepository<Employee, Long> {
  Optional<Employee> findByFullName(String fullName);
}
