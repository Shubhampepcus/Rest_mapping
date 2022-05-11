package com.example1.repository;

import com.example1.entity.Employee;

import java.util.List;

public interface EmployeeCustemRepository {
  List<Employee> findByDepartment(String name, String department);
}
