package com.tech.exam.employees.api.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeRequest {

  @NotBlank(message = "fullName is mandatory")
  private String fullName;
  @NotBlank(message = "function is mandatory")
  private String function;
}
