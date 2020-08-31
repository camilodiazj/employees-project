package com.tech.exam.employees.service;

import com.tech.exam.employees.api.mappers.IEmployeeMapper;
import com.tech.exam.employees.api.model.EmployeeRequest;
import com.tech.exam.employees.api.model.EmployeeResponse;
import com.tech.exam.employees.exceptions.BusinessException;
import com.tech.exam.employees.exceptions.ServiceException;
import com.tech.exam.employees.repository.daos.IEmployeeDao;
import com.tech.exam.employees.repository.entity.Employee;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class EmployeeServiceImpl implements IEmployeeService {


  private final IEmployeeDao iEmployeeDao;
  private final IEmployeeMapper employeeMapper;

  public EmployeeServiceImpl(IEmployeeDao iEmployeeDao,
      IEmployeeMapper employeeMapper) {
    this.iEmployeeDao = iEmployeeDao;
    this.employeeMapper = employeeMapper;
  }

  @Override
  @Transactional
  public void create(EmployeeRequest employee) throws ServiceException {
    try {
      if (employeeNameExists(employee.getFullName())) {
        throw new BusinessException(
            "Employee with name '" + employee.getFullName() + "' already exists");
      }
      iEmployeeDao.save(employeeMapper.employeeRequestToEmployeeEntity(employee));
    } catch (BusinessException e) {
      throw e;
    } catch (Exception e) {
      log.error("Error EmployeeServiceImpl::create");
      throw new ServiceException("Error EmployeeServiceImpl::create");
    }
  }

  @Override
  @Transactional(readOnly = true)
  public EmployeeResponse findById(Long id) throws ServiceException {
    try {
      Employee employee = iEmployeeDao.findById(id)
          .orElseThrow(() -> new BusinessException("Employee not found"));
      return employeeMapper.employeeEntityToEmployeeResponse(employee);

    } catch (BusinessException e) {
      throw e;
    } catch (Exception e) {
      log.error("Error EmployeeServiceImpl::findAll", e);
      throw new ServiceException("Error EmployeeServiceImpl::findById", e);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<EmployeeResponse> findAll() throws ServiceException {
    try {
      List<Employee> employees = (List<Employee>) iEmployeeDao.findAll();

      return employees.stream()
          .map(employeeMapper::employeeEntityToEmployeeResponse)
          .collect(Collectors.toList());

    } catch (Exception e) {
      log.error("Error EmployeeServiceImpl::findAll", e);
      throw new ServiceException("Error EmployeeServiceImpl::findAll", e);
    }
  }

  @Override
  @Transactional
  public void update(Employee employee) throws ServiceException {
    try {

      Employee employeeToUpdate = iEmployeeDao.findById(employee.getId())
          .orElseThrow(() -> new BusinessException("Employee does not exist"));

      boolean employeeChangeFullName = !employee.getFullName()
          .equals(employeeToUpdate.getFullName());

      if (employeeChangeFullName && employeeNameExists(employee.getFullName())) {
        throw new BusinessException(
            "Employee with name '" + employee.getFullName() + "' already exists");
      }

      employeeToUpdate.setFullName(employee.getFullName());
      employeeToUpdate.setFunction(employee.getFunction());
      iEmployeeDao.save(employeeToUpdate);

    } catch (BusinessException e) {
      throw e;
    } catch (Exception e) {
      log.error("Error EmployeeServiceImpl::update", e);
      throw new ServiceException("Error EmployeeServiceImpl::update", e);
    }
  }

  @Override
  @Transactional
  public void delete(Long id) throws ServiceException {
    try {
      if (!iEmployeeDao.findById(id).isPresent()) {
        throw new BusinessException("Employee does not exist");
      }
      iEmployeeDao.deleteById(id);

    } catch (BusinessException e) {
      throw e;
    } catch (Exception e) {
      log.error("Error EmployeeServiceImpl::delete", e);
      throw new ServiceException("Error EmployeeServiceImpl::delete", e);
    }
  }

  @Override
  @Transactional
  public void setBoss(Long idBoss, Long idEmployee) throws ServiceException {
    try {
      if (idBoss.equals(idEmployee)) {
        throw new BusinessException("You can't be your own boss.");
      }

      Employee employee = iEmployeeDao.findById(idEmployee)
          .orElseThrow(() -> new BusinessException("Employee not found"));

      Employee boss = iEmployeeDao.findById(idBoss)
          .orElseThrow(() -> new BusinessException("Boss not found"));

      if(boss.getBoss() != null && boss.getBoss().getId().equals(idEmployee)){
        throw new BusinessException("You can't be boss of your boss.");
      }

      employee.setBoss(boss);
      iEmployeeDao.save(employee);
    } catch (BusinessException e) {
      throw e;
    } catch (Exception e) {
      log.error("Error EmployeeServiceImpl::setBoss", e);
      throw new ServiceException("Error EmployeeServiceImpl::setBoss", e);
    }
  }

  private boolean employeeNameExists(String fullName){
    return iEmployeeDao.findByFullName(fullName)
        .isPresent();
  }

}
