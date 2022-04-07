package com.example1.repository;

import com.example1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public List<Employee> findByname(String name);
    public List<Employee> findByemail(String email);
}
