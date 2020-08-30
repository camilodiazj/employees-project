package com.tech.exam.employees.repository.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Basic
  @Column(name = "full_name", nullable = false, unique = true)
  private String fullName;
  @Basic
  @Column(nullable = false)
  private String function;
  @ManyToOne
  private Employee boss;

}
