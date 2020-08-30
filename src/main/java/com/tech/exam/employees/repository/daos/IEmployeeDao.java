package com.tech.exam.employees.repository.daos;

import com.tech.exam.employees.repository.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeDao extends CrudRepository<Employee, Long> {

}
